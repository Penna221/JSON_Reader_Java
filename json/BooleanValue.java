package json;

import java.util.ArrayList;

public class BooleanValue extends DataType {
	public boolean value;
	public BooleanValue(boolean value) {
		super(DataType.BOOLEAN);
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
		return value;
	}
	@Override
	public String getNull() {
		// TODO Auto-generated method stub
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
