package test;

import java.util.Hashtable;

import javax.naming.*;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
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

		try {
			ctx.bind("cn=myobject, o=JavaObjects", myObject);
		}catch (NameAlreadyBoundException nabe) {
			System.out.println("Object allready bounded");
		}

		MyObject erg= (MyObject) ctx.lookup("cn=myobject,o=JavaObjects");
		
		System.out.println(ctx.lookup("cn=myobject, o=JavaObjects"));
		System.out.println(erg);

		ctx.removeFromEnvironment("cn=myobject");
		
		SearchControls searchControls = new SearchControls();

		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		searchControls.setCountLimit(10);

		NamingEnumeration<SearchResult> namingEnumeration = ctx.search("cn=hb,o=Services", "(cn=*)", new Object[]{}, searchControls);

		while (namingEnumeration.hasMore()) {
			SearchResult sr = namingEnumeration.next();

			System.out.println("GivenName: " + (String) sr.getAttributes().get("givenName").get() );
			System.out.println("cn: " + (String) sr.getAttributes().get("cn").get() );
			System.out.println("uid: " + (String) sr.getAttributes().get("uid").get() );
			System.out.println("mail: " + (String) sr.getAttributes().get("mail").get() );
			System.out.println("telephoneNumber: " + (String) sr.getAttributes().get("telephoneNumber").get() );
			System.out.println("facsimileTelephoneNumber: " + (String) sr.getAttributes().get("facsimileTelephoneNumber").get() );
			System.out.println("Password:" + new String((byte[]) sr.getAttributes().get("userPassword").get()));
		}

		/**
		 * 
		 * givenName: Ari sn: Ayvazyan cn: aa uid: aa userPassword: ******** mail: aari@student.tgm.ac.at telephoneNumber: +43 0664 7239238 facsimileTelephoneNumber: +23 458425 
		 * 
		 */
		
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
