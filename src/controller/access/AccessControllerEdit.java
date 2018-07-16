package controller.access;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.Access;

@SuppressWarnings("serial")
public class AccessControllerEdit extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String iddd=null;
		String role = req.getParameter("role");
		String resource = req.getParameter("resource");
		String id = req.getParameter("accessId2");
		String query = "select from " + Access.class.getName();
		@SuppressWarnings("unchecked")
		List<Access> access = (List<Access>) pm.newQuery(query).execute();
		
		for(int i=0;i<access.size();i++){
			iddd=access.get(i).getId().toString();
			if(iddd.equals(id)) {
				//ninguno cambia
				if(req.getParameter("resource").equals("none") && req.getParameter("status").equals("none")) {
					System.out.println("Entro al primero");
				}
				//cambios el resource y el status no
				else if (!req.getParameter("resource").equals("none") && req.getParameter("status").equals("none")) {
					access.get(i).setResourceAccess(resource);
				}
				//cambia el status y el resource NO
				else if (req.getParameter("resource").equals("none") && !req.getParameter("status").equals("none")) {
					if(req.getParameter("status").equals("true")) {
						access.get(i).setAccessStatus(true);
					}else {
						access.get(i).setAccessStatus(false);
						}
				}
				//cambia todo
				else if(!req.getParameter("resource").equals("none") && !req.getParameter("status").equals("none")) {
					if(req.getParameter("status").equals("true")) {
						access.get(i).setResourceAccess(resource);
						access.get(i).setAccessStatus(true);
					}else {
						access.get(i).setResourceAccess(resource);
						access.get(i).setAccessStatus(false);
					}
				}
			}
		}
		res.sendRedirect("/access");
	}	
}