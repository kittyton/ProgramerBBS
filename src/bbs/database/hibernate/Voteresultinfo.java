package bbs.database.hibernate;

/**
 * Voteresultinfo entity. @author MyEclipse Persistence Tools
 */

public class Voteresultinfo implements java.io.Serializable {

	// Fields

	private Integer resultId;
	private Voteinfo voteinfo;
	private Userinfo userinfo;
	private Integer selectId;
	private String selectItem;
	private Integer selectNumber;
	private String voteTime;

	// Constructors

	/** default constructor */
	public Voteresultinfo() {
	}

	/** full constructor */
	public Voteresultinfo(Voteinfo voteinfo, Userinfo userinfo,
			Integer selectId, String selectItem, Integer selectNumber,
			String voteTime) {
		this.voteinfo = voteinfo;
		this.userinfo = userinfo;
		this.selectId = selectId;
		this.selectItem = selectItem;
		this.selectNumber = selectNumber;
		this.voteTime = voteTime;
	}

	// Property accessors

	public Integer getResultId() {
		return this.resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
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

	public Integer getSelectId() {
		return this.selectId;
	}

	public void setSelectId(Integer selectId) {
		this.selectId = selectId;
	}

	public String getSelectItem() {
		return this.selectItem;
	}

	public void setSelectItem(String selectItem) {
		this.selectItem = selectItem;
	}

	public Integer getSelectNumber() {
		return this.selectNumber;
	}

	public void setSelectNumber(Integer selectNumber) {
		this.selectNumber = selectNumber;
	}

	public String getVoteTime() {
		return this.voteTime;
	}

	public void setVoteTime(String voteTime) {
		this.voteTime = voteTime;
	}

}