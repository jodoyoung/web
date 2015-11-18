package kr.co.anajo.server;

import kr.co.anajo.server.component.resource.ResourceType;

import org.junit.Test;

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
