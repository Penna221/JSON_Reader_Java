
# What
This is a JSON Reader implementation in **Java**. This can be used to **read** and **write** .json files. 
Data will be stored in **KeyValuePair** class.
***
# How to use
Use this library with following instructions:

First you create a new JSON object with _file_ as parameter. 
```
File file = new File("path\\to\\file.json");
JSON json = new JSON(file);
```
**The Constructor** reads the file automatically, but you can use the json.readFile(boolean) function to read it again. The boolean value given will determine if printing to console is desired.
`json.readFile(true);`

Alternatively user can create a **empty** JSON object, so that the keyValuePair can be set manually. This can be useful for writing own JSON Files. More down below...
`JSON empty = new JSON();`

Then you parse the file with the function: _parse(String key)_. It returns a **KeyValuePair** object. This holds the whole json object. You can give the key for that object.
`KeyValuePair kv = json.parse("JSON");`

Intented use of JSON: Only one file for each JSON object.

The **KeyValuePair** class is the **most important**. It holds everything there is for that json object/value.

KeyValuePair class has the following functions:

| Function      | Description |
| ----------- | ----------- |
| print()      | Prints key and value of KeyValuePair to console.|
| printAll()   | Prints all key and value pairs, arrays, objects recursively to console.|
| getKey()   | Returns the key as String. |
| getInteger()   | Returns the value as Integer.  |
| getFloat()   | Retunrs the value as Float. |
| getBoolean()   | Returns the value as boolean|
| getString()   | Returns the value as String|
| getArray()   | Returns a DataType array. |
| getObject()   | Returns the Object as ArrayList\<KeyValuePair>|
| getValue()   | Returns the DataType.  |
| findChild(String key)   | Returns KeyValuePair if found in objects children with given key.    |

If you already know what value the keyValuePair holds, you can get the value straight with the getInteger() -> getObject() methods.

**findChild(String key)** method can be used to find the child of the keyValuePair object **fast** without more code. You need to give string parameter for that function. It first checks if the current KeyValuePair is an object. if so, it tries to find a child keyvalue with the given key and returns it. IF NOT, the function returns null. So please use this method with checks like:
```
KeyValue val = json.findChild("key1");
if(val !=null) {
    //do something with the val.
}else {
    System.out.println("child not found");
}
```
#### Writing a json
Writing a json can be done by first Creating an empty json object, Then creating and populating the KeyValuePair with data and finally writing it with the function.
```
//Create json
JSON json = new JSON();

//Create data for object
StringValue data = new StringValue("This is data");
KeyValuePair kv = new KeyValuePair("Key",data);

//Create ArrayList of KeyValuePairs. This is needed for the ObjectValue.
ArrayList<KeyValuePair> keyValuePairs = new ArrayList<KeyValuePair>();
keyValuePairs.add(kv);

//Create ObjectValue with the keyValuePairs.
ObjectValue objectContainer = new ObjectValue(KeyValuePairs);

//Create the root object of json and set it to json object.
KeyValuePair object = new KeyValue("JSON",objectContainer);
json.setKeyValuePair(object);

//Now user can write it to file.
File output = new File("Desired\\output\\file.json");
json.writeFile(output);
```


# What i learned
This json reader implementation was done for the pure reason of learning and understanding how json works in its base layer. So i had to learn what data types it stores, how they are written in .json files,  how to parse them with java, and how to recursively parse array and/or object data.

While creating this simple json reader implementation, i **struggled** with recursively reading Arrays and Objects. Eventually i had to **reconstruct** my way of coding. I could not do everything in one master function that works always, so i had to chop everything to smaller pieces. That enabled a way simpler method that can be understood better.

I learned 
- more about JSON structure and values it holds,
- Recursive methods

I suggest everyone new to coding to try this as an exercise.
***
