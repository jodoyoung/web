package kr.co.anajo.web.component.auth;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import kr.co.anajo.web.SessionContext;
import kr.co.anajo.web.component.member.MemberService;

@Service
public class AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	public static final String SESSION_USER_KEY = "__AnA_UseR__";

	@Autowired
	private MemberService memberService;

	public boolean login(String loginId, String password) throws Exception {
		JsonObject member = this.memberService.getMemberForLoginId(loginId);

		if (member == null) {
			return false;
		}
		
		logger.debug("member: {}", member);

		if (!password.equals(member.get("password").getAsString())) {
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
