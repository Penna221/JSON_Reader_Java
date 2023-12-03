package json;

import java.util.ArrayList;

public class StringValue extends DataType{

	public String value;
	public StringValue(String value) {
		super(DataType.STRING);
		this.value = value;
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return value;
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
