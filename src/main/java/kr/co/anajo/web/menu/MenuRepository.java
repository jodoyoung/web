package kr.co.anajo.web.menu;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuRepository extends MongoRepository<Menu, String> {

}