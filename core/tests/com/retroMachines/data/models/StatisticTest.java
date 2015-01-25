package com.retroMachines.data.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StatisticTest {
	
	private static final int TEST_ID = 1;
	private static final int TEST_PLAYTIME = 5;
	private static final int TEST_LEVELCOMPLETED = 2;
	private static final int TEST_STEPCOUNTER = 1337;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * fetch from db test
	 */
	@Test
	public void testFETCH() {
		Statistic statistic = new Statistic(TEST_ID);
		assertTrue("wrong playtime", TEST_PLAYTIME == statistic.getPlaytime());
		assertTrue("wrong levels completed", TEST_LEVELCOMPLETED == statistic.getLevelsComplete());
		assertTrue("wrong step counter", TEST_STEPCOUNTER == statistic.getStepCounter());
	}
	
	/**
	 * write back check
	 */
	@Test
	public void testWrite() {
		Statistic statistic = new Statistic(TEST_ID);
		statistic.setLevelsComplete(9000);
		Statistic statistic2 = new Statistic(TEST_ID);
		assertTrue("falsche level completed", statistic2.getLevelsComplete() == 9000);
		statistic2.setLevelsComplete(TEST_LEVELCOMPLETED);
	}
}
