package com.javacache.main;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class Main {

	public static void main(String[] args) {
		JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();
		serverFactory.setResourceProvider(new SingletonResourceProvider(new RandomNameController()));
		serverFactory.setAddress("http://localhost:8080/");
		serverFactory.create();
	}

}
