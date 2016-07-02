package colin.web.monitoring.controller.openfire;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.beetl.core.om.ObjectUtil;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterGroup;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import colin.web.monitoring.controller.CommonController;
import colin.web.monitoring.core.bo.UserChatBo;
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

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView userOpenfireLogin(String username, String password) {
		UserChatBo userInfo = (UserChatBo) super.getHttpSession().getAttribute(
				"userLogin");
		ModelAndView result = new ModelAndView();
		if (ObjectUtils.isEmpty(userInfo)) {
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
				userInfo = new UserChatBo();
				userInfo.setUsername(username);
				// TODO 可以通过添加缓存中间件进行缓存处理
				// 获取好友列表
				Roster userRoster = Roster.getInstanceFor(ofConn);
				// 所有好友
				Set<RosterEntry> rosterEntry = userRoster.getEntries();
				userInfo.setUserRoster(rosterEntry);
				// 所有群
				Collection<RosterGroup> groupEntry = userRoster.getGroups();
				userInfo.setGroupRoster(groupEntry);
				/* Roster userRoster=ofConn. */
			} catch (Exception e) {
				result.setViewName("redirect:login");
				return result;
			}
		}
		result.addObject("userInfo", userInfo);
		result.setViewName("openfire/openfire_client_chat");
		return result;
	}
}
