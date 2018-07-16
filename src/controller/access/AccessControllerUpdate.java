package controller.access;
import java.io.IOException;
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
import model.entity.Role;
import model.entity.Users;
import model.entity.Resource;
@SuppressWarnings("serial")
public class AccessControllerUpdate extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		/*PersistenceManager pm = PMF.get().getPersistenceManager();
		String name=req.getParameter("id");
				String iddd=null;
				Access elegido = null;
				String query = "select from " + Access.class.getName();
				@SuppressWarnings("unchecked")
				List<Access> access = (List<Access>) pm.newQuery(query).execute();
				String query2 = "select from " + Role.class.getName();
				@SuppressWarnings("unchecked")
				List<Role> roles = (List<Role>) pm.newQuery(query2).execute();
				String query3 = "select from " + Resource.class.getName();
				@SuppressWarnings("unchecked")
				List<Resource> resources = (List<Resource>) pm.newQuery(query3).execute();
				
				for(int i=0;i<access.size();i++) {
					iddd=access.get(i).getId().toString();
					if(iddd.equals(name)) {
						elegido=access.get(i);
						req.setAttribute("access", elegido);
						req.setAttribute("roles", roles);
						req.setAttribute("resources", resources);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Access/update.jsp");
						dispatcher.forward(req, res);
						try{
							pm.makePersistent(elegido);
						}finally{
							pm.close();
						}
					}
				}*/
		//Saco el usuario actual
				com.google.appengine.api.users.User uGoogle= UserServiceFactory.getUserService().getCurrentUser();
				
				//saco listas de usuarios, roles, accesos y recursos
				PersistenceManager pm1 = PMF.get().getPersistenceManager();
				PersistenceManager pm2 = PMF.get().getPersistenceManager();
				PersistenceManager pm3 = PMF.get().getPersistenceManager();
				PersistenceManager pm4 = PMF.get().getPersistenceManager();
				
				String query1 = "select from " + Users.class.getName();
				@SuppressWarnings("unchecked")
				List<Users> users = (List<Users>) pm1.newQuery(query1).execute();
				
				String query2 = "select from " + Role.class.getName();
				@SuppressWarnings("unchecked")
				List<Role> roles = (List<Role>) pm2.newQuery(query2).execute();
				
				String query3 = "select from " + Access.class.getName();
				@SuppressWarnings("unchecked")
				List<Access> access = (List<Access>) pm3.newQuery(query3).execute();
				
				String query4 = "select from " + Resource.class.getName();
				@SuppressWarnings("unchecked")
				List<Resource> resources = (List<Resource>) pm4.newQuery(query4).execute();
				
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
						//Sacamos en la variable temporal el nombre del role de este acceso
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
									String names=req.getParameter("id");
											String idddd=null;
											Access elegido = null;
											for(int i=0;i<access.size();i++) {
												idddd=access.get(i).getId().toString();
												if(idddd.equals(names)) {
													elegido=access.get(i);
													req.setAttribute("access", elegido);
													req.setAttribute("roles", roles);
													req.setAttribute("resources", resources);
													RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Access/update.jsp");
													dispatcher.forward(req, res);
													try{
														pm3.makePersistent(elegido);
													}finally{
														pm3.close();
													}
												}
											}
									}
								//Si tiene todos los recursos solo de eventos
								else if (accesoRolePersonaEncontrada.equals(s3)) {
									RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/deny.jsp");
									dp.forward(req, res);
									
								}
								//Si solo puede ver eventos
								else if(accesoRolePersonaEncontrada.equals(s2)) {
									RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/deny.jsp");
									dp.forward(req, res);
								}
							} else {
								RequestDispatcher dp = getServletContext().getRequestDispatcher("/WEB-INF/Views/deny.jsp");
								dp.forward(req, res);
							}
				}
	
		}			
	}