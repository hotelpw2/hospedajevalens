package controller.roles;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.Role;
import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class RolesControllerEdit extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String iddd=null;
		String name = req.getParameter("name");
		String id = req.getParameter("roleId2");
		String query = "select from " + Role.class.getName();
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) pm.newQuery(query).execute();
		
		for(int i=0;i<roles.size();i++){
			iddd=roles.get(i).getId().toString();
			if(iddd.equals(id)) {
				roles.get(i).setRoleName(name);
				if(req.getParameter("status").equals("none")) {
					continue;
				}else if(req.getParameter("status").equals("true")){
					roles.get(i).setRoleStatus(true);
				}else if(req.getParameter("status").equals("false")) {
					roles.get(i).setRoleStatus(false);
				}
			}
		}
		res.sendRedirect("/roles");
	}	
}