package kr.co.anajo.server.presentation.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.anajo.server.component.resource.Resource;
import kr.co.anajo.server.component.resource.ResourceService;
import kr.co.anajo.server.component.resource.ResourceType;

@RestController
public class ResourceController {

	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "!" + MediaType.TEXT_HTML_VALUE)
	public void service(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("resource id: {}", id);

		try {
			ResourceType type = ResourceType.valueOf(id.toUpperCase());
			this.resourceService.getResource(type, request, response);
		} catch (Exception e) {
			this.resourceService.getResource(id, request, response);
		}
	}

	@RequestMapping(value = "/resource", method = RequestMethod.GET, produces = "!" + MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public List<Resource> getRootResources() {
		return this.resourceService.getResources(null);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Resource> create(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getHeader("X-Resource-Type");

		ResourceType resourceType = null;
		try {
			resourceType = ResourceType.valueOf(type);
		} catch (Exception e) {
			throw new Exception("resource type missmatch", e);
		}

		Resource resource = resourceService.createResource(resourceType, request);

		// HttpHeaders responseHeaders = new HttpHeaders();
		// responseHeaders.set("Cache-Control",
		// "no-cache, no-store, max-age=0, must-revalidate");
		// responseHeaders.set("Expires", "Fri, 01 Jan 2010 00:00:00 GMT");
		// return new ResponseEntity<Resource>(resource, responseHeaders,
		// HttpStatus.OK);
		return new ResponseEntity<Resource>(resource, HttpStatus.OK);
	}
}
