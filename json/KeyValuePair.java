package json;

import java.util.ArrayList;

public class KeyValuePair {

	private String key;
	private DataType value;
	public KeyValuePair(String key, DataType value) {
		this.key = key;
		this.value = value;
	}
	/**
	 * This method prints the value inside this keyValuePair.
	 */
	public void print() {
		KeyValuePair obj = this;
		switch(obj.value.getType()) {
			case DataType.INTEGER: 
				int val = obj.value.getInteger();
				System.out.println(obj.key + " - " + val);
				break;
			case DataType.FLOAT:
				float val1 = obj.value.getFloat();
				System.out.println(obj.key + " - " + val1);
				break;
			case DataType.STRING:
				String val2 = obj.value.getString();
				System.out.println(obj.key + " - " + val2);
				break;
			case DataType.BOOLEAN:
				Boolean bo = obj.value.getBoolean();
				System.out.println(obj.key + " - " + bo);
				break;
			case DataType.NULL:
				System.out.println(obj.key + " - " + "NULL");
				break;
			case DataType.ARRAY:
				DataType[] arr = obj.value.getArray();
				System.out.println(obj.key + " - ARRAY size(" + arr.length+")");			
				break;
			case DataType.OBJECT:
				ArrayList<KeyValuePair> objs = obj.value.getObject();
				System.out.println(obj.key + " - OBJECT size(" + objs.size()+")");
				break;
			default:
				break;
		}
	
	}
	/**
	 * This function prints everything inside this keyValuePair recursively.
	 * Indent value given corresponds to dashes printed before the value. This kind of tells the layer the value is in. 
	 * 
	 * @param indent
	 */
	public void printAll(int indent) {
		KeyValuePair obj = this;
		String b = "";
		for(int i = 0; i < indent; i++) {
			b += "-";
		}
		switch(obj.value.getType()) {
			case DataType.INTEGER: 
				int val = obj.value.getInteger();
				System.out.println(b+obj.key + " - " + val);
				break;
			case DataType.FLOAT:
				float val1 = obj.value.getFloat();
				System.out.println(b+obj.key + " - " + val1);
				break;
			case DataType.STRING:
				String val2 = obj.value.getString();
				System.out.println(b+obj.key + " - " + val2);
				break;
			case DataType.BOOLEAN:
				Boolean bo = obj.value.getBoolean();
				System.out.println(b+obj.key + " - " + bo);
				break;
			case DataType.NULL:
				System.out.println(b+obj.key + " - " + "NULL");
				break;
			case DataType.ARRAY:
				DataType[] arr = obj.value.getArray();
				System.out.println(b+obj.key + " - ARRAY size(" + arr.length+")");
				for(DataType o: arr) {
					KeyValuePair kv1 = new KeyValuePair(obj.key,o);
					kv1.printAll(indent+1);
				}
				break;
			case DataType.OBJECT:
				ArrayList<KeyValuePair> objs = obj.value.getObject();
				System.out.println(b+obj.key + " - OBJECT size(" + objs.size()+")");
				for(KeyValuePair kv2 : objs) {
					kv2.printAll(indent+1);
				}
				break;
			default:
				break;
		}
	}
	public String getKey() {return key;}
	public int getInteger() {
		return value.getInteger();
	}
	public float getFloat() {
		return value.getFloat();
	}
	public boolean getBoolean() {
		return value.getBoolean();
	}
	public String getString() {
		return value.getString();
	}
	public DataType[] getArray() {
		return value.getArray();
	}
	public ArrayList<KeyValuePair> getObject() {
		return value.getObject();
	}
	public DataType getValue() {
		return value;
	}
	/**
	 * This function first checks if the current KeyValuePair is an object, if not, return value is null.
	 * If it is an object, the given "key" is used to find a child with that key and returned.
	 * If no child were found with given key, null is returned.
	 * 
	 * @param key
	 * @return
	 */
	public KeyValuePair findChild(String key) {
		if(value.getType()==DataType.OBJECT) {
			for(KeyValuePair kv : value.getObject()) {
				if(kv.getKey().equals(key)) {
					return kv;
				}
			}
		}
		return null;
	}
}
