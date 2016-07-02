package colin.web.monitoring.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import colin.web.monitoring.core.dao.inter.ICommonDao;

@Repository
public class CommonMongoDao implements ICommonDao{

	@Autowired
	private MongoTemplate mongoTemplate;
	public <T> void saveObj(T entity){
		mongoTemplate.insert(entity, "person");
	}
}
