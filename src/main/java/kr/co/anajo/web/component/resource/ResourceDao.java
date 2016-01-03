package kr.co.anajo.web.component.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceDao {

	private static final String COLLECTION_NAME = "resources";

	@Autowired
	MongoTemplate mongoTemplate;

	public void createResource(Resource resource) {
		this.mongoTemplate.insert(resource, COLLECTION_NAME);
	}

	public Resource getResource(String id) {
		return this.mongoTemplate.findById(id, Resource.class, COLLECTION_NAME);
	}

	public Resource getResource(Query query, Class<? extends Resource> T) {
		return this.mongoTemplate.findOne(query, T, COLLECTION_NAME);
	}

	public List<? extends Resource> getResources(ResourceType type) {
		Query query = new Query(new Criteria("type").is(type));
		return this.mongoTemplate.find(query, Resource.class, COLLECTION_NAME);
	}

	public List<Resource> getResources(String parentId) {
		Query query = new Query(new Criteria("parentId").is(parentId));
		return this.mongoTemplate.find(query, Resource.class, COLLECTION_NAME);
	}

	public void updateResource(Resource resource) {
		this.mongoTemplate.save(resource, COLLECTION_NAME);
	}

	public void deleteResource(String id) {
		Query query = new Query(new Criteria("_id").is(id));
		this.mongoTemplate.remove(query, COLLECTION_NAME);
	}

}
