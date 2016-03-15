package bbs.database.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */

public class Userinfo implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userName;
	private String password;
	private Integer userType;
	private String userPicture;
	private String userIntroduction;
	private String userHobby;
	private String userLevel;
	private Integer userSilenced;
	private Set voteinfos = new HashSet(0);
	private Set voteresultinfos = new HashSet(0);
	private Set topicinfos = new HashSet(0);
	private Set moduleinfosForModuleAdmin1 = new HashSet(0);
	private Set moduleinfosForModuleAdmin2 = new HashSet(0);
	private Set moduleinfosForModuleAdmin3 = new HashSet(0);
	private Set replyinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Userinfo() {
	}

	/** full constructor */
	public Userinfo(String userName, String password, Integer userType,
			String userPicture, String userIntroduction, String userHobby,
			String userLevel, Integer userSilenced, Set voteinfos,
			Set voteresultinfos, Set topicinfos,
			Set moduleinfosForModuleAdmin1, Set moduleinfosForModuleAdmin2,
			Set moduleinfosForModuleAdmin3, Set replyinfos) {
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.userPicture = userPicture;
		this.userIntroduction = userIntroduction;
		this.userHobby = userHobby;
		this.userLevel = userLevel;
		this.userSilenced = userSilenced;
		this.voteinfos = voteinfos;
		this.voteresultinfos = voteresultinfos;
		this.topicinfos = topicinfos;
		this.moduleinfosForModuleAdmin1 = moduleinfosForModuleAdmin1;
		this.moduleinfosForModuleAdmin2 = moduleinfosForModuleAdmin2;
		this.moduleinfosForModuleAdmin3 = moduleinfosForModuleAdmin3;
		this.replyinfos = replyinfos;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserPicture() {
		return this.userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

	public String getUserIntroduction() {
		return this.userIntroduction;
	}

	public void setUserIntroduction(String userIntroduction) {
		this.userIntroduction = userIntroduction;
	}

	public String getUserHobby() {
		return this.userHobby;
	}

	public void setUserHobby(String userHobby) {
		this.userHobby = userHobby;
	}

	public String getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getUserSilenced() {
		return this.userSilenced;
	}

	public void setUserSilenced(Integer userSilenced) {
		this.userSilenced = userSilenced;
	}

	public Set getVoteinfos() {
		return this.voteinfos;
	}

	public void setVoteinfos(Set voteinfos) {
		this.voteinfos = voteinfos;
	}

	public Set getVoteresultinfos() {
		return this.voteresultinfos;
	}

	public void setVoteresultinfos(Set voteresultinfos) {
		this.voteresultinfos = voteresultinfos;
	}

	public Set getTopicinfos() {
		return this.topicinfos;
	}

	public void setTopicinfos(Set topicinfos) {
		this.topicinfos = topicinfos;
	}

	public Set getModuleinfosForModuleAdmin1() {
		return this.moduleinfosForModuleAdmin1;
	}

	public void setModuleinfosForModuleAdmin1(Set moduleinfosForModuleAdmin1) {
		this.moduleinfosForModuleAdmin1 = moduleinfosForModuleAdmin1;
	}

	public Set getModuleinfosForModuleAdmin2() {
		return this.moduleinfosForModuleAdmin2;
	}

	public void setModuleinfosForModuleAdmin2(Set moduleinfosForModuleAdmin2) {
		this.moduleinfosForModuleAdmin2 = moduleinfosForModuleAdmin2;
	}

	public Set getModuleinfosForModuleAdmin3() {
		return this.moduleinfosForModuleAdmin3;
	}

	public void setModuleinfosForModuleAdmin3(Set moduleinfosForModuleAdmin3) {
		this.moduleinfosForModuleAdmin3 = moduleinfosForModuleAdmin3;
	}

	public Set getReplyinfos() {
		return this.replyinfos;
	}

	public void setReplyinfos(Set replyinfos) {
		this.replyinfos = replyinfos;
	}

}