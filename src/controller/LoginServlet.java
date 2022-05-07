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

import model.CustomersBean;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId, password;
		userId = request.getParameter("user_id");
		password = request.getParameter("password");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		CustomersBean customersbean = (CustomersBean) context.getBean("customersbean");
		String status = customersbean.authenticateUser(userId, password);
		if(status == "success"){
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", userId);
			response.sendRedirect("http://localhost:8080/ECommerce/products.do");
		}
		else if(status == "userid"){
			response.sendRedirect("http://localhost:8080/ECommerce/login.jsp?usr=false");
		}
		else{
			response.sendRedirect("http://localhost:8080/ECommerce/login.jsp?pass=false");
		}
		
	}

}
