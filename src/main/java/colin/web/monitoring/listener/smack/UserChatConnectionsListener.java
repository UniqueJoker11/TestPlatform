package colin.web.monitoring.listener.smack;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.Future;

import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import colin.web.monitoring.controller.openfire.OpenfireClientLogin;

public class UserChatConnectionsListener implements ConnectionListener {
	private final static Logger logger = LoggerFactory.getLogger(OpenfireClientLogin.class);

	@Override
	public void connected(XMPPConnection connection) {
		if (connection.isConnected()) {
			logger.info("成功连接服务端");
		}
	}

	@Override
	public void authenticated(XMPPConnection connection, boolean resumed) {
		if (!connection.isAuthenticated()) {
			logger.warn("用户身份认证失败！");
		}
	}

	@Override
	public void connectionClosed() {
		logger.info("断开服务端连接");
	}

	@Override
	public void connectionClosedOnError(Exception e) {
		logger.error("出现错误断开连接服务端", e);
	}

	@Override
	public void reconnectionSuccessful() {

	}

	@Override
	public void reconnectingIn(int seconds) {
		// TODOAuto-generated method stub

	}

	@Override
	public void reconnectionFailed(Exception e) {
		// TODOAuto-generated method stub
	}

}
