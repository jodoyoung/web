package kr.co.anajo.server.component.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.anajo.server.component.member.MemberService;
import kr.co.anajo.server.component.member.model.Member;
import kr.co.anajo.server.component.member.model.MemberStatus;
import kr.co.anajo.server.component.menu.MenuService;
import kr.co.anajo.server.component.menu.model.Menu;
import kr.co.anajo.server.util.IdGenerator;

@Service("resourceService")
public class ResourceService {

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

	public List<Resource> getResources(String parentId) {
		return resourceDao.getResources(parentId);
	}
}
