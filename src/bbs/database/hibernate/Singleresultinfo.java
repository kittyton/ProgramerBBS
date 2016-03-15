package bbs.database.hibernate;

/**
 * Singleresultinfo entity. @author MyEclipse Persistence Tools
 */

public class Singleresultinfo implements java.io.Serializable {

	// Fields

	private Integer singleResultId;
	private Voteinfo voteinfo;
	private Userinfo userinfo;
	private String selectDes;
	private String voteTime;

	// Constructors

	/** default constructor */
	public Singleresultinfo() {
	}

	/** full constructor */
	public Singleresultinfo(Voteinfo voteinfo, Userinfo userinfo,
			String selectDes, String voteTime) {
		this.voteinfo = voteinfo;
		this.userinfo = userinfo;
		this.selectDes = selectDes;
		this.voteTime = voteTime;
	}

	// Property accessors

	public Integer getSingleResultId() {
		return this.singleResultId;
	}

	public void setSingleResultId(Integer singleResultId) {
		this.singleResultId = singleResultId;
	}

	public Voteinfo getVoteinfo() {
		return this.voteinfo;
	}

	public void setVoteinfo(Voteinfo voteinfo) {
		this.voteinfo = voteinfo;
	}

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public String getSelectDes() {
		return this.selectDes;
	}

	public void setSelectDes(String selectDes) {
		this.selectDes = selectDes;
	}

	public String getVoteTime() {
		return this.voteTime;
	}

	public void setVoteTime(String voteTime) {
		this.voteTime = voteTime;
	}

}