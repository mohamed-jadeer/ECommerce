package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Customers;
import model.CustomersBean;

@WebServlet("/registration.do")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId, firstName, lastName, password, shippingAddress, emailAddress, contactNumber;
		userId = request.getParameter("user_id");
		firstName = request.getParameter("first_name");
		lastName = request.getParameter("last_name");
		password = request.getParameter("password");
		shippingAddress = request.getParameter("shipping_address");
		emailAddress = request.getParameter("email_address");
		contactNumber = request.getParameter("contact_number");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		Customers customer = (Customers) context.getBean("customers");
		customer.setUserId(userId);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPassword(password);
		customer.setShippingAddress(shippingAddress);
		customer.setEmailAddress(emailAddress);
		customer.setContactNumber(contactNumber);
		
		CustomersBean customersbean = (CustomersBean) context.getBean("customersbean");
		if(customersbean.checkUserId(userId)){
			customersbean.addCustomer(customer);
			response.sendRedirect("http://localhost:8080/ECommerce/login.jsp?reg=true");
		}
		else{
			response.sendRedirect("http://localhost:8080/ECommerce/register.jsp?usr=false");
		}
		
		
	}

}
