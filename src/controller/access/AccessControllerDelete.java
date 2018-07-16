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
public class AccessControllerDelete extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + Access.class.getName();
		String name=req.getParameter("id");
		@SuppressWarnings("unchecked")
		List<Access> access = (List<Access>) pm.newQuery(query).execute();
		String iddd=null;
		for(int i=0;i<access.size();i++) {
			iddd=access.get(i).getId().toString();
			if(iddd.equals(name)) {
				pm.deletePersistent(access.get(i));
			}
		}
		res.sendRedirect("/access");
	}	
}
