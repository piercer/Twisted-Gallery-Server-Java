package com.dz015.tg.application;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwistedGalleryServer {

    private static final URI BASE_URI = URI.create("http://localhost:8080/");
    public static final String ROOT_PATH = "/";

    public static void main(String[] args) {
        try {
            System.out.println("Twisted Gallery Server Starting");

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, new TGResourceConfig());

            System.out.println(String.format("Application started.\nTry out %s%s\nHit enter to stop it...", BASE_URI, ROOT_PATH));
            System.in.read();
            server.stop();
        }
        catch (IOException ex) {
            Logger.getLogger(TwistedGalleryServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
