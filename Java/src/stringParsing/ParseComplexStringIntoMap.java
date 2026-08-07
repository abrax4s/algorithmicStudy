package stringParsing;


import java.util.HashMap;
import java.util.Map;

public class ParseComplexStringIntoMap {
    /*

    Imagine that you are a database manager dealing with a data structure in the form of a complex nested string. This string contains user data and is structured in such a way that user attributes are separated by semicolons (;), and within each user, the attribute-value pairs are separated by colons (:). Some of the user attributes themselves contain nested attribute-value pairs, which are enclosed in curly braces ({}).

Here's an example of such a string:

"User1:Age1=21;Location1=USA;Preferences1={Food1=Italian; Sport1=Fencing};User2:Age2=30; Location2=Canada; Preferences2={Music2=Jazz; Color2=Blue}".
Copy to clipboard
You need to write a Java function that will convert the string into a nested map, following the structure shown in the string. After the string has been converted into a map, the function should update the value of a user-preference pair for any user to a requested value and return the updated map.

In this string, the keys representing the user names contain numbers (User1, User2, etc.). You should also provide an option to find users by their numerical indices following the "User" keyword, such as 1 for User1, 2 for User2, and so on.

Your function should take the input string, the user index, the preference key, and the new value for the preference pair, and should return the updated map in the end.

The size of the input string will be less than or equal to 500 characters.

Example

Given:

String input = "User1:Age1=21;Location1=USA;Preferences1={Food1=Italian;Sport1=Fencing};User2:Age2=30;Location2=Canada;Preferences2={Music2=Jazz;Color2=Blue}";
int userIndex = 1;
String prefKey = "Sport1";
String newValue = "Hockey";

The function should return:

Map<String, Map<String, String>> result = new HashMap<>();
result.put("User1", Map.of("Age1", "21", "Location1", "USA", "Preferences1", "{Food1=Italian;Sport1=Hockey}"));
result.put("User2", Map.of("Age2", "30", "Location2", "Canada", "Preferences2", "{Music2=Jazz;Color2=Blue}"));



    */
    final char COLON = ':', SEMICOLON = ';', EQUALS = '=', OPEN_BRACKET = '{', CLOSE_BRACKET = '}';
    String userKey = "";
    Map<String,String> preferenceMap = new HashMap<>();
    int i = 0;
    public Map<String, Map<String, String>> updatePreference(String inputString, int userIndex, String prefKey, String newValue) {

        Map<String, Map<String,String>> result = new HashMap<>();
        Map<String, String> innerMap = new HashMap<>();

        String innerKey = "", innerValue = "";

        while(i<inputString.length()){
            preferenceMap = new HashMap<>();
            int nextEquals = inputString.indexOf(EQUALS, i);
            int nextColon = inputString.indexOf(COLON, i);
            if(nextColon != -1 && nextColon < nextEquals){
                userKey = inputString.substring(i, inputString.indexOf(COLON, i));
                i = inputString.indexOf(COLON, i)+1;
                innerKey = inputString.substring(i, inputString.indexOf(EQUALS, i));
                i = inputString.indexOf(EQUALS, i)+1;
                innerMap = new HashMap<>();
            }else {
                innerKey = inputString.substring(i, inputString.indexOf(EQUALS, i));
                i = inputString.indexOf(EQUALS, i)+1;
            }
            innerValue = getInnerValue(inputString, prefKey, newValue, userIndex);
            if(i<inputString.length() && inputString.charAt(i)==SEMICOLON){
                i++;
            }
            innerMap.put(innerKey, innerValue);

            result.put(userKey, innerMap);
        }

        return result;
    }

    private String getInnerValue(String input, String prefKey, String newValue, int userIndex){
        String innerValue = "";
        if(input.charAt(i) == OPEN_BRACKET){//entra en el mapa de preferencias
            int j = 1;
            String prefString = input.substring(i,input.indexOf(CLOSE_BRACKET, i));
            while(j<prefString.length()){
                String preferenceKey = prefString.substring(j, prefString.indexOf(EQUALS, j));
                j = prefString.indexOf(EQUALS,j)+1;
                int endTokn = prefString.indexOf(SEMICOLON, j) > 0? prefString.indexOf(SEMICOLON, j) : prefString.length();
                String prefVal = prefString.substring(j, endTokn);
                j = endTokn+1;
                preferenceMap.put(preferenceKey, prefVal);
                if(preferenceMap.containsKey(prefKey) && userKey.equals("User".concat(String.valueOf(userIndex)))){
                    preferenceMap.put(prefKey, newValue);
                }

            }
            StringBuilder sb = new StringBuilder("{");
            for(Map.Entry<String,String> prefEntry : preferenceMap.entrySet()){
                sb.append(prefEntry.getKey())
                        .append(String.valueOf(EQUALS))
                        .append(prefEntry.getValue())
                        .append(String.valueOf(SEMICOLON));
            }
            innerValue = sb.toString();
            innerValue = innerValue.substring(0,innerValue.length()-1).concat(String.valueOf(CLOSE_BRACKET));
            i = input.indexOf(CLOSE_BRACKET,i)+1;
        } else { //es un par clave-valor común
            int endTokn = input.indexOf(SEMICOLON,i) > 0? input.indexOf(SEMICOLON,i):input.length();
            innerValue = input.substring(i, endTokn);
            i = endTokn+1;
        }
        return innerValue;
    }
}