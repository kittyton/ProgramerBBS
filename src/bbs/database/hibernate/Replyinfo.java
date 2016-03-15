package bbs.database.hibernate;

/**
 * Replyinfo entity. @author MyEclipse Persistence Tools
 */

public class Replyinfo implements java.io.Serializable {

	// Fields

	private Integer replyId;
	private Userinfo userinfo;
	private Topicinfo topicinfo;
	private String replyContent;
	private String replyTime;
	private String replyPicture;
	private Integer floorNumber;

	// Constructors

	/** default constructor */
	public Replyinfo() {
	}

	/** full constructor */
	public Replyinfo(Userinfo userinfo, Topicinfo topicinfo,
			String replyContent, String replyTime, String replyPicture,
			Integer floorNumber) {
		this.userinfo = userinfo;
		this.topicinfo = topicinfo;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
		this.replyPicture = replyPicture;
		this.floorNumber = floorNumber;
	}

	// Property accessors

	public Integer getReplyId() {
		return this.replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public Topicinfo getTopicinfo() {
		return this.topicinfo;
	}

	public void setTopicinfo(Topicinfo topicinfo) {
		this.topicinfo = topicinfo;
	}

	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getReplyPicture() {
		return this.replyPicture;
	}

	public void setReplyPicture(String replyPicture) {
		this.replyPicture = replyPicture;
	}

	public Integer getFloorNumber() {
		return this.floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

}