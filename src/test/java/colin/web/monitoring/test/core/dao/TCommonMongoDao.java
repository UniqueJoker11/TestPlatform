package colin.web.monitoring.test.core.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import colin.web.monitoring.core.dao.CommonMongoDao;
import colin.web.monitoring.core.pojo.Person;
import colin.web.monitoring.test.TCommonConfig;

public class TCommonMongoDao extends TCommonConfig{

	@Autowired
	private CommonMongoDao mongoDao;
	@Test
	public void testSave(){
		Person person=new Person();
		person.setName("batman");
		person.setAge(23);
		mongoDao.saveObj(person);
	}
}
