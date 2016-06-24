package colin.web.monitoring.controller.openfire;

import java.io.IOException;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import colin.web.monitoring.controller.CommonController;
import colin.web.monitoring.props.OFProps;

@Controller
@RequestMapping("openfire/client")
public class OpenfireClientLogin extends CommonController {

	private final static Logger logger = LoggerFactory
			.getLogger(OpenfireClientLogin.class);
	@Autowired
	private OFProps ofProps;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String ofUserLogin() {
		return "openfire/openfire_client_login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String userOpenfireLogin(String username, String password) {
		XMPPTCPConnectionConfiguration ofConfig = XMPPTCPConnectionConfiguration
				.builder().setSecurityMode(SecurityMode.disabled)
				.setUsernameAndPassword(username, password)
				.setServiceName(ofProps.getOfServiceName())
				.setHost(ofProps.getOfAddress())
				.setPort(Integer.valueOf(ofProps.getOfPort()))
				.setConnectTimeout(Integer.valueOf(ofProps.getOfTimeout()))
				.build();
		AbstractXMPPConnection ofConn = new XMPPTCPConnection(ofConfig);
		try {
			ofConn.connect().login();
			logger.info("用户登录Openfire成功！");
            //获取好友列表
			ofConn.ge
			return "openfire/openfire_client_chat";
		} catch (SmackException e) {
			e.printStackTrace();
			return "redirect:login";
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:login";
		} catch (XMPPException e) {
			e.printStackTrace();
			return "redirect:login";
		}catch (Exception e){
			e.printStackTrace();
			return "redirect:login";
		}

	}
}
