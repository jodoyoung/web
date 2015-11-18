package kr.co.anajo.server.component.menu;

import java.util.List;

import kr.co.anajo.server.component.menu.model.Menu;
import kr.co.anajo.server.component.resource.ResourceDao;
import kr.co.anajo.server.component.resource.ResourceType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

	@Autowired
	ResourceDao resourceDao;

	public List<Menu> getMenuList() {
		return (List<Menu>) this.resourceDao.getResources(ResourceType.MENU);
	}

	public List<Menu> getMenus(String menuId) {
		return null;
	}

	public Menu getMenu(String id) {
		return (Menu) this.resourceDao.getResource(id);
	}

	public void createMenu(Menu menu) {
		this.resourceDao.createResource(menu);
	}

	public void updateMenu(Menu menu) {
		this.resourceDao.updateResource(menu);
	}

	public void deleteMenu(String menuId) {
		this.resourceDao.deleteResource(menuId);
	}

}
