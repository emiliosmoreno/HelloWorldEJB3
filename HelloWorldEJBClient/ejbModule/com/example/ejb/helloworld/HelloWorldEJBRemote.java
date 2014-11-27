package com.example.ejb.helloworld;

import javax.ejb.Remote;

import com.example.ejb.interfaces.IHelloWorld;

@Remote
public interface HelloWorldEJBRemote  extends IHelloWorld{

}
