package kr.co.anajo.web.resource;

import java.security.InvalidParameterException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.anajo.web.exception.NotSupportException;

@RestController
public class ResourceController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "!" + MediaType.TEXT_HTML_VALUE)
	public void read(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("resource id: {}", id);
		// 특정 자원 조회.
		Resource resource = this.resourceService.get(id);

		RequestDispatcher rd = request.getRequestDispatcher(resource.getType().name().toLowerCase() + "/read/" + id);
		rd.forward(request, response);

		// if (ResourceType.MEMBER == type) {
		//
		// } else if (ResourceType.MENU == type) {
		//
		// } else {
		// response.sendError(HttpStatus.SERVICE_UNAVAILABLE.value(), "service
		// not supported.");
		// return;
		// }
	}

	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
	public List<Resource> type(@PathVariable("type") String type) {
		if (!StringUtils.hasText(type)) {
			throw new InvalidParameterException("type is null.");
		}

		if (EnumUtils.isValidEnum(ResourceType.class, type.toUpperCase())) {
			// 유형별 자원 목록 조회.
			return this.resourceService.getResourcesByType(ResourceType.valueOf(type.toUpperCase()));
		} else {
			throw new NotSupportException();
		}
	}

}