package kr.co.anajo.web.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/member/create")
	public void create(String loginId, String name, String password, String email, MemberStatus status) {
		this.memberService.create(loginId, name, password, email, status);
	}

	@RequestMapping("/member/read")
	public Member read(String id) {
		return this.memberService.read(id);
	}

	@RequestMapping("/member/list")
	public List<Member> list() {
		return this.memberService.readAll();
	}

	@RequestMapping("/member/update")
	public void update(String id, String loginId, String password, String email, MemberStatus status) {
		this.memberService.update(id, loginId, password, email, status);
	}

	@RequestMapping("/member/delete")
	public void delete(String id) {
		this.memberService.delete(id);
	}

	@RequestMapping("/public/member/create")
	@ResponseBody 
	public String publicCreate(String loginId, String name, String password, String email) {
		if (this.memberService.count() != 0) {
			return "failed";
		}
		this.create(loginId, name, password, email, MemberStatus.ACTIVATION);
		return "success";
	}
}