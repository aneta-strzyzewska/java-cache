package com.javacache.app;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private final static String ADDRESS = "http://localhost:8080/";

	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(Main.class);

		JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();
		serverFactory.setResourceProvider(new SingletonResourceProvider(new RandomNameController()));
		serverFactory.setAddress(ADDRESS);
		Server server = serverFactory.create();

		if(server.isStarted()) {
			log.info("Server listening on " + ADDRESS);
		}
	}

}
