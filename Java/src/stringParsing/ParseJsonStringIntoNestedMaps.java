package stringParsing;

import java.util.HashMap;
import java.util.Map;

public class ParseJsonStringIntoNestedMaps {
    /*
    You are given a string representation of a nested JSON object. Each JSON object is represented by key-value pairs enclosed within curly braces {}. Keys and values are separated by colons :, and distinct entries in an object are separated by commas ,. A value in a JSON object can be a string, a number, or another nested JSON object. For simplicity, we will not consider arrays or null values in this task.

For example, the string "{\"key1\": \"value1\", \"key2\": {\"key3\": \"value3\", \"key4\": \"value4\"}, \"key5\": \"value5\"}" represents the following JSON object:

{
  "key1": "value1",
  "key2": {
    "key3": "value3",
    "key4": "value4"
  },
  "key5": "value5"
}
Your task is to transform the given string into a nested Java map structure and then update a specific key-value pair within the map. You should parse the JSON string into a Java nested map and then update the value associated with the key "key4" to the given update_value. The string and the new value will be provided as input to your function. The key "key4" could either be in the outer map or inside a nested map. Your function should return the updated map.

Note: If a value in the outer map is not a map itself but a string, convert it to a map where the original string is the key and an empty string "" is the value. For example, "key": "value" should be converted to "key": {"value": ""}.

The input string will contain from 1 to 500 characters, inclusive. For this task, we'll assume that all keys in the JSON object are unique.

Example Input: "{\"key1\": \"value1\", \"key2\": {\"key3\": \"value3\", \"key4\": \"value4\"}, \"key5\": \"value5\"}", "newValue"

Expected Output:

{
    "key1": {"value1": ""},
    "key2": {
        "key3": "value3",
        "key4": "newValue"
    },
    "key5": {"value5": ""}
}
    */

    public Map<String, Map<String, String>> solution(String jsonString, String updateValue) {
        //0. Definir constantes:
        final char OPEN_BRACKET = '{', CLOSE_BRACKET = '}', COMMA = ',', COLON = ':', QUOTE = '\"';

        //1. Declarar variables a usar
        Map<String,Map<String,String>> outerMap = new HashMap<>();
        Map<String,String> innerMap = new HashMap<>();
        String outerKey= "", innerKey = "";
        boolean isKey = false, insideInnerMap = false, isInnerKey = false;
        int i = 0, toknStt = 0, toknEnd = 0;

        //2. bucle para recorrer el string:
        while(i < jsonString.length()){
            switch (jsonString.charAt(i)) {
                case OPEN_BRACKET:
                    toknStt = jsonString.indexOf(QUOTE, i+1)+1;
                    toknEnd = jsonString.indexOf(QUOTE, toknStt);
                    String key = jsonString.substring(toknStt, toknEnd);
                    if(insideInnerMap || isInnerKey){
                        innerKey = key;
                    } else{
                        outerKey = key;
                    }
                    i = toknEnd+1;
                    break;
                case CLOSE_BRACKET:
                    if(insideInnerMap){
                        outerMap.put(outerKey, new HashMap<>(innerMap));
                        innerMap = new HashMap<>();
                        insideInnerMap = false;
                    }
                    i++;
                    break;
                case COLON:
                    if(jsonString.charAt(i+2)==OPEN_BRACKET){
                        insideInnerMap = true;
                        i +=2;
                    }else{
                        toknStt = jsonString.indexOf(QUOTE, i)+1;
                        toknEnd = jsonString.indexOf(QUOTE, toknStt);
                        String strValue = jsonString.substring(toknStt,toknEnd);
                        if(insideInnerMap){
                            innerMap.put(innerKey, strValue);
                        }else{
                            outerMap.put(outerKey, new HashMap<>(Map.of(strValue, "")));
                        }
                        i = toknEnd;
                    }
                    break;
                case COMMA:
                    toknStt = jsonString.indexOf(QUOTE, i)+1;
                    toknEnd = jsonString.indexOf(QUOTE, toknStt);
                    if(insideInnerMap){
                        isInnerKey = true;
                        innerKey = jsonString.substring(toknStt, toknEnd);
                    }else{
                        isInnerKey = false;
                        outerKey = jsonString.substring(toknStt, toknEnd);
                    }
                    i = toknEnd+1;
                    break;
                default:
                    i++;
                    break;
            }
        }

        //3. actualiza key4
        if(outerMap.containsKey("key4")){
            outerMap.put("key4", Map.of(updateValue, ""));
        } else {
            for(Map<String,String> map : outerMap.values()){
                if(map.containsKey("key4")){
                    map.put("key4", updateValue);
                }
            }
        }



        return outerMap;
    }
}