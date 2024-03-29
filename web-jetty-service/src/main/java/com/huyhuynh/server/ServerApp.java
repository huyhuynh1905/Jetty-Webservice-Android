package com.huyhuynh.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.servlet.ServletContainer;

import com.huyhuynh.service.ServiceAndroid;

public class ServerApp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Server theServer = new Server(7070);
		
		HandlerCollection hanCollection = new HandlerCollection();
		WebAppContext webapp = new WebAppContext();
		webapp.setResourceBase("src/main/webapp");
		webapp.setContextPath("/jetty");
		webapp.setDefaultsDescriptor("src/main/webdefault/webdefault.xml");
		hanCollection.addHandler(webapp);
		
		//theServer.setHandler(hanCollection);
		theServer.setHandler(getJerseyHandler());

		theServer.start();
		theServer.join();

	}

	private static Handler getJerseyHandler() {
		ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		handler.setContextPath("/jetty");
		ServletHolder holder = handler.addServlet(ServletContainer.class, "/*");
		holder.setInitParameter("jersey.config.server.provider.classnames", ServiceAndroid.class.getCanonicalName());
		
		return handler;
	}

}
