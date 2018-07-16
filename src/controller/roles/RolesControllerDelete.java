package controller.roles;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.Role;

@SuppressWarnings("serial")
public class RolesControllerDelete extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + Role.class.getName();
		String name=req.getParameter("id");
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) pm.newQuery(query).execute();
		String iddd=null;
		for(int i=0;i<roles.size();i++) {
			iddd=roles.get(i).getId().toString();
			if(iddd.equals(name)) {
				pm.deletePersistent(roles.get(i));
			}
		}
		res.sendRedirect("/roles");
	}	
}
