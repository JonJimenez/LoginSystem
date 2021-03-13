package dao;

import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.*;

public class UserDao {

    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(user);
            // commit transaction
            //transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
              //transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public boolean isUser(String userName) {
    	Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName).uniqueResult();

            if (user != null ) {
                return true;
            }
            // commit transaction
            //transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                //transaction.rollback();
            }
            e.printStackTrace();
        }
    	return false;
    }
    public User validate(String userName, String password) {

        Transaction transaction = null;
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName").setParameter("userName", userName).uniqueResult();

            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
            // commit transaction
            //transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
               // transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
    public boolean isValidEmail(String email) {
    	 String emailRegex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    	 Pattern pat = Pattern.compile(emailRegex); 
         if (email == null) 
             return false; 
         return pat.matcher(email).matches(); 
    }
    public boolean isValidPassword(String password) {
     if (password == null) {
            return false; 
     }
   	 if(password.length()>20||password.length()<4) {
   		 return false;
   	 }
   	 String passRegex = "[a-zA-Z0-9]*";
   	 Pattern pat = Pattern.compile(passRegex);
     return pat.matcher(password).matches(); 
    }
    public boolean isValidUsername(String username) {
        if (username == null) {
               return false; 
        }
        if(username.length()>20||username.length()<4) {
      		 return false;
      	 }
      	String passRegex = "[a-zA-Z0-9]*";
      	Pattern pat = Pattern.compile(passRegex);
      	return pat.matcher(username).matches(); 
    }

}