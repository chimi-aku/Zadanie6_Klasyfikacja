package com.company;

import java.util.*;

public class KNN {

    private int k = 3; // k - Nearest Neighbor
    private Sample testSample ;
    private Map<String, Double> distances = new HashMap<>();


    public KNN(int k, Sample testSample, Map<String, Double> distances) {
        this.k = k;
        this.testSample = testSample;
        this.distances = distances;
    }

    public boolean classificate() {

        Map<String, Double> KNeighbors = getKNeighbors();
        Map<String, Double> countedNeighbors = countNeighborsByUsers(KNeighbors);
        String modeUser = getModeUser(countedNeighbors);

        String testUser = getUser(testSample.getSampleName());

        if(testUser.equals(modeUser)) {
            return true;
        }
        else {
            return false;
        }

    }



    private Map<String, Double> getKNeighbors() {

        Map<String, Double> kNeighbors = new HashMap<>();
        Map<String, Double> sortedDistances = sortByValue(distances);


        int i = 0;

        // Iterating HashMap through for loop
        for (Map.Entry<String, Double> set :
                sortedDistances.entrySet()) {

            kNeighbors.put(set.getKey(), set.getValue());
            i++;
            if(i == k) break;
        }

         return kNeighbors;
    }

    private Map<String, Double> countNeighborsByUsers(Map<String, Double> KNeighbors) {

        Map<String, Double> countedNeighbors = new HashMap<>();

        // Iterating HashMap through for loop
        for (Map.Entry<String, Double> set :
                KNeighbors.entrySet()) {

            String user = getUser(set.getKey());
            countedNeighbors.put(user, 0.0);
        }

        // Iterating over two hashmaps

        Iterator<Map.Entry<String, Double>> firstIterator = KNeighbors.entrySet().iterator();

        while(firstIterator.hasNext()) {

            // next hashmaps element
            Map.Entry<String, Double> firstEntry = firstIterator.next();


            String user = getUser(firstEntry.getKey());
            Double count = countedNeighbors.get(user) + 1;
            countedNeighbors.put(user, count);
        }


        return countedNeighbors;

    }

    private String getModeUser(Map<String, Double> countedNeighbors) {

        String modeUser = "";
        Map<String, Double> sortedCountedNeighbors = sortByValue(countedNeighbors);

        for (Map.Entry<String, Double> set :
                sortedCountedNeighbors.entrySet()) {

            modeUser = set.getKey();
        }


        return modeUser;

    }

    private String getUser(String sampleName) {

        String[] str = sampleName.split("_");
        String user = str[0];
        return user;

    }

    // function to sort hashmap by values
    private static Map<String, Double> sortByValue(Map<String, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double> > list =
                new LinkedList<Map.Entry<String, Double> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Double> >() {
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


}
