package kr.co.anajo.web.drive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

@Controller
public class PictureController {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private ImageService pictureFacade;

	@RequestMapping(value = "/picture/directory/**", method = RequestMethod.GET)
	public String page() {
		return "home";
	}

	@RequestMapping(value = "/picture/api/directory", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> rootDirectory() throws IOException {
		Map<String, Object> model = pictureFacade.getDirectoryItems();
		return model;
	}

	@RequestMapping(value = "/picture/api/directory/**", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> directory(HttpServletRequest request) throws IOException {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		path = path.replaceAll("/picture/api/directory", "");
		Map<String, Object> model = pictureFacade.getDirectoryItems(path);
		return model;
	}

	@RequestMapping(value = "/picture/thumbnail/**", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] thumbnail(HttpServletRequest request) throws IOException {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		path = path.replaceAll("/picture/thumbnail", "");

		Path thumbnailPath = pictureFacade.getThumbnailImage(path);

		return Files.readAllBytes(thumbnailPath);
	}

	@RequestMapping(value = "/picture/image/**", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] image(HttpServletRequest request) throws IOException {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		path = path.replaceAll("/picture/image", "");

		Path imagePath = pictureFacade.getImagePath(path);

		if (Files.notExists(imagePath)) {
			imagePath = Paths.get(request.getSession().getServletContext().getRealPath("/resources/img/no_image.png"));
		}
		return Files.readAllBytes(imagePath);
	}
}