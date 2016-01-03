package kr.co.anajo.web.component.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public Member getMemberForLoginId(String loginId) {
		Query query = new Query(new Criteria("loginId").is(loginId));
		return (Member) this.resourceDao.getResource(query, Member.class);
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