package kr.co.anajo.server.component.member;

import java.util.List;

import kr.co.anajo.server.component.member.model.Member;
import kr.co.anajo.server.component.resource.ResourceDao;
import kr.co.anajo.server.component.resource.ResourceType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public void updateMember(Member member) {
		this.resourceDao.updateResource(member);
	}

	public void deleteMember(String memberId) {
		this.resourceDao.deleteResource(memberId);
	}

}