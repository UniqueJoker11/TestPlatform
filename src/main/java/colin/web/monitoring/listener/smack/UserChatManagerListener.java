package colin.web.monitoring.listener.smack;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManagerListener;

/**
 * 创建用户的聊天
 * @author Administrator
 *
 */
public class UserChatManagerListener implements ChatManagerListener{

	@Override
	public void chatCreated(Chat chat, boolean createdLocally) {
		// TODO Auto-generated method stub
		if (!createdLocally){
			/*try {
				chat.sendMessage("我是客户端发送的消息！！");
			} catch (NotConnectedException e) {
				// TODOAuto-generated catch block
				e.printStackTrace();
			}*/
			chat.addMessageListener(new UserLocalChatMessageListener());
		}
	}

}
