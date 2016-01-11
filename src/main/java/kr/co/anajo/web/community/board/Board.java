package kr.co.anajo.web.community.board;

import kr.co.anajo.web.resource.Resource;

/**
 * 게시판
 * 
 * @author jodoyoung
 * @date 2015-12-27
 */
public class Board extends Resource {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
