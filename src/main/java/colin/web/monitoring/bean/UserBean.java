package colin.web.monitoring.bean;

public class UserBean {

	private Long userid;
	private String birthday;
	private String createdate;
	private String creator_id;
	private String creator_rname;
	private String dept;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}

	public String getCreator_rname() {
		return creator_rname;
	}

	public void setCreator_rname(String creator_rname) {
		this.creator_rname = creator_rname;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}
