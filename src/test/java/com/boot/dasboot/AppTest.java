package com.boot.dasboot;

import com.boot.dasboot.controller.HomeController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

	@Test
	public void testApp() {
		HomeController homeController = new HomeController();
		String result = homeController.home();
		assertEquals(result, "Das Boot, reporting for duty!");
	}
}