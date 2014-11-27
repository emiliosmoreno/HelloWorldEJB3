package com.example.ejb.clients;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.example.ejb.helloworld.HelloWorldEJBRemote;

//app-name/module-name/bean-name!bean-interface
//
//where,
//
//app-name = the name of the .ear (without the .ear suffix) or the application name configured via application.xml deployment descriptor. If the application isn't packaged in a .ear then there will be no app-name part to the JNDI string.
//module-name = the name of the .jar or .war (without the .jar/.war suffix) in which the bean is deployed or the module-name configured in web.xml/ejb-jar.xml of the deployment. The module name is mandatory part in the JNDI string.
//bean-name = the name of the bean which by default is the simple name of the bean implementation class. Of course it can be overridden either by using the "name" attribute of the bean definining annotation (@Stateless(name="blah") in this case) or even the ejb-jar.xml deployment descriptor.
//bean-interface = the fully qualified class name of the interface being exposed by the bean.
//
//So in our example above, l
public class HelloWorldEJBClient {

	
	public static void main(String[] args) throws Exception {
		testRemoteEJB();

	}
	 
    private static void testRemoteEJB() throws NamingException {
 
        final HelloWorldEJBRemote ejb = lookupRemote();
        String s = ejb.sayHello();
        System.out.println(s);
        
    }
	 

		/*
		 * 
		java:global/HelloWorldEAR/HelloWorldEJB/HelloWorldBean!com.example.ejb.helloworld.HelloWorldEJBLocal
		java:app/HelloWorldEJB/HelloWorldBean!com.example.ejb.helloworld.HelloWorldEJBLocal
		java:module/HelloWorldBean!com.example.ejb.helloworld.HelloWorldEJBLocal
		java:global/HelloWorldEAR/HelloWorldEJB/HelloWorldBean!com.example.ejb.helloworld.HelloWorldEJBRemote
		java:app/HelloWorldEJB/HelloWorldBean!com.example.ejb.helloworld.HelloWorldEJBRemote
		java:module/HelloWorldBean!com.example.ejb.helloworld.HelloWorldEJBRemote
		java:jboss/exported/HelloWorldEAR/HelloWorldEJB/HelloWorldBean!com.example.ejb.helloworld.HelloWorldEJBRemote
		
		*/
//		String pathJNDI="java:module/HelloWorldBean!com.example.ejb.helloworld.HelloWorldEJBRemote";
	    
	    
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
//			String pathJNDI = "java:/HelloWorldEAR/HelloWorldEJB/HelloWorldBean!com.example.ejb.helloworld.HelloWorldEJBRemote";
			
			miEJB = (HelloWorldEJBRemote) context.lookup(pathJNDI);
//			System.out.println(miEJB.sayHello());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return miEJB;

	}
	    
	    
}
