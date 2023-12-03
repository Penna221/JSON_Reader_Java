package json;
/**
* @author  Juho Pennanen
* @version 1.0
* @since   2023-12-03
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JSON {
	private String raw = "";
	private String noWhiteSpace = "";
	public File file;
	public KeyValuePair kv;

	/**
	 * Give a .json File as parameter of the constructor.
	 * 
	 * @param f
	 */
	public JSON(File f) {
		this.file = f;
		readFile(false);
	}
	/**
	 * Empty Constructor for creating own JSON and later saving it.
	 */
	public JSON(){}
	/**
	 * Reads the file given to the JSON json = new JSON(File f) object
	 * user can give the boolean parameter if the console output is desired.
	 * This method is separated from the constructor so that
	 * @param print
	 */
	public void readFile(boolean print) {
		raw = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			if(print) {
				System.out.println("--------------------------------");								
			}
			while((line = reader.readLine())!=null) {
				if(print) {
					System.out.println(line);
				}
				raw += line;
			}
			if(print) {
				System.out.println("--------------------------------");				
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * User can give String key for the KeyValuePair.
	 * This method can and should only be used after the file was read with ReadFile() function.
	 * @param key
	 * @return
	 */
	public KeyValuePair parse(String key) {
		
		noWhiteSpace = removeWhiteSpace(raw);
		String obj = wormOne('{','}',noWhiteSpace);
		if(obj==null) {
			System.out.println("Something went wrong :(");
			return null;
		}else {
			kv = new KeyValuePair(key,parseObject(obj));
			return kv;
		}
	}
	/**
	 * Can be used to get the KeyValuePair from the JSON object.
	 * This function will return something other than null only after using parse() function.
	 * 
	 * @return
	 */
	public KeyValuePair getKeyValue() {
		return kv;
	}
	
	/**
	 * This one will find only one enclosed "Whole" object and returns it.
	 * @param openChar
	 * @param closeChar
	 * @param s
	 * @return
	 */
	private String wormOne(char openChar, char closeChar,String s) {
		char[] worm = s.toCharArray();
		int start = 0;
		int end = worm.length;
		
		
		int firstOpenIndex = 0;
		int closingIndex = 0;
		//Counters   curlyOpen -> object
		int cOpen = 0;
		int cClose = 0;
		for(int c = start; c < end; c++) {
			char ch = worm[c];
			if(ch==openChar) {
				if(cOpen==0) {
					firstOpenIndex = c;
				}
				cOpen++;
			}
			if(ch == closeChar) {
				cClose++;
			}
			if(cOpen >0 && cOpen==cClose) {
				//Object closing.
				closingIndex = c+1;
				start = c+1;
				break;
			}
		}
		if(closingIndex != 0) {
			String t = s.substring(firstOpenIndex, closingIndex);
			return t;			
		}else {
			return null;
		}
	}
	/**
	 * Go through the text s, check the openchar and closeChar, give the ending index of the object.
	 * @param openChar
	 * @param closeChar
	 * @param s
	 * @return
	 */
	private int getObjectTail(char openChar, char closeChar, String s) {
		char[] worm = s.toCharArray();
		int start = 0;
		int end = worm.length;
		
		int closingIndex = 0;
		//Counters   curlyOpen -> object
		int cOpen = 0;
		int cClose = 0;
		for(int c = start; c < end; c++) {
			char ch = worm[c];
			if(ch==openChar) {
				
				cOpen++;
			}
			if(ch == closeChar) {
				cClose++;
			}
			if(cOpen >0 && cOpen==cClose) {
				//Object closing.
				closingIndex = c+1;
				start = c+1;
				break;
			}
		}
		if(closingIndex != 0) {
			
			return closingIndex;			
		}else {
			return -1;
		}
	}
	/**
	 * Removes all tabs(\t) and new lines(\n) from the text.
	 * @param s
	 * @return
	 */
	private String removeWhiteSpace(String s) {
		s = s.replaceAll("\t", "");
		s = s.replaceAll("\n", "");
		return s;
		//System.out.println(noWhiteSpace);
		
	}
	/**
	 * Removes the first and last char of the string and returns it back.
	 * @param s
	 * @return
	 */
	private String removeSkin(String s) {
		String ss = s.substring(1,s.length()-1);
		return ss;
	}
	/**
	 * Parses the object given to it.
	 * @param obj
	 * @return
	 */
	private ObjectValue parseObject(String obj) {
		ArrayList<KeyValuePair> dataArray = new ArrayList<KeyValuePair>();
		String s = removeSkin(obj);
		ArrayList<String> splitted = splitWithCommas(s);
		for(int i = 0; i < splitted.size(); i++) {
			
			//First get the keys, then worry about what Datatype the value is.
			String key2 = parseKey(splitted.get(i));
			//System.out.println(key);
			
			int beginIndex = splitted.get(i).indexOf(":")+1;
			String value = splitted.get(i).substring(beginIndex);
			value = value.trim();
			
			DataType data = parseValue(value);
			KeyValuePair kv = new KeyValuePair(key2,data);
			dataArray.add(kv);
		}
		
		return new ObjectValue(dataArray);
	}
	/**
	 * Takes the value from the text and returns it as DataType
	 * @param value
	 * @return
	 */
	private DataType parseValue(String value) {
		
		//System.out.println("VALUE:  "+ value);
		if(value.startsWith("\"")) {
			//STRING
			//System.out.println("TYPE: STRING");
			value = removeSkin(value);
			return new StringValue(value);
		}else if(value.startsWith("[")) {
			//ARRAY
			//System.out.println("TYPE: ARRAY");
			return parseArray(value);
		}else if(value.startsWith("{")) {
			//OBJECT
			//System.out.println("TYPE: OBJECT");
			return parseObject(value);
		}else if(value.startsWith("true")) {
			//BOOLEAN
			//System.out.println("TYPE: BOOLEAN");
			return new BooleanValue(true);
		}else if(value.startsWith("false")) {
			//BOOLEAN
			//System.out.println("TYPE: BOOLEAN");
			return new BooleanValue(false);
		}else if(value.startsWith("null")) {
			//NULL
			//System.out.println("TYPE: NULL");
			return new EmptyValue();
		}
		else {
			//NUMBER
			//System.out.println("TYPE: NUMBER");
			if(value.contains(".")) {
				//System.out.println("MORE SPECIFIC TYPE: FLOAT");
				float f = Float.parseFloat(value);
				return new FloatValue(f);
			}else {
				//System.out.println("MORE SPECIFIC TYPE: INTEGER");
				int i = Integer.parseInt(value);
				return new NumberValue(i);
			}
		}
	}
	/**
	 * Parses an array.
	 * @param array
	 * @return
	 */
	private ArrayValue parseArray(String array) {
		String s = removeSkin(array);
		DataType[] values;
		
		ArrayList<String> splitted = splitWithCommas(s);
		int size = splitted.size();
		values = new DataType[size];
		for(int i = 0; i < splitted.size(); i++) {
			String value = splitted.get(i);
			value = value.trim();
			DataType data = parseValue(value);
			values[i] = data;
		}
		return new ArrayValue(values);
	}
	/**
	 * Parses the key of the string.
	 * @param s
	 * @return
	 */
	private String parseKey(String s) {
		String[] split = s.split(":");
		String key = split[0];
		key = removeSkin(key);
		
		return key;
	}
	
	/**
	 * Splits the text s into arraylist of strings.
	 * This checks the text if it has objects or arrays inside 
	 * and capsulates them inside of one string in the arraylist. 
	 * @param s
	 * @return
	 */
	private ArrayList<String> splitWithCommas(String s) {
		//System.out.println(s);
		ArrayList<String> splits = new ArrayList<String>();
		ArrayList<Integer> markers = new ArrayList<Integer>();
		
		char[] charArray = s.toCharArray();
		markers.add(0);
		
		for(int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if(c == ',') {
				markers.add(i);
			}	
			if(c == '{') {
				String x = s.substring(i);
				i+= getObjectTail('{','}',x)-1;
				continue;
			}
			else if(c == '[') {
				String x = s.substring(i);
				i+=getObjectTail('[',']',x)-1;
				continue;
			}
			
		}
		for(int i = 0; i < markers.size(); i++) {
			
			int start = markers.get(i);
			int end = 0;
			if(i < markers.size()-1) {
				end = markers.get(i+1);
			}else {
				end = s.length();
			}
			String sub = s.substring(start,end);
			if(sub.startsWith(",")) {
				sub = sub.substring(1);
			}
			splits.add(sub);
		}
		
		return splits;
	}
	
	
	/**
	 * This function is used to write a KeyValuePair stored inside the JSON object into a file in JSON format.
	 * @param f
	 * @throws Exception
	 */
	public void writeFile(File f) throws Exception{
		if(kv == null) {
			throw new Exception("KeyValuePair is null. There is nothing to write.");
		}
		BufferedWriter w = new BufferedWriter(new FileWriter(f));
		
		String text = getDataFromKeyValue(kv);
		w.write(text);
		w.close();
	}
	public void setKeyValuePair(KeyValuePair kv){
		this.kv = kv;
	}


	private String getDataFromKeyValue(KeyValuePair kv) {
		String toReturn = "";
		String key = kv.getKey();
		int type = kv.getValue().getType();
		if(type==DataType.OBJECT) {
			
			if(!key.equals("")) {
				toReturn += "\""+key + "\":";				
			}
			
			toReturn += "{";
			for(KeyValuePair child : kv.getObject()) {
				String s = getDataFromKeyValue(child);
				toReturn += s + ",";
			}
			//Remove last comma ( , )
			toReturn = toReturn.substring(0, toReturn.length()-1);
			toReturn += "}";
		}
		else if(type==DataType.ARRAY) {
			if(!key.equals("")) {
				toReturn += "\""+key + "\":";				
			}
			toReturn += "[";
			DataType[] array = kv.getArray();
			for(DataType child : array) {
				if(child.getType()== DataType.ARRAY || child.getType()==DataType.OBJECT ) {
					
					String val = getDataFromKeyValue(new KeyValuePair("",child));
					toReturn += val;
				}else {
					String s = getDataFromDataType(child);	
					toReturn += s;
				}
				toReturn +=",";
			}
			toReturn = toReturn.substring(0, toReturn.length()-1);
			toReturn += "]";
		}else {
			toReturn += "\""+key + "\":";
			String s = getDataFromDataType(kv.getValue());
			toReturn += s;
		}
		
		
		return toReturn;
	}
	private String getDataFromDataType(DataType d) {
		int type = d.getType();
		
		switch (type) {
		case DataType.INTEGER:
			return String.valueOf(d.getInteger());
		case DataType.BOOLEAN:
			return String.valueOf(d.getBoolean());
		case DataType.FLOAT:
			return String.valueOf(d.getFloat());
		case DataType.STRING:
			return "\""+d.getString()+"\"";			
		case DataType.NULL:
			return d.getNull();
		default:
			return "null";
		}
	}
}
