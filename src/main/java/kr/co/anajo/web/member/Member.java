package kr.co.anajo.web.member;

import kr.co.anajo.web.resource.Resource;

/**
 * 회원
 * 
 * @author jodoyoung
 * @date 2016-01-02
 */
public class Member extends Resource {

	private String loginId, password, email, name;

	private MemberStatus status;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MemberStatus getStatus() {
		return status;
	}

	public void setStatus(MemberStatus status) {
		this.status = status;
	}

}