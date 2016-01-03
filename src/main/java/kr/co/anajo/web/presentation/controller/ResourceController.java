package kr.co.anajo.web.presentation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import kr.co.anajo.web.component.resource.Resource;
import kr.co.anajo.web.component.resource.ResourceService;
import kr.co.anajo.web.component.resource.ResourceType;

@RestController
public class ResourceController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "!" + MediaType.TEXT_HTML_VALUE)
	public void service(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("resource id: {}", id);
		if (EnumUtils.isValidEnum(ResourceType.class, id.toUpperCase())) {
			// 유형별 자원 목록 조회.
			this.resourceService.getResource(ResourceType.valueOf(id.toUpperCase()), request, response);
		} else { // 특정 자원 조회.
			JsonObject resourceInfo = this.resourceService.getResourceInfo(id);
			String type = resourceInfo.get("type").toString();

			AbstractResourceController controller = null;
			if (ResourceType.MEMBER.name().equalsIgnoreCase(type)) {
				controller = new MemberController();
			} else if (ResourceType.MENU.name().equalsIgnoreCase(type)) {
				controller = new MenuController();
			}

			if (controller == null) {
				response.sendError(HttpStatus.SERVICE_UNAVAILABLE.value(), "service not supported.");
				return;
			}

			controller.read(request, response);
		}
	}

	@RequestMapping(value = "/resource", method = RequestMethod.GET, produces = "!" + MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public List<Resource> getRootResources() {
		return this.resourceService.getResources(null);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getHeader("X-Resource-Type");

		ResourceType resourceType = null;
		try {
			resourceType = ResourceType.valueOf(type);
		} catch (Exception e) {
			throw new Exception("resource type missmatch", e);
		}

		AbstractResourceController controller = null;
		if (resourceType == ResourceType.MEMBER) {
			controller = new MemberController();
		} else if (resourceType == ResourceType.MENU) {
			controller = new MenuController();
		}

		controller.create(request, response);
		// Resource resource = resourceService.createResource(resourceType,
		// request);

		// HttpHeaders responseHeaders = new HttpHeaders();
		// responseHeaders.set("Cache-Control",
		// "no-cache, no-store, max-age=0, must-revalidate");
		// responseHeaders.set("Expires", "Fri, 01 Jan 2010 00:00:00 GMT");
		// return new ResponseEntity<Resource>(resource, responseHeaders,
		// HttpStatus.OK);
	}
}
