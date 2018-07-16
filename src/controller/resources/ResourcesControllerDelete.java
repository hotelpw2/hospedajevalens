package controller.resources;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.Resource;

@SuppressWarnings("serial")
public class ResourcesControllerDelete extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + Resource.class.getName();
		String name=req.getParameter("id");
		@SuppressWarnings("unchecked")
		List<Resource> resources = (List<Resource>) pm.newQuery(query).execute();
		String iddd=null;
		for(int i=0;i<resources.size();i++) {
			iddd=resources.get(i).getId().toString();
			if(iddd.equals(name)) {
				pm.deletePersistent(resources.get(i));
			}
		}
		res.sendRedirect("/resources");
	}	
}
