package kr.co.anajo.web.community.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public void create(String id, String subject, String content) {
		Post post = new Post();
		post.setId(id);
		post.setSubject(subject);
		post.setContent(content);
		this.postRepository.insert(post);
	}

	public Post read(String id) {
		return this.postRepository.findOne(id);
	}

	public List<Post> readAll() {
		return this.postRepository.findAll();
	}

	public void update(String id, String subject, String content) {
		Post post = this.read(id);
		post.setSubject(subject);
		post.setContent(content);
		this.postRepository.save(post);
	}

	public void delete(String id) {
		this.postRepository.delete(id);
	}

}
