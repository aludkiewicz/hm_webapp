package se.hm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.hm.backend.Dao;
import se.hm.dtos.ListResult;
import se.hm.util.HMUtils;

/**
 * 
 * This class deals with the pure backend-calls, i.e. calls to the DB.
 * In this case there are just two: Storing and fetching
 * 
 * @author alud
 *
 */
@RestController
@RequestMapping(value = "/backend", method = RequestMethod.GET)
public class BackendController {

    @Autowired
    private Dao dao;
    
    @RequestMapping(value = "/sortandstore", method = RequestMethod.PUT)
    public Status sortAndStore(@RequestParam(value = "list", required = true) String listString) {
        
        Status status = new Status();
        /*
         * The formulation "Sortera ett givet antal numeriska värden genom att slumpvis byta plats 
         * på dem i en stigande ordning och notera hur lång tid samt hur många platsbyten som 
         * behövdes för att alla värden skall varar representerade ordnat." is slightly unclear
         * 
         * I have made the following interpretation:
         * 
         * If the input is not already sorted, do the following:
         * 
         * 1. Randomize the order of the elements in the input
         * 2. Check if they are sorted in ascending order (1 < 2 < 3 etc)
         * 3. If yes, we're done. Otherwise continue with 1.
         * 
         */
        try {
            parseAndSave(listString);
        } catch (Exception e) {
            status.setMsg(e.getMessage());
            return status;
        }
        status.setMsg("OK");
        return status;
    }

    private void parseAndSave(String listString) {
        List<Integer> inputList = HMUtils.stringToList(listString);
        List<Integer> outputList = new ArrayList<>(inputList);
        
        long time = System.nanoTime();
        int iterations = HMUtils.sortAndCountIterations(outputList);
        long elapsedTime = (System.nanoTime() - time);
        
        saveResults(outputList, inputList, iterations, toMillis(elapsedTime));
    }
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public List<ListResult> getAll() {
        return dao.getAll();
    }
    
    private void saveResults(List<Integer> sortedList, List<Integer> originalList, int iterations, double elapsedTime) {
        ListResult listRes = new ListResult();
        listRes.setInputList(HMUtils.listToString(originalList));
        listRes.setSortedList(HMUtils.listToString(sortedList));
        listRes.setIterations(iterations);
        listRes.setTime(elapsedTime);
        dao.save(listRes);
    }
    
    private double toMillis(long elapsedTime) {
        return elapsedTime/1000.0/1000.0;
    }
    
    private class Status {
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
