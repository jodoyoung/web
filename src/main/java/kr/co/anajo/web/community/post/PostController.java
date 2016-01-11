package kr.co.anajo.web.community.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping("/post/create")
	public String create(String id, String subject, String content) {
		this.postService.create(id, subject, content);
		return "success";
	}

	@RequestMapping("/post/read")
	public Post read(String id) {
		return this.postService.read(id);
	}

	@RequestMapping("/post/list")
	public List<Post> list() {
		return this.postService.readAll();
	}

	@RequestMapping("/post/update")
	public String update(String id, String subject, String content) {
		this.postService.update(id, subject, content);
		return "success";
	}
}