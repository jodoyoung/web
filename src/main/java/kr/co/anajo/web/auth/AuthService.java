package kr.co.anajo.web.auth;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.anajo.web.SessionContext;
import kr.co.anajo.web.member.Member;
import kr.co.anajo.web.member.MemberService;

@Service
public class AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	public static final String SESSION_USER_KEY = "__AnA_UseR__";

	@Autowired
	private MemberService memberService;

	public boolean login(String loginId, String password) throws Exception {
		Member member = this.memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return false;
		}

		logger.debug("member: {}", member);

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
