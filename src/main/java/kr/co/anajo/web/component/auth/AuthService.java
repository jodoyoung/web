package kr.co.anajo.web.component.auth;

import javax.servlet.http.HttpSession;

import kr.co.anajo.web.SessionContext;
import kr.co.anajo.web.component.member.MemberService;
import kr.co.anajo.web.component.member.model.Member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	public static final String SESSION_USER_KEY = "__AnA_UseR__";

	@Autowired
	private MemberService memberService;

	public boolean login(String loginId, String password) {
		Member member = this.memberService.getMemberForLoginId(loginId);

		if (member == null) {
			return false;
		}

		if (!password.equals(member.getPassword())) {
			return false;
		}

		SessionContext.setAttribute(AuthService.SESSION_USER_KEY, member);

		return true;
	}

	public void logout() {
		HttpSession session = SessionContext.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

}
