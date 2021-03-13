import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.HibernateUtil;
import com.example.User;

import dao.UserDao;

/**
 * Servlet implementation class UserAuthenticate
 */
@WebServlet
public class UserAuthenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAuthenticate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        try {
               SessionFactory factory = HibernateUtil.getSessionFactory();
               String message = null;
               Session session = factory.openSession();
               // using HQL
               String userName = request.getParameter("username");
               String password = request.getParameter("password");
               UserDao dao = new UserDao();
               User userLogin=dao.validate(userName, password);
               List<User> list = session.createQuery("FROM User", User.class).list();
               
               session.close();
               
               if(userLogin !=null) {
            	System.out.println("Logging In ");
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<link rel=\"stylesheet\" href=\"css/login.css\">");
                out.println("<h2>Welcome "+userLogin.getUsername()+"</h2>");
                out.println("<h3>List of User Info:</h3>");
                out.println("<table><tr><th>ID</th><th>Username</th><th>Password</th><th>Email</th></tr>");
                for(User user: list) {
                    out.println("<tr><td>"+String.valueOf(user.getID())+"</td>"
                    			+"<td>"+String.valueOf(user.getUsername())+"</td>"
                                +"<td>"+String.valueOf(user.getPassword())+"</td>"
                                +"<td>"+String.valueOf(user.getEmail())+"</td></tr>");
                }	
                out.println("</table>");
                out.println("<form action=\"index.jsp\">");
                out.println("<input type=\"submit\" value=\"Logout\"></form>");
                out.println("</body></html>");
               }
               else {
            	   message = "Error: Couldn't Login ";
            	   request.getSession().setAttribute("message", message);
                   response.sendRedirect("message.jsp");
               }
            
            
        } catch (Exception ex) {
                throw ex;
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}