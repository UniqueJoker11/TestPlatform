package colin.web.monitoring.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(locations="classpath:/config/mongodb.properties")
public class MongoDbProps {

	private String mongoUrls;

	private String mongoDb;
	
	public String getMongoDb() {
		return mongoDb;
	}

	public void setMongoDb(String mongoDb) {
		this.mongoDb = mongoDb;
	}

	public String getMongoUrls() {
		return mongoUrls;
	}

	public void setMongoUrls(String mongoUrls) {
		this.mongoUrls = mongoUrls;
	}
}
