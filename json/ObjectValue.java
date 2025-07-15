package json;

import java.util.ArrayList;

public class ObjectValue extends DataType {
	public ArrayList<KeyValuePair> obj;
	public ObjectValue(ArrayList<KeyValuePair> data) {
		super(DataType.OBJECT);
		this.obj = data;
	}
	@Override
	public int getInteger() {
		return 0;
	}
	@Override
	public float getFloat() {
		return 0;
	}
	@Override
	public boolean getBoolean() {
		return false;
	}
	@Override
	public String getNull() {
		return null;
	}
	@Override
	public String getString() {
		return null;
	}
	@Override
	public DataType[] getArray() {
		return null;
	}
	@Override
	public ArrayList<KeyValuePair> getObject() {
		return obj;
	}
	public DataType findObjectWithID(String id) {
		for (KeyValuePair pair : obj) {
			if (pair.getKey().equals(id)) {
				return pair.getValue();
			}
		}
		return null; // Return null if no object with the given ID is found
	}
	

}
