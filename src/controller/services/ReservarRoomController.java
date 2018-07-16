package controller.services;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Guest;
import model.entity.Room;

@SuppressWarnings("serial")
public class ReservarRoomController extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();
		if (uGoogle != null) {

			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "select  from " + Guest.class.getName();
			@SuppressWarnings("unchecked")
			List<Guest> guests = (List<Guest>) pm.newQuery(query).execute();

			String query2 = "select  from " + Room.class.getName();
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) pm.newQuery(query2).execute();
			boolean existe = false;
			for (int i = 0; i < guests.size(); i++) {
				if (guests.get(i).getEmail() == uGoogle.getEmail()) {
					int tipo = Integer.parseInt(req.getParameter("tipo"));
					double precio;
					Key k = KeyFactory.createKey(Guest.class.getSimpleName(),
							new Long(guests.get(i).getId()).longValue());
					Guest temp = pm.getObjectById(Guest.class, k);
					if (tipo == 0) {
						precio = 50;
					} else if (tipo == 1) {
						precio = 100;
					} else if (tipo == 2) {
						precio = 200;
					} else {
						precio = 300;
					}
					Room temp2 = null;
					for (int j = 0; j < rooms.size(); j++) {
						if (rooms.get(j).getEstado() == 0 && rooms.get(j).getTipo() == tipo) {
							k = KeyFactory.createKey(Room.class.getSimpleName(),
									new Long(rooms.get(j).getId()).longValue());
							temp2 = pm.getObjectById(Room.class, k);
							temp2.setEstado(1);
							temp2.setIdGuest(uGoogle.getEmail());
							temp.transaccion(precio);
							existe = true;
							break;
						}
					}
				}
				if (existe) {
					break;
				}
			}
			if (!existe) {
				req.setAttribute("tipo", req.getParameter("tipo"));
				req.getRequestDispatcher("/WEB-INF/Services/room.jsp").forward(req, resp);
			} else {
				resp.sendRedirect("../");
			}
		} else {

			RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/deny.jsp");
			dp.forward(req, resp);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// Saco el usuario actual
		com.google.appengine.api.users.User uGoogle = UserServiceFactory.getUserService().getCurrentUser();

		// Cuando no hay nadie logeado
		if (uGoogle == null) {
			RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/deny.jsp");
			dp.forward(req, res);
		} else {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "select  from " + Room.class.getName();
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) pm.newQuery(query).execute();
			Room temp = null;
			int tipo = Integer.parseInt(req.getParameter("tipo"));
			double precio;
			for (int i = 0; i < rooms.size(); i++) {
				if (rooms.get(i).getEstado() == 0 && rooms.get(i).getTipo() == tipo) {
					Key k = KeyFactory.createKey(Room.class.getSimpleName(),
							new Long(rooms.get(i).getId()).longValue());
					temp = pm.getObjectById(Room.class, k);

				}
			}
			if (temp != null) {
				temp.setEstado(1);
				temp.setIdGuest(uGoogle.getEmail());
				if (tipo == 0) {
					precio = 50;
				} else if (tipo == 1) {
					precio = 100;
				} else if (tipo == 2) {
					precio = 200;
				} else {
					precio = 300;
				}
				if (req.getParameter("gender").equals("hombre")) {
					Guest g = new Guest(req.getParameter("name"), req.getParameter("Arequipa"),
							req.getParameter("phone"), req.getParameter("dni"), true, true, req.getParameter("age"),
							req.getParameter(uGoogle.getEmail()), null);
					g.transaccion(precio);
					pm.makePersistent(g);

				} else {
					Guest g = new Guest(req.getParameter("name"), req.getParameter("Arequipa"),
							req.getParameter("phone"), req.getParameter("dni"), false, true, req.getParameter("age"),
							req.getParameter(uGoogle.getEmail()), null);
					g.transaccion(precio);
					pm.makePersistent(g);

				}
				pm.close();
				res.sendRedirect("../rooms");
			}

		}

	}

}
