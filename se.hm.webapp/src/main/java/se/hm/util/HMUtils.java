package se.hm.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class contains utility functions for the controllers, so as to 
 * separate the logic from the controlling of the webapp
 * 
 * @author alud
 *
 */
public class HMUtils {
    
    
    private static final String DELIM = ",";

    public static <T> String listToString(List<T> list) {
        
        StringBuilder sb = new StringBuilder();
        for(T t : list) {
            sb.append(t.toString()).append(DELIM);
        }
        sb.setLength(sb.length() - DELIM.length()); // remove last DELIM
        return sb.toString();
    }


    public static List<Integer> stringToList(String listString) {
        List<Integer> list = new ArrayList<>();
        String[] elements = listString.split(DELIM);
        
        for(String element : elements) {
            list.add(Integer.parseInt(element));
        }
        return list;
    }


    public static boolean isSorted(List<Integer> maybeSortedList) {
        for(int i = 0; i < maybeSortedList.size() - 1; i++) {
            if(maybeSortedList.get(i) > maybeSortedList.get(i+1)) {
                return false; // At least one element out of order
            }
        }
        return true; // All elements in order
    }
    
    /**
     * Warning! This algorithm runs in O(n!). Takes ~300k iterations to sort 10 elements
     * 
     * @param list
     * @return
     */
    public static int sortAndCountIterations(List<Integer> list) {
        int iterations = 0;
        while(!HMUtils.isSorted(list)) {
            Collections.shuffle(list);
            iterations++;
        }
        return iterations;
    }
}
