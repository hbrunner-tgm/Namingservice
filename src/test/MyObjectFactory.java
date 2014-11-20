package test;

import javax.naming.*;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 * This is an object factory that when given a reference for a Fruit
 * object, will create an instance of the corresponding Fruit.
 */
public class MyObjectFactory implements ObjectFactory {

	public MyObjectFactory() {
	}

	public Object getObjectInstance(Object obj, Name name, Context ctx, Hashtable env) throws Exception {
		
		if (obj instanceof Reference) {
			Reference ref = (Reference)obj;
			if (ref.getClassName().equals(MyObject.class.getName())) {
				RefAddr addr = ref.get("fruit");
				if (addr != null) {
					return new MyObject((String)addr.getContent());
				}
			}
		}
		
		return null;
	}
}
