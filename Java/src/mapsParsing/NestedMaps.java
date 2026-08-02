package mapsParsing;

import java.util.HashMap;
import java.util.Map;

public class NestedMaps {
    public static void main(String[] args) {
// TODO: Define a 'libraryCatalog' using HashMap with a few books. 1
// Each book id as key and another HashMap as value containing details like title, author, and year_published
        HashMap<String,HashMap<String,String>> library = new HashMap<>();
        library.put("BookA", new HashMap<>(Map.of(
                "title","Neverending Story",
                "author","no one knows",
                "year","19something"
        )));

        library.put("BookB", new HashMap<>(Map.of(
                "title","Thje expanse",
                "author","I don't remember'",
                "year","20something"
        )));

        library.put("BookC", new HashMap<>(Map.of(
                "title","Undying Mercenaries",
                "author","BV Larson",
                "year","201x"
        )));

// TODO: Assign a 'bookId' variable with the id of the book you want to check
        String bookId = "BookA";
        String result;

// TODO: Implement a search in 'libraryCatalog' to check if the 'bookId' exists and print the book details or "Book not found in the library catalog."
        if(null!=library.get(bookId)){
            result = String.format("Title: %s \n"
                            +"Author: %s \n"
                            +"Year: %s",
                    library.get(bookId).get("title"),
                    library.get(bookId).get("author"),
                    library.get(bookId).get("year"));
        }else{
            result = "Book not found in the library catalog.";
        }
        System.out.println(result);

    }

}