package colin.web.monitoring.listener.smack;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.springframework.util.StringUtils;

public class UserLocalChatMessageListener implements ChatMessageListener{

	private String msg;
	public UserLocalChatMessageListener(){
		
	}
	public UserLocalChatMessageListener (String msg){
		this.msg=msg;
	}
	@Override
	public void processMessage(Chat chat, Message message) {
		if(StringUtils.isEmpty(message.getBody())){
			message.setBody("测试消息");
		}else{
			message.setBody(msg);
		}
		try {
			chat.sendMessage(message);
		} catch (NotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
