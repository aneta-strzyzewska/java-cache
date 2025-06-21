package com.javacache.main;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class Main {

	private final static String ADDRESS = "http://localhost:8080/";

	public static void main(String[] args) {
		JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();
		serverFactory.setResourceProvider(new SingletonResourceProvider(new RandomNameController()));
		serverFactory.setAddress(ADDRESS);
		Server server = serverFactory.create();

		if(server.isStarted()) {
			System.out.println("Server listening on " + ADDRESS);
		}
	}

}
