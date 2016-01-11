package kr.co.anajo.web.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public void create(String id, String loginId, String name, String password, String email, MemberStatus status) {
		Member member = new Member();
		member.setId(id);
		member.setLoginId(loginId);
		member.setName(name);
		member.setPassword(password);
		member.setEmail(email);
		member.setStatus(status);
		this.memberRepository.insert(member);
	}

	public Member read(String id) {
		return this.memberRepository.findOne(id);
	}

	public List<Member> readAll() {
		return this.memberRepository.findAll();
	}

	public void update(String id, String loginId, String password, String email, MemberStatus status) {
		Member member = this.read(id);
		if (StringUtils.hasText(loginId))
			member.setLoginId(loginId);
		if (StringUtils.hasText(password))
			member.setPassword(password);
		if (StringUtils.hasText(email))
			member.setEmail(email);
		if (status != null)
			member.setStatus(status);
		this.memberRepository.save(member);
	}

	public void delete(String id) {
		this.memberRepository.delete(id);
	}

	public Member getMemberByLoginId(String loginId) {
		return this.memberRepository.findByLoginId(loginId);
	}

}
