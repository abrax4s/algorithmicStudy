package stringParsing;

import java.util.*;

public class StringParsingOne {

    public List<String> organizeInbox(String inboxString) {

        List<String> mailCounts = new ArrayList<>();
        List<String> sentMails = List.of(inboxString.split(";"));
        Map<String,Integer> counterMap = new HashMap<>();

        for(String mail : sentMails){
            String[] mailDetails = mail.split(",");
            counterMap.put(mailDetails[0].trim(), counterMap.getOrDefault(mailDetails[0].trim(), 0)+1);
        }

        List<Map.Entry> sortedEntries = new ArrayList<Map.Entry>(counterMap.entrySet());
        Collections.sort(sortedEntries, (a, b) -> {
            int countCompare = Integer.compare((int)b.getValue(), (int)a.getValue());
            if (countCompare != 0)
                return countCompare;
            return ((String) a.getKey()).compareTo((String)b.getKey());
        });
        for(Map.Entry mailEntry : sortedEntries){
            mailCounts.add(mailEntry.getKey()+" "+mailEntry.getValue());
        }
        return mailCounts;
    }
}