package com.stream.video;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {

	public static final String BASE_URI = "http://0.0.0.0:8081/api/";

	public static HttpServer startServer() throws Exception {
		ResourceConfig rc = new ResourceConfig().packages("com.stream.video.resource");
		rc.register(LoggingFeature.class);
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}

	public static void main(String[] args) throws Exception {
		final HttpServer server = startServer();
		System.out.println(String
				.format("Video stream Service started with base URI at" + 
						"%s\nHit enter to stop it...", BASE_URI));
		System.in.read();
		server.shutdown();
	}
}
