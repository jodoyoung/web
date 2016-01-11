package kr.co.anajo.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.co.anajo.web.auth.AuthService;
import kr.co.anajo.web.member.Member;

public class SessionContext {

	public static void setAttribute(String key, Object value) {
		HttpSession httpSession = SessionContext.getSession(true);
		httpSession.setAttribute(key, value);
	}

	public static Object getAttribute(String key) {
		HttpSession httpSession = SessionContext.getSession(false);
		if (httpSession == null) {
			return null;
		}
		return httpSession.getAttribute(key);
	}

	public static Member getCurrentUser() {
		Member member = (Member) SessionContext.getAttribute(AuthService.SESSION_USER_KEY);
		return member;
	}

	public static HttpSession getSession(boolean isCreate) {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return servletRequestAttribute.getRequest().getSession(isCreate);
	}

}
