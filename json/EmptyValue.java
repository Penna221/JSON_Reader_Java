package json;

import java.util.ArrayList;

public class EmptyValue extends DataType {

	public String value;
	public EmptyValue() {
		super(DataType.NULL);
		this.value = null;
	}
	@Override
	public int getInteger() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getFloat() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean getBoolean() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String getNull() {
		
		return null;
	}
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DataType[] getArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<KeyValuePair> getObject() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
