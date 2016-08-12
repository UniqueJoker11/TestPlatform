package colin.web.monitoring.test.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import colin.web.monitoring.service.IMessageService;
import colin.web.monitoring.test.TCommonConfig;

public class TMessageServcieImpl extends TCommonConfig {

	private static final Logger logger = LoggerFactory
			.getLogger(TMessageServcieImpl.class);
	@Autowired
	private IMessageService messageService;

	/**
	 * 初始化连接
	 * @throws XMPPException
	 * @throws SmackException
	 * @throws IOException
	 */
	@Test
	public void initXMPPTCPConnectionConfiguration() throws XMPPException, SmackException, IOException {
		XMPPTCPConnection connection = messageService.initXMPPTCPConnectionConfiguration("13000000001",
				"123456");
		connection.connect().login();
		// TODO 可以通过添加缓存中间件进行缓存处理
		AccountManager accountManager = AccountManager.getInstance(connection);
		Set<String> accountAttrs = accountManager.getAccountAttributes();
		for (String accountAttr : accountAttrs) {
			logger.info("用户{},属性{}的值是{}", "13000000001", accountAttr,
					accountManager.getAccountAttribute(accountAttr));
		}
	}
	/**
	 * 获取好友列表
	 */
	@Test
	public void getUserOFRoster(){
		XMPPTCPConnection connection = messageService.initXMPPTCPConnectionConfiguration("13000000001",
				"123456");
		try {
			connection.connect();
			Collection<RosterEntry> rosterUser=messageService.getUserRoster(connection);
			for(RosterEntry roster:rosterUser){
				logger.info("好友姓名是"+roster.getName());
			}
			
		} catch (XMPPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
