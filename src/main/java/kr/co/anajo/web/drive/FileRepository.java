package kr.co.anajo.web.drive;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<File, String>{

}