package kr.co.anajo.web.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.anajo.web.component.menu.MenuService;
import kr.co.anajo.web.component.menu.model.Menu;

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
