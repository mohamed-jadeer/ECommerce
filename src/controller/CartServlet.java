package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.CartBean;
import model.Products;
import model.ProductsBean;

@WebServlet("/cart.do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId, orderQuantity, userId;
		productId = request.getParameter("product_id");
		orderQuantity = request.getParameter("order_quantity");
		HttpSession session = request.getSession();
		userId = session.getAttribute("userId").toString();
		//System.out.println(userId);
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		ProductsBean productsbean = (ProductsBean) context.getBean("productsbean");
		if(productsbean.checkStock(Integer.parseInt(productId), Integer.parseInt(orderQuantity))){
			Products product = productsbean.getProductbyId(productId);
			CartBean cartbean = (CartBean) context.getBean("cartbean");
			cartbean.addToCart(product, userId, Integer.parseInt(orderQuantity));
			//System.out.println("Inside if block" + userId);
			response.sendRedirect("http://localhost:8080/ECommerce/products.do");
		}
		else{
			response.sendRedirect("http://localhost:8080/ECommerce/products.do?stock=false");
		}
		
	}

}
