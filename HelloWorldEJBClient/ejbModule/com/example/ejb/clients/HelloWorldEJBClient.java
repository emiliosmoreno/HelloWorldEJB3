package com.example.ejb.clients;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.example.ejb.helloworld.HelloWorldEJBRemote;

public class HelloWorldEJBClient {

	
	public static void main(String[] args) throws Exception {
		testRemoteEJB();

	}
	 
    private static void testRemoteEJB() throws NamingException {
 
        final HelloWorldEJBRemote ejb = lookupRemote();
        String s = ejb.sayHello();
        System.out.println(s);
        
    }
	 
	    
	private static HelloWorldEJBRemote lookupRemote() throws NamingException {

		HelloWorldEJBRemote miEJB = null;
		try {
			Hashtable propiedades = new Hashtable();
			
			propiedades.put(Context.INITIAL_CONTEXT_FACTORY,"org.jboss.naming.remote.client.InitialContextFactory");
			propiedades.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
			propiedades.put(Context.SECURITY_PRINCIPAL, "appuser"); // username
			propiedades.put(Context.SECURITY_CREDENTIALS, "pwdappuser"); // password
			propiedades.put("jboss.naming.client.ejb.context", true);
			
			Context context = new InitialContext(propiedades);

			String pathJNDI = "HelloWorldEAR/HelloWorldEJB/HelloWorldBean!com.example.ejb.helloworld.HelloWorldEJBRemote";
			
			miEJB = (HelloWorldEJBRemote) context.lookup(pathJNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return miEJB;

	}
	    
	    
}
