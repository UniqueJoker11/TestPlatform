package colin.web.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication(scanBasePackages = "colin.web.monitoring.**")
@EnableScheduling
public class Application {
	private static final Logger logger = LoggerFactory
			.getLogger(Application.class);

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		logger.info("应用服务器启动开始");
		SpringApplication springApplication=new SpringApplication(Application.class);
		springApplication.setWebEnvironment(true);
		springApplication.run(Application.class);
		logger.info("应用服务器启动成功，监听端口号是：{}",9999);
	}

}
