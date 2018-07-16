package controller.users;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Users;

@SuppressWarnings("serial")
public class UsersControllerDelete extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + Users.class.getName();
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) pm.newQuery(query).execute();
		String roleUsuario=null;
		
		//Saco el usuario actual
		com.google.appengine.api.users.User uGoogle= UserServiceFactory.getUserService().getCurrentUser();
		String usuarioActual = uGoogle.getEmail();
		for(int i=0;i<users.size();i++) {
			String tmp = users.get(i).getUserMail();
			if(tmp.equals(usuarioActual)) {
				roleUsuario= users.get(i).getUserRole();
			}
		}
		
		String name=req.getParameter("id");
		String iddd=null;
		for(int i=0;i<users.size();i++) {
			iddd=users.get(i).getId().toString();
			if(iddd.equals(name) && !users.get(i).getUserRole().equals("Administrador")) {
				pm.deletePersistent(users.get(i));
				res.sendRedirect("/users");
			}else if(iddd.equals(name) && users.get(i).getUserRole().equals("Administrador")) {
				RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/alerta1.jsp");
				dp.forward(req, res);
			}
		}
		//res.sendRedirect("/users");
	}	
}
