package kr.co.anajo.server;

import static org.junit.Assert.fail;

import java.io.IOException;

import kr.co.anajo.server.component.member.model.Member;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/application.xml", "classpath:config/servlet.xml" })
public class CreateResourceTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		Member member = om.readValue("{\"id\": \"whehdud\"}", Member.class);
		System.out.println(member);
		fail("Not yet implemented");
	}

}
