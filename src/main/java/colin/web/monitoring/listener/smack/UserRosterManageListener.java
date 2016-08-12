package colin.web.monitoring.listener.smack;

import java.util.Collection;

import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.RosterListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public class UserRosterManageListener implements RosterListener{
	private static final Logger logger =LoggerFactory.getLogger(UserRosterManageListener.class);
    /**
     * 已添加的好友
     */
	@Override
	public void entriesAdded(Collection<String> addresses) {
		// TODO Auto-generated method stub
		logger.info("已添加的好友列表"+addresses);
	}
    /**
     * 好友信息更新
     */
	@Override
	public void entriesUpdated(Collection<String> addresses) {
		// TODO Auto-generated method stub
		logger.info("已更新的好友列表"+addresses);
	}

	/**
	 * 删除好友
	 */
	@Override
	public void entriesDeleted(Collection<String> addresses) {
		// TODO Auto-generated method stub
		logger.info("删除后的好友列表"+addresses);
	}

	/**
	 * 好友状态改变
	 */
	@Override
	public void presenceChanged(Presence presence) {
		// TODO Auto-generated method stub
		logger.info("好友的状态"+presence.getFrom()+"变为"+presence.getStatus());
	}

}
