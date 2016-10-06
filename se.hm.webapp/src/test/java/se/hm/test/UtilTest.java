package se.hm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import se.hm.util.HMUtils;

public class UtilTest {
    
    @Test
    public void testUtilSortsProperly() {
        String listString = "5,1,6,2";
        List<Integer> list = HMUtils.stringToList(listString);
        HMUtils.sortAndCountIterations(list);
        assertTrue(HMUtils.isSorted(list));
    }
    
    @Test
    public void testUtilVerifiesSorts() {
        assertFalse(HMUtils.isSorted(Arrays.asList(1,2,3,5,1)));
        assertTrue(HMUtils.isSorted(Arrays.asList(1,2,3,5)));
        assertTrue(HMUtils.isSorted(Arrays.asList(1,2,3,4,4,4,4,4,5,5,5,5,7)));
    }
    
    @Test
    public void testUtilListToString() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        String testString = HMUtils.listToString(list);
        assertEquals("1,2,3,4,5,6,7", testString);
    }
}