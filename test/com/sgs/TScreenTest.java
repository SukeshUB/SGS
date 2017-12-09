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

import com.sgs.exception.InvalidRangeException;

/**
 * @author Rakesh
 * Tests all the types of input against the expected values for normalize method
 * of TScreen class
 */
public class TScreenTest {

	private static final double DELTA = 0.00001;
	private static List<Integer> marksList1 = null;
	private static List<Integer> marksList2 = null;
	private static List<Integer> marksList3 = null;
	private static List<Integer> marksList4 = null;
	private static List<Integer> marksList5 = null;
	private static List<Integer> marksList6 = null;
	private static List<Integer> marksList7 = null;
	private static List<Integer> marksList8 = null;
	private static List<Integer> negativeMarkList = null;
	private static List<Integer> marksListMoreThanFive = null;

	private static final String[] MEMBER_NAMES = new String[] { "Rakesh", "Sourabh", "Yuhao", "Harsha", "Sukesh", "Dexter",
			"House" };
	private static final double MIN = 0;
	private static final double MAX = 1;

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
		marksList3.add(3);
		marksList3.add(3);
		marksList3.add(4);
		marksList4 = new ArrayList<>();
		marksList4.add(4);
		marksList4.add(1);
		marksList4.add(3);
		marksList5 = new ArrayList<>();
		marksList5.add(2);
		marksList5.add(3);
		marksList5.add(5);
		marksList6 = new ArrayList<>();
		marksList6.add(5);
		marksList6.add(0);
		marksList6.add(2);
		marksList7 = new ArrayList<>();
		marksList7.add(3);
		marksList7.add(4);
		marksList7.add(0);

		// all zeroes
		marksList8 = new ArrayList<Integer>();
		marksList8.add(0);
		marksList8.add(0);
		marksList8.add(0);

		// negtive values
		negativeMarkList = new ArrayList<Integer>();
		negativeMarkList.add(-1);
		negativeMarkList.add(4);
		negativeMarkList.add(5);

