package stringParsing;

import java.util.*;

public class StringParsingThree {

//"1 borrow 09:00, 2 borrow 10:00, 1 return 12:00, 3 borrow 13:00, 2 return 15:00, 3 return 16:00"
//return ["2 05:00"]

    public List<String> solution(String logs) {
        List<String> sublogs = List.of(logs.split(", "));
        List<String> result = new ArrayList<>();
        Map<String, Integer> borrowTimes = new HashMap<>();
        Map<String, Integer> returnTimes = new HashMap<>();
        Map<String,Integer> times = new HashMap<>();

        for(String sublog : sublogs){
            String[] arrayLog = sublog.split(" ");
            int borrowHr, borrowMin, returnHr, returnMin, borrowTime, returnTime;

            if("borrow".equals(arrayLog[1])){
                borrowHr = Integer.parseInt(arrayLog[2].substring(0,2));
                borrowMin = Integer.parseInt(arrayLog[2].substring(3,5));
                borrowTime = borrowHr*60+borrowMin;
                borrowTimes.put(arrayLog[0], borrowTimes.getOrDefault(arrayLog[0], 0)+borrowTime);
            }else{
                returnHr = Integer.parseInt(arrayLog[2].substring(0,2));
                returnMin = Integer.parseInt(arrayLog[2].substring(3,5));
                returnTime = returnHr*60+returnMin;
                returnTimes.put(arrayLog[0], returnTimes.getOrDefault(arrayLog[0], 0)+returnTime);
            }
        }

        for(Map.Entry<String,Integer> returnTime : returnTimes.entrySet()){
//test10: 1 borrow 01:00, 1 return 02:00, 2 borrow 03:00, 2 return 05:00, 1 borrow 06:00, 1 return 10:00
            times.put(returnTime.getKey(), times.getOrDefault(returnTime.getKey(), 0) +(returnTime.getValue()-borrowTimes.get(returnTime.getKey())));
        }

        int maxTime = Collections.max(times.values());
        for (Map.Entry<String,Integer> timeEntry : times.entrySet()){
            if(maxTime == timeEntry.getValue()){
                int totalHours = timeEntry.getValue()/60;
                int totalMinutes = timeEntry.getValue()%60;
                result.add(String.format("%s %s:%s", timeEntry.getKey(), convertTimeToString(totalHours), convertTimeToString(totalMinutes)));
            }
        }
        result.sort(Comparator.comparingInt(s -> Integer.parseInt(s.split(" ")[0])));
        return result;
    }

    private static String convertTimeToString(int time){
        if(time < 10){
            return "0"+String.valueOf(time);
        }
        return String.valueOf(time);
    }
}