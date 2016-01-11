package kr.co.anajo.web.resource;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResourceRepository extends MongoRepository<Resource, String> {

	List<Resource> findByType(ResourceType type);
}
