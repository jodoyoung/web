package kr.co.anajo.server;

import org.junit.Test;

import kr.co.anajo.web.component.resource.ResourceType;

public class JavaTest {

	@Test
	public void testEnum() {
		try {
			ResourceType type = ResourceType.valueOf("menu1".toUpperCase());
			System.out.println(type);
		} catch (IllegalArgumentException iae) {
			System.out.println("error");
		}
	}
}
