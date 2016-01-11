package kr.co.anajo.web.community.post;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String>{

}