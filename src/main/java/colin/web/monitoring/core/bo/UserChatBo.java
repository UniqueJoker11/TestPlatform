package colin.web.monitoring.core.bo;

import java.util.Collection;
import java.util.Set;

import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterGroup;

public class UserChatBo {

	private String username;
	private String password;
	private Set<RosterEntry> userRoster;
	private Collection<RosterGroup> groupRoster;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<RosterEntry> getUserRoster() {
		return userRoster;
	}
	public void setUserRoster(Set<RosterEntry> userRoster) {
		this.userRoster = userRoster;
	}
	public Collection<RosterGroup> getGroupRoster() {
		return groupRoster;
	}
	public void setGroupRoster(Collection<RosterGroup> groupRoster) {
		this.groupRoster = groupRoster;
	}
}
