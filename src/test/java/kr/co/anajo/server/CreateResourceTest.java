package kr.co.anajo.server;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.anajo.web.component.member.model.Member;

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
