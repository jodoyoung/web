package kr.co.anajo.web.component.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.anajo.web.component.member.MemberService;
import kr.co.anajo.web.component.member.model.Member;
import kr.co.anajo.web.component.member.model.MemberStatus;
import kr.co.anajo.web.component.menu.MenuService;
import kr.co.anajo.web.component.menu.model.Menu;
import kr.co.anajo.web.util.IdGenerator;

@Service("resourceService")
public class ResourceService {

	private static final Logger log = LoggerFactory.getLogger(ResourceService.class);

	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MenuService menuService;

	public Resource createResource(ResourceType type, HttpServletRequest request) throws Exception {
		return this.createResource(type, request, null);
	}

	public Resource createResource(ResourceType type, HttpServletRequest request, String parentId) throws Exception {
		if (type == ResourceType.MEMBER) {
			Member member = null;
			try {
				String body = IOUtils.toString(request.getInputStream(), "utf-8");
				ObjectMapper om = new ObjectMapper();
				member = om.readValue(body, Member.class);
			} catch (IOException ioe) {
				throw new Exception("member object parse failed", ioe);
			}
			member.setId(IdGenerator.createUUID());
			member.setType(type);
			member.setStatus(MemberStatus.ENABLED);
			member.setCreateTime(new Date());
			member.setParentId(parentId);
			memberService.createMember(member);
			return member;
		} else if (type == ResourceType.MENU) {
			Menu menu = null;
			try {
				String body = IOUtils.toString(request.getInputStream(), "utf-8");
				ObjectMapper om = new ObjectMapper();
				menu = om.readValue(body, Menu.class);
			} catch (IOException ioe) {
				throw new Exception("menu object parse failed", ioe);
			}
			menu.setId(IdGenerator.createUUID());
			menu.setType(type);
			menu.setCreateTime(new Date());
			menu.setParentId(parentId);
			menuService.createMenu(menu);
			return menu;
		} else if (type == ResourceType.FILE) {
			Collection<Part> parts = request.getParts();
			for (Part part : parts) {
				log.debug("ppppp ==== {}", part.getContentType());
				log.debug("ppppp ==== {}", part.getHeaderNames());
				log.debug("ppppp ==== {}", part.getName());
				log.debug("ppppp ==== {}", part.getSize());
				log.debug("ppppp ==== {}", part.getSubmittedFileName());
			}
		}
		return null;
	}

	public void getResource(ResourceType type, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (type == ResourceType.MENU) {
			List<Menu> menus = this.menuService.getMenuList();

			if (CollectionUtils.isEmpty(menus)) {
				response.sendError(404, "resource not found.");
			}

			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(menus);
			response.setHeader("Content-Type", "application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.append(json);
			out.close();
		} else if (type == ResourceType.MEMBER) {

		} else {
			response.sendError(404, "resource not found.");
		}
		return;
	}

	public Resource getResource(String id, HttpServletRequest request, HttpServletResponse response) {
		return resourceDao.getResource(id);
	}

	public JsonObject getResourceInfo(String id) throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		
		URIBuilder builder = new URIBuilder("http://localhost:36006/read");
		builder.setParameter("id", id);
		
		HttpGet get = new HttpGet(builder.build());
		HttpResponse response = client.execute(get);
		
		String content = EntityUtils.toString(response.getEntity());

		JsonParser parser = new JsonParser();
		return parser.parse(content).getAsJsonObject();
	}

	public List<Resource> getResources(String parentId) {
		return resourceDao.getResources(parentId);
	}
}
