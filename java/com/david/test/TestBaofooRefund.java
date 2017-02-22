package com.david.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBaofooRefund {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRefund() {
		String info = "test refund";
		System.err.println(info);
		Assert.assertEquals(info, "test refund");
	}

}
