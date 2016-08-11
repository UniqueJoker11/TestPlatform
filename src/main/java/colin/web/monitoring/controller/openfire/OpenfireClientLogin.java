package colin.web.monitoring.controller.openfire;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import colin.web.monitoring.controller.CommonController;
import colin.web.monitoring.listener.smack.UserChatConnectionsListener;
import colin.web.monitoring.listener.smack.UserChatManagerListener;
import colin.web.monitoring.service.IMessageService;

@Controller
@RequestMapping("openfire/client")
public class OpenfireClientLogin extends CommonController {

	private final static Logger logger = LoggerFactory.getLogger(OpenfireClientLogin.class);

	@Autowired
	private IMessageService messageService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String ofUserLogin() {
		return "openfire/openfire_client_login";
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 * @throws XMPPException
	 * @throws IOException
	 * @throws SmackException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView userOpenfireLogin(String username, String password) throws SmackException, IOException, XMPPException, InterruptedException, ExecutionException {
		ModelAndView result = new ModelAndView();
		try {
			if (ObjectUtils.isEmpty(super.getHttpSession())) {
				initUserLogin(result, username, password);
			} else {
				if (ObjectUtils.isEmpty(super.getHttpSession().getAttribute(username))) {
					initUserLogin(result, username, password);
				}
			}
			result.setViewName("openfire/openfire_client_chat");
			return result;
		} catch (Exception e) {
			logger.warn("从Session中获取用户信息已失效");
			result.setViewName("redirect:login");
			return result;
		}

	}

	/**
	 * 
	 * 方法描述：初始化用户登录
	 *
	 * @param result
	 * @param username
	 * @param password
	 * @return void
	 * @throws XMPPException
	 * @throws IOException
	 * @throws SmackException
	 * @throws
	 * @author IM-linqiang
	 * @reviewer
	 */
	private void initUserLogin(ModelAndView result, String username, String password) throws SmackException, IOException, XMPPException {
		AbstractXMPPConnection connection = messageService.initXMPPTCPConnectionConfiguration(username, password);
		// 注册监听事件
		connection.addConnectionListener(new UserChatConnectionsListener());
		// 连接
		connection.connect();
		// 登录
		connection.login();
		// 设置用户连接
		super.getHttpSession().setAttribute(username, connection);
		result.addObject("userInfo", username);
	}

	/**
	 * 
	 * 方法描述：获取用户的好友列表
	 *
	 * @param username
	 * @return
	 * @return ModelAndView
	 * @throws
	 * @author IM-linqiang
	 * @reviewer
	 */
	@RequestMapping(value = "roster", method = RequestMethod.GET)
	public ModelAndView loadUserFriends(@RequestParam String username) {
		ModelAndView result = new ModelAndView();
		try {
			if (!ObjectUtils.isEmpty(super.getHttpSession().getAttribute(username))) {
				AbstractXMPPConnection connection = (AbstractXMPPConnection) super.getHttpSession().getAttribute(username);
				// 获取好友列表
				Roster roster = Roster.getInstanceFor(connection);
				Set<RosterEntry> entries = roster.getEntries();
				if (!entries.isEmpty()) {
					for (RosterEntry friend : entries) {
						logger.info("好友信息是" + friend.getName());
					}
				} else {
					logger.warn("无法获取好友列表");
				}
				result.setViewName("openfire/openfire_chat_friend_list");
				result.addObject("friends", entries);
			}
		} catch (Exception e) {
			result.setViewName("redirect:login");
			logger.error("清空用户连接失败！", e);
		}
		return result;
	}

	/**
	 * 
	 * 方法描述：发送消息
	 *
	 * @param username
	 * @param toUsername
	 * @param msg
	 * @return 
	 * @return ModelAndView
	 * @throws
	 * @author IM-linqiang
	 * @reviewer
	 */
	@RequestMapping(value = "talk", method = RequestMethod.GET)
	public ModelAndView sendUserMsg(String username,String toUsername,String msg) {
		ModelAndView result = new ModelAndView();
		try {
			if (!ObjectUtils.isEmpty(super.getHttpSession().getAttribute(username))) {
				AbstractXMPPConnection connection = (AbstractXMPPConnection) super.getHttpSession().getAttribute(username);
				// 聊天信息发送
				ChatManager chatManager = ChatManager.getInstanceFor(connection);
				chatManager.createChat(toUsername).sendMessage(msg);
				chatManager.addChatListener(new UserChatManagerListener());
			}
		} catch (Exception e) {
			result.setViewName("redirect:login");
			logger.error("清空用户连接失败！", e);
		}
		return result;
	}

	/**
	 * 
	 * 方法描述：退出首页
	 *
	 * @param username
	 * @return
	 * @return ModelAndView
	 * @throws
	 * @author IM-linqiang
	 * @reviewer
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public ModelAndView userLogOut(String username) {
		try {
			if (!ObjectUtils.isEmpty(super.getHttpSession().getAttribute(username))) {
				AbstractXMPPConnection connection = (AbstractXMPPConnection) super.getHttpSession().getAttribute(username);
				connection.disconnect();
				super.getHttpSession().removeAttribute(username);
			}
		} catch (Exception e) {
			logger.error("清空用户连接失败！", e);
		}
		ModelAndView result = new ModelAndView("redirect:login");
		return result;
	}
}
