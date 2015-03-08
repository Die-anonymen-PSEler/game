package com.retroMachines.data.models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StatisticTest {
	
	private static final int TEST_ID = 1;
	private static final float TEST_PLAYTIME = 5;
	private static final int TEST_LEVELCOMPLETED = 2;
	private static final int TEST_STEPCOUNTER = 1337;
	
	private Statistic statistic;

	@Before
	public void setUp() throws Exception {
		statistic = new Statistic(TEST_ID);
		statistic.setLevelsComplete(TEST_LEVELCOMPLETED);
		statistic.setPlaytime(TEST_PLAYTIME);
		statistic.setStepCounter(TEST_STEPCOUNTER);
	}

	@After
	public void tearDown() throws Exception {
		statistic.destroy();
	}
	
	@Test
	public void testCreateStatistic() {
		Statistic statistic = new Statistic(TEST_ID, TEST_PLAYTIME, TEST_LEVELCOMPLETED, TEST_STEPCOUNTER);
		assertTrue("wrong playtime", TEST_PLAYTIME == statistic.getPlaytime());
		assertTrue("wrong levels completed", TEST_LEVELCOMPLETED == statistic.getLevelsComplete());
		assertTrue("wrong step counter", TEST_STEPCOUNTER == statistic.getStepCounter());
		statistic.destroy();
	}
	
	/**
	 * fetch from db test
	 */
	@Test
	public void testDBFetch() {
		Statistic statisticLocal = new Statistic(TEST_ID);
		
		assertTrue("wrong playtime", TEST_PLAYTIME == statisticLocal.getPlaytime());
		assertTrue("wrong levels completed", TEST_LEVELCOMPLETED == statisticLocal.getLevelsComplete());
		assertTrue("wrong step counter", TEST_STEPCOUNTER == statisticLocal.getStepCounter());
	}
	
	/**
	 * write back check
	 */
	@Test
	public void testWriteBack() {
		Statistic statistic = new Statistic(TEST_ID);
		statistic.setLevelsComplete(9000);
		Statistic statistic2 = new Statistic(TEST_ID);
		assertTrue("falsche level completed", statistic2.getLevelsComplete() == 9000);
		statistic2.setLevelsComplete(TEST_LEVELCOMPLETED);
	}
}