		// marksListMoreThanFive
		marksListMoreThanFive = new ArrayList<Integer>();
		marksListMoreThanFive.add(6);
		marksListMoreThanFive.add(4);
		marksListMoreThanFive.add(1);

	}

	/**
	 * testNormalizeEmptyMap - Tests the empty map with no values passed as
	 * parameter to the normalize method
	 */
	@Test
	public void testNormalizeEmptyMap() {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		Map<String, Double> normalizedScore = null;

		try {
			normalizedScore = tscreen.normalize(dataMap);
		} catch (InvalidRangeException e) {
			assertEquals(TScreen.INVALID_RANGE, e.getMessage());
		}

	}

	/**
	 * testNormalizeEmptyMap - Tests the null value passed to the normalize method
	 */
	@Test
	public void testNormalizeNull() throws InvalidRangeException {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = null;
		Map<String, Double> normalizedScore = null;
		normalizedScore = tscreen.normalize(dataMap);
		assertNull(normalizedScore);
	}

	/**
	 * testNormalizeEmptyMap - Tests the map with two entries passed as
	 * parameter to the normalize method
	 */
	@Test
	public void testNormalizeMapWithTwoEntry() throws InvalidRangeException {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put(MEMBER_NAMES[0], marksList1);
		dataMap.put(MEMBER_NAMES[1], marksList2);
		Map<String, Double> normalizedScore = null;
		normalizedScore = tscreen.normalize(dataMap);
		for (Entry<String, Double> entry : normalizedScore.entrySet()) {
			if (MEMBER_NAMES[0].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.47D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);

			} else if (MEMBER_NAMES[1].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.53D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			}
		}
	}

	/**
	 * testNormalizeEmptyMap - Tests the map with three entries passed as
	 * parameter to the normalize method
	 */
	@Test
	public void testNormalizeMapWithThreeEntry() throws InvalidRangeException {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put(MEMBER_NAMES[0], marksList1);
		dataMap.put(MEMBER_NAMES[1], marksList2);
		dataMap.put(MEMBER_NAMES[2], marksList3);

		Map<String, Double> normalizedScore = null;
		normalizedScore = tscreen.normalize(dataMap);
		for (Entry<String, Double> entry : normalizedScore.entrySet()) {
			if (MEMBER_NAMES[0].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.31D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[1].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.34D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[2].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.34D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			}
		}
	}

	/**
	 * testNormalizeEmptyMap - Tests the map with six entries passed as
	 * parameter to the normalize method
	 */
	@Test
	public void testNormalizeMapWithSixEntry() throws InvalidRangeException {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put(MEMBER_NAMES[0], marksList1);
		dataMap.put(MEMBER_NAMES[1], marksList2);
		dataMap.put(MEMBER_NAMES[2], marksList3);
		dataMap.put(MEMBER_NAMES[3], marksList4);
		dataMap.put(MEMBER_NAMES[4], marksList5);
		dataMap.put(MEMBER_NAMES[5], marksList6);
		Map<String, Double> normalizedScore = null;
		normalizedScore = tscreen.normalize(dataMap);
		for (Entry<String, Double> entry : normalizedScore.entrySet()) {
			if (MEMBER_NAMES[0].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.17D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[1].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.19D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[2].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.19D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[3].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.15D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[4].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.19D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[5].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.13D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			}
		}
	}

	/**
	 * testNormalizeEmptyMap - Tests the map with seven entries passed as
	 * parameter to the normalize method
	 */
	@Test
	public void testNormalizeMapWithSevenEntry() throws InvalidRangeException {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put(MEMBER_NAMES[0], marksList1);
		dataMap.put(MEMBER_NAMES[1], marksList2);
		dataMap.put(MEMBER_NAMES[2], marksList3);
		dataMap.put(MEMBER_NAMES[3], marksList4);
		dataMap.put(MEMBER_NAMES[4], marksList5);
		dataMap.put(MEMBER_NAMES[5], marksList6);
		dataMap.put(MEMBER_NAMES[6], marksList7);
		Map<String, Double> normalizedScore = null;
		normalizedScore = tscreen.normalize(dataMap);
		for (Entry<String, Double> entry : normalizedScore.entrySet()) {
			if (MEMBER_NAMES[0].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.15D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[1].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.16D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[2].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.16D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[3].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.13D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[4].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.16D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[5].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.11D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[6].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.11D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			}
		}
	}

	/**
	 * testNormalizeEmptyMap - Tests the map with two entries with all marks as zeroes 
	 * passed as parameter to the normalize method
	 */
	@Test
	public void testNormalizeMapWithTwoEntryAllZeroes() throws InvalidRangeException {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put(MEMBER_NAMES[0], marksList8);
		dataMap.put(MEMBER_NAMES[1], marksList8);
		Map<String, Double> normalizedScore = null;
		normalizedScore = tscreen.normalize(dataMap);
		for (Entry<String, Double> entry : normalizedScore.entrySet()) {
			if (MEMBER_NAMES[0].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.00D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			} else if (MEMBER_NAMES[1].equals(entry.getKey())) {
				assertEquals((double) entry.getValue(), 0.00D, DELTA);
				assertTrue(MIN <= (double) entry.getValue() && (double) entry.getValue() <= MAX);
			}
		}
	}

	/**
	 * testNormalizeEmptyMap - Tests the map with one entry as parameter 
	 * to the normalize method
	 */
	@Test
	public void testNormalizeMapWithOneEntry() {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put(MEMBER_NAMES[0], marksList8);
		Map<String, Double> normalizedScore = null;
		try {
			normalizedScore = tscreen.normalize(dataMap);
		} catch (InvalidRangeException e) {
			assertEquals(TScreen.INVALID_RANGE, e.getMessage());
		}

	}

	/**
	 * testNormalizeEmptyMap - Tests the map with two entries with a negative mark 
	 * value as parameter to the normalize method
	 */
	@Test
	public void testNormalizeMapWithTwoEntryNegativeValues() {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put(MEMBER_NAMES[0], negativeMarkList);
		dataMap.put(MEMBER_NAMES[1], marksList1);
		Map<String, Double> normalizedScore = null;
		try {
			normalizedScore = tscreen.normalize(dataMap);
		} catch (InvalidRangeException e) {
			assertEquals(TScreen.INVALID_RANGE, e.getMessage());
		}

	}

	/**
	 * testNormalizeEmptyMap - Tests the map with two entries, with one having 
	 * more than five marks, as parameter to the normalize method
	 */
	@Test
	public void testNormalizeMapWithTwoEntryMarksMoreThanFive() {
		TScreen tscreen = new TScreen();
		Map<String, List<Integer>> dataMap = new HashMap<>();
		dataMap.put(MEMBER_NAMES[0], marksListMoreThanFive);
		dataMap.put(MEMBER_NAMES[1], marksList1);
		Map<String, Double> normalizedScore = null;
		try {
			normalizedScore = tscreen.normalize(dataMap);
		} catch (InvalidRangeException e) {
			assertEquals(TScreen.INVALID_RANGE, e.getMessage());
		}

	}

}
