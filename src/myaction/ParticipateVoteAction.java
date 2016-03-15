package myaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.Userinfo;
import bbs.database.hibernate.Voteinfo;
import bbs.database.hibernate.Voteselectinfo;

import com.opensymphony.xwork2.ActionSupport;

public class ParticipateVoteAction extends ActionSupport{
	private String voteTitle;
    private Integer voteID;
    private String vote_title;
    private String vote_des;
    private String vote_deadline;
    private List<String> voteselect;//取得关于该投票的所有选项信息
	public String getVoteTitle() {
		return voteTitle;
	}

	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}
	
	public String getVote_title() {
		return vote_title;
	}

	public void setVote_title(String vote_title) {
		this.vote_title = vote_title;
	}

	public String getVote_des() {
		return vote_des;
	}

	public void setVote_des(String vote_des) {
		this.vote_des = vote_des;
	}

	public List<String> getVoteselect() {
		return voteselect;
	}

	public void setVoteselect(List<String> voteselect) {
		this.voteselect = voteselect;
	}

	public Integer getVoteID() {
		return voteID;
	}

	public void setVoteID(Integer voteID) {
		this.voteID = voteID;
	}

	public String getVote_deadline() {
		return vote_deadline;
	}

	public void setVote_deadline(String vote_deadline) {
		this.vote_deadline = vote_deadline;
	}

	public String execute() throws Exception{
	    Configuration config=new Configuration().configure();
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		voteselect=new ArrayList<String>();//勿忘初始化
		Voteinfo vote=new Voteinfo();
		vote=(Voteinfo) session.load(Voteinfo.class, voteID);
		vote_title=vote.getVoteTitle();
		vote_des=vote.getVoteDescription();
		vote_deadline=vote.getVoteDeadline();
		session.close();
		session=sessionFactory.openSession();
		Voteselectinfo select=new Voteselectinfo();
		Criteria select_cr=session.createCriteria(Voteselectinfo.class);
		select_cr.add(Restrictions.eq("voteinfo", vote));//注意第一个参数是属性，要和映射文件一致
		List selectlist=select_cr.list();
		Iterator it=selectlist.iterator();
		while(it.hasNext())
		{
			
			select=(Voteselectinfo) it.next();
			voteselect.add(select.getSelectDes());
		}
		return SUCCESS;
	}

}
