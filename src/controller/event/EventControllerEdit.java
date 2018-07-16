package controller.event;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.PMF;
import model.entity.Event;
import javax.jdo.PersistenceManager;

@SuppressWarnings("serial")
public class EventControllerEdit extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		String id = req.getParameter("eventId2");
		String query = "select from " + Event.class.getName();
		@SuppressWarnings("unchecked")
		List<Event> events = (List<Event>) pm.newQuery(query).execute();
		boolean st1;
		
		String x= "sin definir";
		String y= "sin definir";
		String hora=req.getParameter("houra");
		String hora2=req.getParameter("hourDate");
		String day=req.getParameter("day");
		String month=req.getParameter("month");
		String year=req.getParameter("year");
		y=day+"/"+month+"/"+year;
		x=hora+" "+hora2;
		String s;
		String s2;
		boolean s3;
		String a=null;
		String a2=null;
		String iddd=null;
		for(int i=0;i<events.size();i++){
			iddd=events.get(i).getId().toString();
			if(iddd.equals(id)) {
				a=events.get(i).getSchedule();
				a2=events.get(i).getDate();
				String dd = a2.substring(0,a2.indexOf("/"));
				String mm = a2.substring(a2.indexOf("/")+1, a2.lastIndexOf("/"));
				String yy = a2.substring(a2.lastIndexOf("/")+1, a2.length());
				st1=events.get(i).getStatus();
				events.get(i).setName(req.getParameter("name"));
				events.get(i).setLocation(req.getParameter("location"));
				events.get(i).setCapacity(Integer.parseInt(req.getParameter("capacity")));
				//casos de la hora
				if(!req.getParameter("houra").equals("none") && !req.getParameter("hourDate").equals("none")){
					events.get(i).setSchedule(x);
				}else if(req.getParameter("houra").equals("none") && req.getParameter("hourDate").equals("none")) {
					s = "No hago nada xd";
				}else if(!req.getParameter("houra").equals("none") && req.getParameter("hourDate").equals("none")) {
					String hd = a.substring(a.indexOf(" ")+1,a.length());
					x=hora+" "+hd;
					events.get(i).setSchedule(x);
				}else if(req.getParameter("houra").equals("none") && !req.getParameter("hourDate").equals("none")) {
					String hd = a.substring(0,a.indexOf(" "));
					x=hd+" "+hora2;
					events.get(i).setSchedule(x);
				}
				if(!req.getParameter("day").equals("none") && !req.getParameter("month").equals("none") && !req.getParameter("year").equals("none")) {
					events.get(i).setDate(y);
				}else if(req.getParameter("day").equals("none") && req.getParameter("month").equals("none") && req.getParameter("year").equals("none")) {
					s2 = "no hago nada x2 xd";
				}else if(req.getParameter("day").equals("none") && !req.getParameter("month").equals("none") && !req.getParameter("year").equals("none")) {
					y=dd+"/"+month+"/"+year;
					events.get(i).setDate(y);
				}else if(!req.getParameter("day").equals("none") && req.getParameter("month").equals("none") &&! req.getParameter("year").equals("none")) {
					y=day+"/"+mm+"/"+year;
					events.get(i).setDate(y);
				}else if(!req.getParameter("day").equals("none") && !req.getParameter("month").equals("none") && req.getParameter("year").equals("none")) {
					y=day+"/"+month+"/"+yy;
					events.get(i).setDate(y);
				}else if(req.getParameter("day").equals("none") && req.getParameter("month").equals("none") && !req.getParameter("year").equals("none")) {
					y=dd+"/"+mm+"/"+year;
					events.get(i).setDate(y);
				}else if(req.getParameter("day").equals("none") && !req.getParameter("month").equals("none") && req.getParameter("year").equals("none")) {
					y=dd+"/"+month+"/"+yy;
					events.get(i).setDate(y);
				}else if(!req.getParameter("day").equals("none") && req.getParameter("month").equals("none") && req.getParameter("year").equals("none")) {
					y=day+"/"+mm+"/"+yy;
					events.get(i).setDate(y);
				}
				//casos del estado 
				if(req.getParameter("eventStatus").equals("true")) {
					s3=true;
					events.get(i).setStatus(s3);
				}else if(req.getParameter("eventStatus").equals("false")) {
					s3=false;
					events.get(i).setStatus(s3);
				}else if(req.getParameter("eventStatus").equals("none")) {
					s3=st1;
					events.get(i).setStatus(s3);
				}
			}
		}
		res.sendRedirect("/events");
	}	
}