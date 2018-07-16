package controller.users;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.Role;
import model.entity.Users;
import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class UsersControllerEdit extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		if(req.getParameter("action").equals("userCreateDo")) {
		PersistenceManager pm2 = PMF.get().getPersistenceManager();
		String query2 = "select from " + Role.class.getName();
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) pm2.newQuery(query2).execute();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String iddd=null;
		String email = req.getParameter("mail");
		String role = req.getParameter("role");
		String id = req.getParameter("userId2");
		Users elegido;
		String query = "select from " + Users.class.getName();
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) pm.newQuery(query).execute();
		
		for(int i=0;i<users.size();i++){
			iddd=users.get(i).getId().toString();
			if(iddd.equals(id)) {
				elegido = users.get(i);
				boolean existe = false;
				for(int j=0;j<users.size();j++) {
					if(!elegido.getId().toString().equals(users.get(j).getId().toString()) && users.get(j).getUserMail().equals(email)) {
						existe = true;
						System.out.println("Entro aqui");
					}
				}
				if(existe) {
					req.setAttribute("roles", roles);
					req.setAttribute("users", elegido);
					RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/alerta3.jsp");
					dp.forward(req, res);
					//res.sendRedirect("/WEB-INF/Views/alerta2.jsp");
				}else if(!existe) {
					users.get(i).setUserMail(email);
					if(req.getParameter("role").equals("none") && req.getParameter("status").equals("none")) {
						System.out.println("no cambio nada");
					}
					else if(!req.getParameter("role").equals("none") && req.getParameter("status").equals("none")) {
						users.get(i).setUserRole(role);
					}
					else if(req.getParameter("role").equals("none") && !req.getParameter("status").equals("none")){
						if(req.getParameter("status").equals("true"))
							users.get(i).setUserStatus(true);
						else
							users.get(i).setUserStatus(false);
					}else if(!req.getParameter("role").equals("none") && !req.getParameter("status").equals("none")){
						if(req.getParameter("status").equals("true")) {
							users.get(i).setUserRole(role);
							users.get(i).setUserStatus(true);
						}else {
							users.get(i).setUserRole(role);
							users.get(i).setUserStatus(false);
						}
					}
					res.sendRedirect("/users");
				}
				
				/*users.get(i).setUserMail(email);
				if(req.getParameter("role").equals("none") && req.getParameter("status").equals("none")) {
					continue;
				}
				if(!req.getParameter("role").equals("none") && req.getParameter("status").equals("none")) {
					users.get(i).setUserRole(role);
				}
				else if(req.getParameter("role").equals("none") && !req.getParameter("status").equals("none")){
					if(req.getParameter("status").equals("true"))
						users.get(i).setUserStatus(true);
					else
						users.get(i).setUserStatus(false);
				}else if(!req.getParameter("role").equals("none") && !req.getParameter("status").equals("none")){
					if(req.getParameter("status").equals("true")) {
						users.get(i).setUserRole(role);
						users.get(i).setUserStatus(true);
					}else {
						users.get(i).setUserRole(role);
						users.get(i).setUserStatus(false);
					}
				}
				
				*/
				
				
			}
		}
	//	res.sendRedirect("/users");
	}
	}
}