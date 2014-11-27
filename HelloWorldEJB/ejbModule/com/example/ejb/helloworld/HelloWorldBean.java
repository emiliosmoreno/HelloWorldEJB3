package com.example.ejb.helloworld;

import javax.ejb.Stateless;



/**
 * Session Bean implementation class HelloWorldEJB
 */
@Stateless(mappedName = "helloWorld")
public class HelloWorldBean implements HelloWorldEJBRemote, HelloWorldEJBLocal {

    /**
     * Default constructor. 
     */
    public HelloWorldBean() {
    }

	public String sayHello() {
		return "Hello World !!!";
	}

}
