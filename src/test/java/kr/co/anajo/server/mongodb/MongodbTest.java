package kr.co.anajo.server.mongodb;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import kr.co.anajo.web.component.member.model.Member;
import kr.co.anajo.web.component.member.model.MemberStatus;
import kr.co.anajo.web.component.resource.Resource;
import kr.co.anajo.web.component.resource.ResourceType;
import kr.co.anajo.web.util.IdGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("mongodb.xml")
public class MongodbTest {

	private static final Logger logger = LoggerFactory.getLogger(MongodbTest.class);

	@Autowired
	MongoTemplate mongoTemplate;

	@Before
	public void before() throws FileNotFoundException {
		Log4jConfigurer.initLogging("file:src/main/webapp/WEB-INF/config/log4j.xml");
	}

	@Test
	public void select() {
		Resource resource = mongoTemplate.findById("04aa3982bb1b40b3acf819f28bf42340", Resource.class, "resources");
		logger.info("Resource Class: {}", resource.getClass().getTypeName());
		logger.info("Resource: {}", resource.toString());

		ResourceType type = resource.getType();

		logger.info("Resource Type: {}", type);

		Member member = (Member) resource;
		logger.info("Member: {}", member);
	}

	// @Test
	public void crud() {
		Resource resource1 = createResource();
		mongoTemplate.insert(resource1, "resources");

		Member resource2 = mongoTemplate.findById(resource1.getId(), Member.class, "resources");

		resource2.setEmail("jodoyoung36@naver.com");
		mongoTemplate.save(resource2, "resources");

		Member resource3 = mongoTemplate.findById(resource1.getId(), Member.class, "resources");
		logger.info("update email: {}", resource3.getEmail());

		Query query = new Query(new Criteria("_id").is(resource1.getId()));
		mongoTemplate.remove(query, "resources");

		List<Resource> resources = mongoTemplate.findAll(Resource.class, "resources");
		logger.info("resources list: {}", resources.toString());
	}

	// @Test
	public void delete() {
		try {
			Resource resource = new Member();
			resource.setId("1bf85bda749e49928c54ff59e8b2bdc6");
			Query query = new Query(new Criteria("_id").is("1bf85bda749e49928c54ff59e8b2bdc6"));
			mongoTemplate.remove(query, "resources");
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public Resource createResource() {
		Member resource = new Member();
		resource.setId(IdGenerator.createUUID());
		resource.setType(ResourceType.MEMBER);
		resource.setLoginId("jodoyoung");
		resource.setPassword("whehdud36");
		resource.setEmail("jodoyoung36@gmail.com");
		resource.setName("조도영");
		resource.setStatus(MemberStatus.ENABLED);
		resource.setCreateTime(new Date());
		resource.setUpdateTime(null);
		return resource;
	}
}
