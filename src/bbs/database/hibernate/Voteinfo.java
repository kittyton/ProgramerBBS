package bbs.database.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Voteinfo entity. @author MyEclipse Persistence Tools
 */

public class Voteinfo implements java.io.Serializable {

	// Fields

	private Integer voteId;
	private Userinfo userinfo;
	private String voteTitle;
	private String voteDescription;
	private String voteCreateTime;
	private String voteDeadline;
	private Set voteselectinfos = new HashSet(0);
	private Set singleresultinfos = new HashSet(0);
	private Set totalresultinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Voteinfo() {
	}

	/** full constructor */
	public Voteinfo(Userinfo userinfo, String voteTitle,
			String voteDescription, String voteCreateTime, String voteDeadline,
			Set voteselectinfos, Set singleresultinfos, Set totalresultinfos) {
		this.userinfo = userinfo;
		this.voteTitle = voteTitle;
		this.voteDescription = voteDescription;
		this.voteCreateTime = voteCreateTime;
		this.voteDeadline = voteDeadline;
		this.voteselectinfos = voteselectinfos;
		this.singleresultinfos = singleresultinfos;
		this.totalresultinfos = totalresultinfos;
	}

	// Property accessors

	public Integer getVoteId() {
		return this.voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public Userinfo getUserinfo() {
		return this.userinfo;
	}

	public void setUserinfo(Userinfo userinfo) {
		this.userinfo = userinfo;
	}

	public String getVoteTitle() {
		return this.voteTitle;
	}

	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}

	public String getVoteDescription() {
		return this.voteDescription;
	}

	public void setVoteDescription(String voteDescription) {
		this.voteDescription = voteDescription;
	}

	public String getVoteCreateTime() {
		return this.voteCreateTime;
	}

	public void setVoteCreateTime(String voteCreateTime) {
		this.voteCreateTime = voteCreateTime;
	}

	public String getVoteDeadline() {
		return this.voteDeadline;
	}

	public void setVoteDeadline(String voteDeadline) {
		this.voteDeadline = voteDeadline;
	}

	public Set getVoteselectinfos() {
		return this.voteselectinfos;
	}

	public void setVoteselectinfos(Set voteselectinfos) {
		this.voteselectinfos = voteselectinfos;
	}

	public Set getSingleresultinfos() {
		return this.singleresultinfos;
	}

	public void setSingleresultinfos(Set singleresultinfos) {
		this.singleresultinfos = singleresultinfos;
	}

	public Set getTotalresultinfos() {
		return this.totalresultinfos;
	}

	public void setTotalresultinfos(Set totalresultinfos) {
		this.totalresultinfos = totalresultinfos;
	}

}