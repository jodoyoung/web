package kr.co.anajo.web.menu;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping("/menu/create")
	public void create(@RequestBody Menu menu) {
		this.menuService.create(menu);
	}

	@RequestMapping("/menu/read")
	public Menu read(String id) {
		return this.menuService.read(id);
	}

	@RequestMapping("/menu/list")
	public List<Menu> list() {
		return this.menuService.readAll();
	}

	@RequestMapping("/menu/update")
	public void update(String id, String title, MenuVisibility visibility, String link, int order) {
		this.menuService.update(id, title, visibility, link, order);
	}

	@RequestMapping("/menu/delete")
	public void delete(String id) {
		this.menuService.delete(id);
	}
}