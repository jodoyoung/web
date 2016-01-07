package kr.co.anajo.web.component.member;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.anajo.web.component.member.model.Member;
import kr.co.anajo.web.component.member.model.MemberStatus;
import kr.co.anajo.web.component.resource.ResourceDao;
import kr.co.anajo.web.component.resource.ResourceType;

@Service
public class MemberService {

	@Autowired
	private ResourceDao resourceDao;

	public List<Member> getMembers() {
		return (List<Member>) this.resourceDao.getResources(ResourceType.MEMBER);
	}

	public Member getMember(String id) {
		return (Member) this.resourceDao.getResource(id);
	}

	public JsonObject getMemberForLoginId(String loginId) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		
		URIBuilder builder = new URIBuilder("http://localhost:36006/read");
		builder.setParameter("loginId", loginId);
		
		HttpGet get = new HttpGet(builder.build());
		HttpResponse response = client.execute(get);
		
		String content = EntityUtils.toString(response.getEntity());

		JsonParser parser = new JsonParser();
		return parser.parse(content).getAsJsonObject();
	}

	@Transactional
	public void createMember(Member member) {
		this.resourceDao.createResource(member);
	}
	
	public void create(String id, String loginId, String password, String email, MemberStatus status) {
	}

	public void updateMember(Member member) {
		this.resourceDao.updateResource(member);
	}

	public void deleteMember(String memberId) {
		this.resourceDao.deleteResource(memberId);
	}

}