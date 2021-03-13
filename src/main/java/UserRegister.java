import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.HibernateUtil;
import com.example.User;
import dao.UserDao;

/**
 * Servlet implementation class UserRegister
 */
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message = null;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		UserDao dao = new UserDao();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        if(dao.isUser(username)) {
        	message = "Username already exists";
        }
        else {
        	User newUser = new User();
        	if(dao.isValidEmail(email)) {
            	if(dao.isValidPassword(password)) {
            		if(dao.isValidUsername(username)){
            			newUser.setPassword(password);
            			newUser.setEmail(email);
                		newUser.setUsername(username);
                		dao.saveUser(newUser);
                		message = "Registration Successful";
                	}
            		else {
            			message="Invalid Username";
            		}
            	}
            	else {
            		message="Invalid Password";
            	}
        	}
        	else {
        		message="Invalid Email";
        		
        	}
        }
        request.getSession().setAttribute("message", message);
		response.sendRedirect("message.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
