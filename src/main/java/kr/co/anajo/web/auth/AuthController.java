package kr.co.anajo.web.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.anajo.web.member.MemberService;

@Controller
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthService authService;

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (this.memberService.count() == 0) {
			return "member/create";
		}

		return "auth/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String loginId, String password,
			@RequestHeader(value = "referer", required = false) String referer) throws Exception {
		logger.debug("login id: {}, password: {}", loginId, password);
		this.authService.login(loginId, password);
		// TODO home menu uri redirect
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		this.authService.logout();
		return "redirect:/";
	}

	@RequestMapping("/loginFailed")
	public String loginFailed(Model model) {
		return "auth/login_failed";
	}

	@RequestMapping("/accessDenied")
	public String accessDenied(Model model) {
		return "auth/access_denied";
	}
}
