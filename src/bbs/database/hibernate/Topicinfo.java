package bbs.database.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Topicinfo entity. @author MyEclipse Persistence Tools
 */

public class Topicinfo implements java.io.Serializable {

	// Fields

	private Integer topicId;
	private Userinfo userinfo;
	private Childmoduleinfo childmoduleinfo;
	private String topicContent;
	private String topicPublicTime;
	private String topicPicture;
	private String topicTitle;
	private Set replyinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Topicinfo() {
	}

	/** full constructor */
	public Topicinfo(Userinfo userinfo, Childmoduleinfo childmoduleinfo,
			String topicContent, String topicPublicTime, String topicPicture,
			String topicTitle, Set replyinfos) {
		this.userinfo = userinfo;
		this.childmoduleinfo = childmoduleinfo;
		this.topicContent = topicContent;
		this.topicPublicTime = topicPublicTime;
		this.topicPicture = topicPicture;
		this.topicTitle = topicTitle;
		this.replyinfos = replyinfos;
	}

	// Property accessors

	public Integer getTopicId() {
		return this.topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public Childmoduleinfo getChildmoduleinfo() {
		return this.childmoduleinfo;
	}

	public void setChildmoduleinfo(Childmoduleinfo childmoduleinfo) {
		this.childmoduleinfo = childmoduleinfo;
	}

	public String getTopicContent() {
		return this.topicContent;
	}

	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	public String getTopicPublicTime() {
		return this.topicPublicTime;
	}

	public void setTopicPublicTime(String topicPublicTime) {
		this.topicPublicTime = topicPublicTime;
	}

	public String getTopicPicture() {
		return this.topicPicture;
	}

	public void setTopicPicture(String topicPicture) {
		this.topicPicture = topicPicture;
	}

	public String getTopicTitle() {
		return this.topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public Set getReplyinfos() {
		return this.replyinfos;
	}

	public void setReplyinfos(Set replyinfos) {
		this.replyinfos = replyinfos;
	}

}