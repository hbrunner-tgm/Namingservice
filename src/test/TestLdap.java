package test;

import java.util.Hashtable;

import javax.naming.*;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * 
 * @author Helmuth Brunner, helmuth.brunner@student.tgm.ac.at
 * @version Nov 18, 2014
 *
 * Current project: ldap
 */
public class TestLdap {

	public static void main(String[] args) throws NamingException {

		//		Hashtable<Object, String> env = new Hashtable<Object, String>();
		//		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		//		env.put(Context.PROVIDER_URL, "ldap://10.0.105.234:389");
		//		env.put(Context.SECURITY_AUTHENTICATION, "none");
		//		env.put(Context.SECURITY_PRINCIPAL,"cn=Directory Manager");	 // specify the username
		//		env.put(Context.SECURITY_PRINCIPAL,"uid=admin,ou=system");	 // specify the username
		//		env.put(Context.SECURITY_CREDENTIALS,"admin");           	 // specify the password
		//
		//		DirContext ctx = new InitialDirContext(env);
		//
		//		MyObject myObject= new MyObject("Hello");
		//
		//		ctx.bind("otherobject", myObject);
		//
		//		MyObject erg= (MyObject) ctx.lookup("otherobject");
		//
		//		System.out.println(erg.toString());

		//TODO for home Test the code
		Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:1389/dc=example,dc=com");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager");
		env.put(Context.SECURITY_CREDENTIALS, "admin");

		DirContext ctx = new InitialDirContext(env);

		MyObject myObject= new MyObject("Hello");

		ctx.bind("javaObject", myObject);

		MyObject erg= (MyObject) ctx.lookup("javaObject");

		System.out.println(erg.toString());


		//		SearchControls searchControls = new SearchControls();
		//
		//		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		//		searchControls.setCountLimit(10);
		//
		//		NamingEnumeration<SearchResult> namingEnumeration = ctx.search("", "(uid=*)", new Object[]{}, searchControls);
		//		
		//		while (namingEnumeration.hasMore()) {
		//			SearchResult sr = namingEnumeration.next();
		//			System.out.println("DN: " + sr.getName());
		//			System.out.println(sr.getAttributes().get("uid"));
		//			System.out.println("Password:" + new String((byte[]) sr.getAttributes().get("userPassword").get()));
		//		}

		ctx.close();
	}
}


/*
		// Identify service provider to use
		Hashtable env = new Hashtable(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:1389/dc=example,dc=com");

		try {

			// Create the initial directory context
			DirContext ctx = new InitialDirContext(env);

			// Ask for all attributes of the object 
			Attributes attrs = ctx.getAttributes("cn=Ted Geisel, ou=People");

			// Find the surname ("sn") and print it
			System.out.println("sn: " + attrs.get("sn").get());

			// Close the context when we're done
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
 */
