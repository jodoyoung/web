package kr.co.anajo.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.anajo.web.component.auth.AuthService;

public class AuthenticationFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		String uri = request.getRequestURI();
		logger.debug("request uri : " + uri);
		// TODO 권한 체크.
		if ("/login".equals(uri)) {
			chain.doFilter(req, res);
			return;
		}

		Object obj = request.getSession().getAttribute(AuthService.SESSION_USER_KEY);
		if (obj != null && obj instanceof JsonObject) {
			chain.doFilter(req, res);
		} else {
			RequestDispatcher dispatcher = this.filterConfig.getServletContext().getRequestDispatcher(
					"/WEB-INF/views/auth/login.jsp");
			dispatcher.forward(req, res);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
