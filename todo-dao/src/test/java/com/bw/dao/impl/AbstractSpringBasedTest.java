package com.bw.dao.impl;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:test-dao-context.xml")
public class AbstractSpringBasedTest {
	
	public static void println(Object msg) {
		System.out.println(msg);
	}
}
