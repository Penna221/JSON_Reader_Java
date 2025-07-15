package json;

import java.util.ArrayList;

public class ArrayValue extends DataType {
	public DataType[] array;
	public ArrayValue(DataType[] values) {
		super(DataType.ARRAY);
		this.array = values;
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
		return null;
	}
	@Override
	public DataType[] getArray() {
		// TODO Auto-generated method stub
		return array;
	}
	@Override
	public ArrayList<KeyValuePair> getObject() {
		// TODO Auto-generated method stub
		return null;
	}
}
