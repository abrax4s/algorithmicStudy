package stringParsing;

import java.util.*;

public class StringParsingTwo {
    public List<int[]> analyzeCompetition(String logs) {

        List<String> parsedLogs = List.of(logs.split(", "));
        List<int[]> orderedScores = new ArrayList<>();
        Map<Integer,Integer> summedScores = new HashMap<>();
        Map<Integer,Integer> failureCounts = new HashMap<>();
        Map<Integer, Integer> succesCounts = new HashMap<>();

        for(String log : parsedLogs){
            String[] arrayLog = log.split(" ");
            if("solve".equals(arrayLog[1])){
                summedScores.put(Integer.parseInt(arrayLog[0].trim()), (summedScores.getOrDefault(Integer.parseInt(arrayLog[0].trim()), 0)+Integer.parseInt(arrayLog[3].trim())));
                succesCounts.put(Integer.parseInt(arrayLog[0].trim()), (succesCounts.getOrDefault(Integer.parseInt(arrayLog[0].trim()), 0))+1);
            }else{
                failureCounts.put(Integer.parseInt(arrayLog[0].trim()), (failureCounts.getOrDefault(Integer.parseInt(arrayLog[0].trim()), 0))+1);
            }
        }

        for(Map.Entry success : succesCounts.entrySet()){
            orderedScores.add(new int[]{
                    (int)success.getKey(),
                    summedScores.get(success.getKey()),
                    (int)success.getValue(),
                    failureCounts.getOrDefault(success.getKey(), 0)
            });
        }

        Collections.sort(orderedScores, (a, b)->b[1]-a[1]);
        return orderedScores;

    }
}