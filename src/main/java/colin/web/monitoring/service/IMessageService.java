package colin.web.monitoring.service;

import java.util.Collection;

import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

/**
 * 
 * @author Administrator
 *
 */
public interface IMessageService {
	/**
	 * 初始化聊天配置
	 * @param username
	 * @param password
	 * @return
	 */
	public XMPPTCPConnection initXMPPTCPConnectionConfiguration(String username,String password);
	
	/**
	 * 获取用户的好友关系
	 * @param conn
	 * @param fromUser
	 * @return
	 */
	public Collection<RosterEntry> getUserRoster(XMPPTCPConnection conn);
	
	/**
	 * 发送聊天消息
	 * @param conn
	 * @param toUser
	 * @param msg
	 */
	public void sendMessageTo(XMPPTCPConnection conn,String fromUser,String toUser,String msg);
	
}
