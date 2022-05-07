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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Products;
import model.ProductsBean;

@WebServlet("/products.do")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("<h3> <center> Available Products </center> </h3>");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		ProductsBean productsbean = (ProductsBean) context.getBean("productsbean");
		List<Products> result = productsbean.getAllProducts();
		//out.println("After DB");
		ListIterator<Products> litr = result.listIterator();
		if(litr.hasNext()){
			out.println("<table> <tr> <th> Product Name </th> <th> Unit Price </th> <th> Quantity Required </th></tr>");
		}
		while(litr.hasNext()){
			Products product = litr.next();
			out.println("<tr> <td> " + product.getProductName() + "</td> <td> " + product.getUnitPrice() + "</td> <td> <form action='cart.do' method='post'> <input type='text' name='order_quantity'> </td> <td> <input type='hidden' name='product_id' value='" + product.getProductId() + "'> </td> <td> <input type='submit' value='Add to Cart'> </td> </tr> </form>");
		}
		out.println("</table>");
		if((request.getParameter("stock") != null) && (request.getParameter("stock").equals("false"))){
			out.println("<h4> The Stock availability is not sufficient to meet you requirement. Please try again later </h4>");			
		}
		out.println("<form action='' method='get'> <input type='submit' value='Go to Cart'> </form>");
		out.println("<h3> <center> Previous Order Details: </center> </h3>");
	}

}
