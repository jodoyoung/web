package kr.co.anajo.web.drive;

import kr.co.anajo.web.resource.Resource;

public class File extends Resource {

	private String path, name;

	private boolean isTemp;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTemp() {
		return isTemp;
	}

	public void setTemp(boolean isTemp) {
		this.isTemp = isTemp;
	}

}