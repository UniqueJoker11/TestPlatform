package colin.web.monitoring.service.impl;

import java.util.Collection;

import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import colin.web.monitoring.listener.smack.UserRosterManageListener;
import colin.web.monitoring.props.OFProps;
import colin.web.monitoring.service.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService {

	private final static Logger logger = LoggerFactory
			.getLogger(MessageServiceImpl.class);
	@Autowired
	private OFProps ofProps;

	@Autowired
	private UserRosterManageListener userRosterManageListener;

	@Override
	public XMPPTCPConnection initXMPPTCPConnectionConfiguration(
			String username, String password) {
		try {
			XMPPTCPConnection ofConn = new XMPPTCPConnection(
					this.buildXMPPTCPConnectionConfiguration(username, password));
			return ofConn;
		} catch (Exception e) {
			logger.error("用户" + username + "登录失败，失败原因是:", e);
			return null;
		}
	}

	/**
	 * 建立用户连接配置
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private XMPPTCPConnectionConfiguration buildXMPPTCPConnectionConfiguration(
			String username, String password) {
		XMPPTCPConnectionConfiguration ofConfig = XMPPTCPConnectionConfiguration
				.builder().setSecurityMode(SecurityMode.disabled)
				.setUsernameAndPassword(username, password)
				.setServiceName(ofProps.getOfServiceName())
				.setHost(ofProps.getOfAddress())
				.setPort(Integer.valueOf(ofProps.getOfPort()))
				.setConnectTimeout(Integer.valueOf(ofProps.getOfTimeout()))
				.build();
		return ofConfig;
	}

	@Override
	public Collection<RosterEntry> getUserRoster(XMPPTCPConnection conn) {
		Roster roster = Roster.getInstanceFor(conn);
		// 添加好友变动情况监听器
		roster.addRosterListener(userRosterManageListener);
		// 获取到好友列表
		Collection<RosterEntry> entries = roster.getEntries();
		return entries;
	}

	@Override
	public void sendMessageTo(XMPPTCPConnection conn, String fromUser,
			String toUser, String msg) {

	}
}
