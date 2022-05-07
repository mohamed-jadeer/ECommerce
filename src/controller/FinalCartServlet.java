package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Cart;
import model.CartBean;
import model.Products;
import model.ProductsBean;

@WebServlet("/final_cart.do")
public class FinalCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FinalCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = session.getAttribute("userId").toString();
		PrintWriter out = response.getWriter();
		out.println("<h3> <center> Products in your Cart </center> </h3>");
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		CartBean cartbean = (CartBean) context.getBean("cartbean");
		List<Cart> result = cartbean.getCart(userId);
		ListIterator<Cart> litr = result.listIterator();
		if(litr.hasNext()){
			out.println("<table> <tr> <th> Product Name </th> <th> Unit Price </th> <th> Quantity Ordered </th> <th> Net Amount </hr> </tr>");
		}
		while(litr.hasNext()){
			Cart cart = litr.next();
			out.println("<tr> <td> " + cart.getProductName() + "</td> <td> " + cart.getUnitPrice() + "</td> <td> " + cart.getOrderQuantity() + "</td> <td> " + cart.getNetAmount() + "</td> </tr>");
		}
		out.println("</table>");
		out.println("<form action='save.do' method='get' > <input type='submit' value='Confirm the Order'></form>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
