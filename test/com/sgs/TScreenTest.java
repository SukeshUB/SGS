package com.sgs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class TScreenTest {
	
	private static List<Integer> marksList1 = null;
	private static List<Integer> marksList2 = null;
	private static List<Integer> marksList3 = null;
	private static List<Integer> marksList4 = null;
	private static List<Integer> marksList5 = null;
	private static List<Integer> marksList6 = null;
	private static List<Integer> marksList7 = null;
	
	private static final String[] RNAMES = new String[] { "Rakesh", "Sourabh", "Yuhao", "Harsha", "Sukesh", "Dexter", "House" };
	
	{
		marksList1 = new ArrayList<Integer>();
		marksList1.add(3);
		marksList1.add(3);
		marksList1.add(3);
		marksList2 = new ArrayList<>();
		marksList2.add(4);
		marksList2.add(1);
		marksList2.add(5);
		marksList3 = new ArrayList<>();
		marksList3.add(1);
		marksList3.add(2);
		marksList3.add(3);
		marksList4 = new ArrayList<>();
		marksList4.add(3);
		marksList4.add(3);
		marksList4.add(3);
		marksList5 = new ArrayList<>();
		marksList5.add(3);
		marksList5.add(3);
		marksList5.add(3);
		marksList6 = new ArrayList<>();
		marksList6.add(3);
		marksList6.add(3);
		marksList6.add(3);
	}
	
	@Test
	public void testNormalizeEmptyMap() {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		Map<String, Double> normalizedScore = null;
		normalizedScore = tscreen.normalize(dataMap);
		assertTrue(normalizedScore.isEmpty());
	}
	
	@Test
	public void testNormalizeNull() {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = null;
		Map<String, Double> normalizedScore = null;
		normalizedScore = tscreen.normalize(dataMap);
		assertNull(normalizedScore);
	}
	
	@Test
	public void testNormalizeMapWithTwoEntry() {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put(RNAMES[0], marksList1);
		dataMap.put(RNAMES[1], marksList2);
		Map<String, Double> normalizedScore = null;
		normalizedScore = tscreen.normalize(dataMap);
		for(Entry<String, Double> entry: normalizedScore.entrySet()) {
			if(RNAMES[0].equals(entry.getKey()))
				assertEquals((double)entry.getValue(), 0.47D,0.001);
			if(RNAMES[1].equals(entry.getKey()))
				assertEquals((double)entry.getValue(),0.53D,0.001);
		}
	}
	
	@Test
	public void testNormalizeMapWithSixEntry() {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put(RNAMES[0], marksList1);
		dataMap.put(RNAMES[1], marksList2);
		dataMap.put(RNAMES[2], marksList3);
		dataMap.put(RNAMES[3], marksList4);
		dataMap.put(RNAMES[4], marksList5);
		dataMap.put(RNAMES[5], marksList6);
		Map<String, Double> normalizedScore = null;
		normalizedScore = tscreen.normalize(dataMap);
		for(Entry<String, Double> entry: normalizedScore.entrySet()) {
			assertEquals((double)entry.getValue(), 0.47D,0.001);
			assertEquals((double)entry.getValue(),0.53D,0.001);
		}
	}

}

