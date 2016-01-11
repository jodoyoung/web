package kr.co.anajo.web.menu;

import kr.co.anajo.web.resource.Resource;

/**
 * 메뉴
 * 
 * @author jodoyoung
 * @date 2016-01-03
 */
public class Menu extends Resource {

	private String title, link;

	private MenuVisibility visibility;

	private int order;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public MenuVisibility getVisibility() {
		return visibility;
	}

	public void setVisibility(MenuVisibility visibility) {
		this.visibility = visibility;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}