package kr.co.anajo.web.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;

	public void create(String id, ResourceType type, String parentId) {
		Resource resource = new Resource();
		resource.setId(id);
		resource.setType(type);
		resource.setCreateTime(new Date());
		resource.setParentId(parentId);
		this.resourceRepository.insert(resource);
	}

	public Resource get(String id) {
		return this.resourceRepository.findOne(id);
	}

	public List<Resource> getAll() {
		return this.resourceRepository.findAll();
	}

	public List<Resource> getResourcesByType(ResourceType type) {
		return this.resourceRepository.findByType(type);
	}

	public void update(String id, String parentId) {
		Resource resource = this.get(id);
		if (StringUtils.hasText(parentId)) {
			resource.setParentId(parentId);
		}
		resource.setUpdateTime(new Date());
		this.resourceRepository.save(resource);
	}

	public void delete(String id) {
		this.resourceRepository.delete(id);
	}

}
