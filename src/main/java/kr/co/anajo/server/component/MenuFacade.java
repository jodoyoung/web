package kr.co.anajo.server.component;

import java.util.ArrayList;
import java.util.List;

import kr.co.anajo.server.component.menu.MenuService;
import kr.co.anajo.server.component.menu.model.Menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuFacade {

	@Autowired
	private MenuService menuService;

	public List<Menu> getMenuList() {
		List<Menu> menus = new ArrayList<Menu>();

		// Menu Resource 조회

		return menus;
	}

	public List<Menu> getMenuList(String menuId) {
		List<Menu> menus = new ArrayList<Menu>();

		// Menu Resource 조회

		return menus;
	}

	public Menu getMenu(String menuId) {
		return menuService.getMenu(menuId);
	}

	public void createMenu(Menu model) {
		menuService.createMenu(model);
	}

	public void updateMenu(Menu model) {
		menuService.updateMenu(model);
	}

	public void deleteMenu(String menuId) {
		menuService.deleteMenu(menuId);
	}

}
