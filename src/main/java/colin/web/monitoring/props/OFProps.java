package colin.web.monitoring.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ofenfire属性类
 * 
 * @author Administrator
 *
 */
@Component
@ConfigurationProperties(locations = "classpath:config/openfire.properties")
public class OFProps {

	private String ofAddress;
	private String ofPort;
	private String ofRetryNum;
	private String ofTimeout;
	private String ofServiceName;
	public String getOfAddress() {
		return ofAddress;
	}
	public void setOfAddress(String ofAddress) {
		this.ofAddress = ofAddress;
	}
	
	public String getOfPort() {
		return ofPort;
	}
	public void setOfPort(String ofPort) {
		this.ofPort = ofPort;
	}
	public String getOfRetryNum() {
		return ofRetryNum;
	}
	public void setOfRetryNum(String ofRetryNum) {
		this.ofRetryNum = ofRetryNum;
	}
	public String getOfTimeout() {
		return ofTimeout;
	}
	public void setOfTimeout(String ofTimeout) {
		this.ofTimeout = ofTimeout;
	}
	public String getOfServiceName() {
		return ofServiceName;
	}
	public void setOfServiceName(String ofServiceName) {
		this.ofServiceName = ofServiceName;
	}
	
}
