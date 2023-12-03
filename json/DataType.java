package json;

import java.util.ArrayList;

public abstract class DataType {
	private int type;
	public static final int STRING = 0;
	public static final int INTEGER = 1;
	public static final int FLOAT = 2;
	public static final int BOOLEAN = 3;
	public static final int NULL = 4;
	public static final int ARRAY = 5;
	public static final int OBJECT = 6;
	
	public DataType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}
	public abstract int getInteger();
	public abstract float getFloat();
	public abstract boolean getBoolean();
	public abstract String getNull();
	public abstract String getString();
	public abstract DataType[] getArray();
	public abstract ArrayList<KeyValuePair> getObject();
}
