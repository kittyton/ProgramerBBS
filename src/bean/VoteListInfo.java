package bean;

public class VoteListInfo {
	private String vote_title;
	private String vote_creater;
	private String create_time;
	private Integer part_num;
	private Integer voteID;
	public String getVote_creater() {
		return vote_creater;
	}
	public void setVote_creater(String vote_creater) {
		this.vote_creater = vote_creater;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Integer getPart_num() {
		return part_num;
	}
	public void setPart_num(Integer part_num) {
		this.part_num = part_num;
	}
	public VoteListInfo(){
		
	}

	public String getVote_title() {
		return vote_title;
	}
	public void setVote_title(String vote_title) {
		this.vote_title = vote_title;
	}
	public Integer getVoteID() {
		return voteID;
	}
	public void setVoteID(Integer voteID) {
		this.voteID = voteID;
	}
	

}
