package controller.guest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.Access;
import model.entity.Guest;
import model.entity.Role;
import model.entity.Users;

@SuppressWarnings("serial")
public class GuestControllerAdd extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		//Saco el usuario actual
		com.google.appengine.api.users.User uGoogle= UserServiceFactory.getUserService().getCurrentUser();
		
		//saco listas de usuarios, roles, y accesos
		PersistenceManager pm1 = PMF.get().getPersistenceManager();
		PersistenceManager pm2 = PMF.get().getPersistenceManager();
		PersistenceManager pm3 = PMF.get().getPersistenceManager();
		
		String query1 = "select from " + Users.class.getName();
		@SuppressWarnings("unchecked")
		List<Users> users = (List<Users>) pm1.newQuery(query1).execute();
		
		String query2 = "select from " + Role.class.getName();
		@SuppressWarnings("unchecked")
		List<Role> roles = (List<Role>) pm2.newQuery(query2).execute();
		
		String query3 = "select from " + Access.class.getName();
		@SuppressWarnings("unchecked")
		List<Access> access = (List<Access>) pm3.newQuery(query3).execute();
		
		//Los recursos disponibles
		String s1 ="All resources";
		String s2 ="Only views";
		String s3 ="All features resources";
		
		
		//Cuando no hay nadie logeado
		if(uGoogle == null) {
			RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/deny.jsp");
			dp.forward(req, res);
		}
		//Cuando hay alguien logeado
		else if(uGoogle != null){
			/*Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			PersistenceManager pm = PMF.get().getPersistenceManager();
				if(req.getParameter("action").equals("eventCreate")) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Events/add.jsp");
					dispatcher.forward(req, res);
				} else
					if(req.getParameter("action").equals("eventCreateDo")) {
					// create the new event
					String x= "sin definir";
					String y= "sin definir";
					String hora=req.getParameter("houra");
					String hora2=req.getParameter("hourDate");
					String day=req.getParameter("day");
					String month=req.getParameter("month");
					String year=req.getParameter("year");
					y=day+"/"+month+"/"+year;
					x=hora+" "+hora2;
					Event a = new Event(
					(String)req.getParameter("name"),
					(String)req.getParameter("location"),
					x,
					y,
					Integer.parseInt(req.getParameter("capacity")),
					true,
					sdf.format(date));
				try{
					pm.makePersistent(a);
				}finally{
					pm.close();
				}
				res.sendRedirect("/events");
			}*/
			boolean userExisteBaseDatos = false;
			boolean estadoUser = false;
			boolean rolEnBaseDatos=false;
			boolean estadoAcceso = false;
			String rolePersonaEcontrada = null;
			//que recursos tiene el rol de la persona encontrada
			String accesoRolePersonaEncontrada = null;
			
			//Sacamos el email de la persona logueada
			String emailCurrent = uGoogle.getEmail();
			
			//Buscamos por email si ese usuario existe en la base de datos USERS
			for(int i=0;i<users.size();i++) {
				//variable temporal que toma el email
				String tmp = users.get(i).getUserMail();
				//Se compara la variable temporal con el email actual
				if(tmp.equals(emailCurrent)) {
					//Si lo encuentra, el usuario registrado es verdadero
					userExisteBaseDatos = true;
					//Sacamos el rol de la persona encontrada
					rolePersonaEcontrada=users.get(i).getUserRole();
					//Sacamos el estado de la persona encontrada
					estadoUser = users.get(i).getUserStatus();
				}
			}
			//Buscamos si el role de la persona existe en la base de datos ACCESS
			for(int i=0;i<access.size();i++) {
				//Sacamos en la variable temporal del nombre del role de este acceso
				String tmp=access.get(i).getRoleAccess();
				//Se compara la variable temporal con el role de la persona encontrada
				if(tmp.equals(rolePersonaEcontrada)) {
					//si lo encuentra, el rol existe
					rolEnBaseDatos=true;
					//Sacamos el recurso de este rol
					accesoRolePersonaEncontrada = access.get(i).getResourceAccess();
					//Sacamos el estado de este acceso
					estadoAcceso=access.get(i).getAccessStatus();
					}
			}
			//Bien ahora tenemos el Rol y sus accesos del usuario encontrado en la base de datos
			
			//Solo podra entrar si es un usuario existente, tiene su estado true, y su role existe en base de datos.
			if(userExisteBaseDatos && estadoUser && estadoAcceso && rolEnBaseDatos) {
						
						//Si tiene todos los recursos
						if(accesoRolePersonaEncontrada.equals(s1)) {
							
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							PersistenceManager pm = PMF.get().getPersistenceManager();
								if(req.getParameter("action").equals("guestCreate")) {
									RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Guests/add.jsp");
									dispatcher.forward(req, res);
								} else
									if(req.getParameter("action").equals("guestCreateDo")) {
									// create the new guest
										if(req.getParameter("gender").equals("hombre")){
											Guest g = new Guest(req.getParameter("name"), req.getParameter("city"), req.getParameter("phone"),
													req.getParameter("dni"), true, true, req.getParameter("age"), req.getParameter("email"), null);
											// persist the entity
											try {
												pm.makePersistent(g);
											} finally {
												pm.close();
											}
											res.sendRedirect("/guests");

										}
										else{
											Guest g = new Guest(req.getParameter("name"), req.getParameter("city"), req.getParameter("phone"),
													req.getParameter("dni"), false, true, req.getParameter("age"), req.getParameter("email"), null);
											// persist the entity	
											try {
												pm.makePersistent(g);
											} finally {
												pm.close();
											}
											res.sendRedirect("/guests");

										}
							}
							}
						//Si tiene todos los recursos solode eventos
						else if (accesoRolePersonaEncontrada.equals(s3)) {
							
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							PersistenceManager pm = PMF.get().getPersistenceManager();
								if(req.getParameter("action").equals("guestCreate")) {
									RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Guests/add.jsp");
									dispatcher.forward(req, res);
								} else
									if(req.getParameter("action").equals("guestCreateDo")) {
									// create the new guest
										if(req.getParameter("gender").equals("hombre")){
											Guest g = new Guest(req.getParameter("name"), req.getParameter("city"), req.getParameter("phone"),
													req.getParameter("dni"), true, true, req.getParameter("age"), req.getParameter("email"), null);
											// persist the entity
											try {
												pm.makePersistent(g);
											} finally {
												pm.close();
											}
											res.sendRedirect("/guests");

										}
										else{
											Guest g = new Guest(req.getParameter("name"), req.getParameter("city"), req.getParameter("phone"),
													req.getParameter("dni"), false, true, req.getParameter("age"), req.getParameter("email"), null);
											// persist the entity	
											try {
												pm.makePersistent(g);
											} finally {
												pm.close();
											}
											res.sendRedirect("/guests");

										}
							}
							
						}
						//Si solo puede ver eventos
						else if(accesoRolePersonaEncontrada.equals(s2)) {
							
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							PersistenceManager pm = PMF.get().getPersistenceManager();
								if(req.getParameter("action").equals("guestCreate")) {
									RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Guests/add.jsp");
									dispatcher.forward(req, res);
								} else
									if(req.getParameter("action").equals("guestCreateDo")) {
									// create the new guest
										if(req.getParameter("gender").equals("hombre")){
											Guest g = new Guest(req.getParameter("name"), req.getParameter("city"), req.getParameter("phone"),
													req.getParameter("dni"), true, true, req.getParameter("age"), req.getParameter("email"), null);
											// persist the entity
											try {
												pm.makePersistent(g);
											} finally {
												pm.close();
											}
											res.sendRedirect("/guests");

										}
										else{
											Guest g = new Guest(req.getParameter("name"), req.getParameter("city"), req.getParameter("phone"),
													req.getParameter("dni"), false, true, req.getParameter("age"), req.getParameter("email"), null);
											// persist the entity	
											try {
												pm.makePersistent(g);
											} finally {
												pm.close();
											}
											res.sendRedirect("/guests");

										}
							}
							
							
						}
					}
				else {
						RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/deny.jsp");
						dp.forward(req, res);
					}
		}
		
	}
}
