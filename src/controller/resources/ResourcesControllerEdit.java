package controller.resources;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.Resource;
import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class ResourcesControllerEdit extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String iddd=null;
		String name = req.getParameter("name");
		String id = req.getParameter("resourceId2");
		String query = "select from " + Resource.class.getName();
		@SuppressWarnings("unchecked")
		List<Resource> resources = (List<Resource>) pm.newQuery(query).execute();
		
		for(int i=0;i<resources.size();i++){
			iddd=resources.get(i).getId().toString();
			if(iddd.equals(id)) {
				resources.get(i).setResourceName(name);
				if(req.getParameter("status").equals("none")) {
					continue;
				}else if(req.getParameter("status").equals("true")){
					resources.get(i).setResourceStatus(true);
				}else if(req.getParameter("status").equals("false")) {
					resources.get(i).setResourceStatus(false);
				}
			}
		}
		res.sendRedirect("/resources");
	}	
}