package com.bw.dao.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-dao-context.xml")
public class AbstractSpringBasedTest {
	
	public static void println(Object msg) {
		System.out.println(msg);
	}
}
