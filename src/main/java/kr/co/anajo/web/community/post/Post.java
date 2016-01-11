package kr.co.anajo.web.community.post;

import kr.co.anajo.web.resource.Resource;

/**
 * 게시글
 * 
 * @author jodoyoung
 * @date 2015-12-27
 */
public class Post extends Resource {

	private String subject, content;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}