package test;

import java.io.Serializable;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;

/**
 * 
 * @author Helmuth Brunner
 * @version Nov 18, 2014
 *
 * Current project: ldap
 */

public class MyObject implements Referenceable {

	String name;

	public MyObject(String name) {
		this.name= name;
	}

	@Override
	public Reference getReference() throws NamingException {
		return new Reference(
			    MyObject.class.getName(),
			    new StringRefAddr("name", name),
			    MyObjectFactory.class.getName(),
			    null);          // factory location
	}
	
	public String toString() {
		return name;
	}

}
