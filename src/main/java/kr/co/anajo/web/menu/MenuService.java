package kr.co.anajo.web.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;
	
	public void create(Menu menu) {
		this.menuRepository.insert(menu);
	}

	public void create(String id, String title, MenuVisibility visibility, String link, int order) {
		Menu menu = new Menu();
		menu.setId(id);
		menu.setTitle(title);
		menu.setVisibility(visibility);
		menu.setLink(link);
		if (order > 0) {
			menu.setOrder(order);
		}
		this.menuRepository.insert(menu);
	}

	public Menu read(String id) {
		return this.menuRepository.findOne(id);
	}

	public List<Menu> readAll() {
		return this.menuRepository.findAll();
	}

	public void update(String id, String title, MenuVisibility visibility, String link, int order) {
		Menu menu = this.read(id);
		if (StringUtils.hasText(title))
			menu.setTitle(title);
		if (StringUtils.hasText(link))
			menu.setTitle(link);
		if (visibility != null)
			menu.setVisibility(visibility);
		if (order > 0)
			menu.setOrder(order);
		this.menuRepository.save(menu);
	}

	public void delete(String id) {
		this.menuRepository.delete(id);
	}

}
