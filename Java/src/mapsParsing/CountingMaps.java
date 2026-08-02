package mapsParsing;

import java.util.HashMap;
import java.util.ArrayList;

public class CountingMaps {

    public static void main(String[] args) {

// Create and initialize an ArrayList 'books' with some duplicated book titles of your choice
        ArrayList<String> books = new ArrayList<>();
        books.add("Neverending Story"); books.add("The handmaiden tale");
        books.add("Neverending Story"); books.add("Total Recall");
        books.add("Neverending Story"); books.add("Undying mercenaries");
        books.add("Hephestus rising"); books.add("Omega Force");
        books.add("Hephestus rising"); books.add("Jurassic Park");
        books.add("Hephestus rising"); books.add("Jurassic Park");
        books.add("Hephestus rising"); books.add("Jurassic Park");
        books.add("The order of the Phoenix"); books.add("The order of the Phoenix");

// Create an empty HashMap 'bookCount' to store the count of each book
        HashMap<String,Integer> bookCount = new HashMap<>();

// Loop through each book in the 'books' list and count the occurrences
        books.forEach(book -> {
            bookCount.put(book,bookCount.getOrDefault(book, 0)+1);
        });

// Finally, print the 'bookCount' HashMap
        bookCount.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}