package kr.co.anajo.web.community.board;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardRepository extends MongoRepository<Board, String>{

}
