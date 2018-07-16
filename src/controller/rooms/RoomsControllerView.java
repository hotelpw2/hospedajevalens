package controller.rooms;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class RoomsControllerView extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select  from " + Room.class.getName();
		@SuppressWarnings("unchecked")
		List<Room> rooms = (List<Room>) pm.newQuery(query).execute();
		req.setAttribute("rooms", rooms);
		req.getRequestDispatcher("/WEB-INF/Rooms/view.jsp").forward(req, resp);
		pm.close();

	}
}