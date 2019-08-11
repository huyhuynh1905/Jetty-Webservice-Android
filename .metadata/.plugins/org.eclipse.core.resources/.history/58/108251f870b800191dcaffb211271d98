package com.huyhuynh.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

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
		
		theServer.setHandler(hanCollection);
		theServer.start();
		theServer.join();

	}

}
