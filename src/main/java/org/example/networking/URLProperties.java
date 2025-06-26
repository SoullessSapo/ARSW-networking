package org.example.networking;

import java.net.*;

public class URLProperties {
    public static void main(String[] args) throws Exception {
        URI uri = new URI("http://www.google.com:80/search?q=java#top");
        URL url = uri.toURL();
        System.out.println("Protocol: " + url.getProtocol());
        System.out.println("Authority: " + url.getAuthority());
        System.out.println("Host: " + url.getHost());
        System.out.println("Port: " + url.getPort());
        System.out.println("Path: " + url.getPath());
        System.out.println("Query: " + url.getQuery());
        System.out.println("File: " + url.getFile());
        System.out.println("Ref: " + url.getRef());
    }
}
