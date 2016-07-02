package colin.web.monitoring.config;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import colin.web.monitoring.props.MongoDbProps;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Configuration
public class MongoDBAggregateConfig {

	@Autowired
	private MongoDbProps mongoDbProps;

	@Bean
	public MongoTemplate configMongoAggregate() {
		MongoTemplate mongoTemplate = new MongoTemplate(this.configSimpleMongoDbFactory());
		return mongoTemplate;
	}

	public MongoDbFactory configSimpleMongoDbFactory(){
		MongoDbFactory mongoDbFactory=new SimpleMongoDbFactory(this.configMongoClient(),mongoDbProps.getMongoDb());
		return mongoDbFactory;
	}
	public MongoClient configMongoClient() {
		MongoClient mongoClient = null;
		try {
			String[] mongoUris = mongoDbProps.getMongoUrls().split(",");
			List<ServerAddress> mongoAddressList = new ArrayList<ServerAddress>();
			for (String mongoUri : mongoUris) {
				String[] mongoUriParts = mongoUri.split(":");
				mongoAddressList.add(new ServerAddress(mongoUriParts[0],
						Integer.valueOf(mongoUriParts[1])));
			}
			mongoClient = new MongoClient(mongoAddressList);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return mongoClient;
	}
}
