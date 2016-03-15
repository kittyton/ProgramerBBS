package bbs.database.hibernate;

/**
 * Voteselectinfo entity. @author MyEclipse Persistence Tools
 */

public class Voteselectinfo implements java.io.Serializable {

	// Fields

	private Integer selectId;
	private Voteinfo voteinfo;
	private String selectDes;

	// Constructors

	/** default constructor */
	public Voteselectinfo() {
	}

	/** full constructor */
	public Voteselectinfo(Voteinfo voteinfo, String selectDes) {
		this.voteinfo = voteinfo;
		this.selectDes = selectDes;
	}

	// Property accessors

	public Integer getSelectId() {
		return this.selectId;
	}

	public void setSelectId(Integer selectId) {
		this.selectId = selectId;
	}

	public Voteinfo getVoteinfo() {
		return this.voteinfo;
	}

	public void setVoteinfo(Voteinfo voteinfo) {
		this.voteinfo = voteinfo;
	}

	public String getSelectDes() {
		return this.selectDes;
	}

	public void setSelectDes(String selectDes) {
		this.selectDes = selectDes;
	}

}