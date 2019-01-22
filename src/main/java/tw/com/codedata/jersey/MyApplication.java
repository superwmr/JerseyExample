package tw.com.codedata.jersey;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import socket_service.SocketServer;
import test.Config;

@ApplicationPath("helloRestful")
public class MyApplication extends ResourceConfig {
	//
	public MyApplication() {
		packages("tw.com.codedata.jersey");

		(new SocketServer()).start();

		Utils.printUserMenu();

		register(CustomLoggingFilter.class);
	}
}