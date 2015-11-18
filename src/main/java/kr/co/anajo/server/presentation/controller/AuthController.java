package kr.co.anajo.server.presentation.controller;

import kr.co.anajo.server.component.auth.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "auth/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String loginId, String password,
			@RequestHeader(value = "referer", required = false) String referer) {
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
