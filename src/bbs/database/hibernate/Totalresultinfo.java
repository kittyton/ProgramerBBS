package bbs.database.hibernate;

/**
 * Totalresultinfo entity. @author MyEclipse Persistence Tools
 */

public class Totalresultinfo implements java.io.Serializable {

	// Fields

	private Integer totalresultId;
	private Voteinfo voteinfo;
	private String totalSelectDes;
	private Integer totalNum;

	// Constructors

	/** default constructor */
	public Totalresultinfo() {
	}

	/** full constructor */
	public Totalresultinfo(Voteinfo voteinfo, String totalSelectDes,
			Integer totalNum) {
		this.voteinfo = voteinfo;
		this.totalSelectDes = totalSelectDes;
		this.totalNum = totalNum;
	}

	// Property accessors

	public Integer getTotalresultId() {
		return this.totalresultId;
	}

	public void setTotalresultId(Integer totalresultId) {
		this.totalresultId = totalresultId;
	}

	public Voteinfo getVoteinfo() {
		return this.voteinfo;
	}

	public void setVoteinfo(Voteinfo voteinfo) {
		this.voteinfo = voteinfo;
	}

	public String getTotalSelectDes() {
		return this.totalSelectDes;
	}

	public void setTotalSelectDes(String totalSelectDes) {
		this.totalSelectDes = totalSelectDes;
	}

	public Integer getTotalNum() {
		return this.totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

}