package myaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.Totalresultinfo;
import bbs.database.hibernate.Userinfo;
import bbs.database.hibernate.Voteinfo;
import bbs.database.hibernate.Voteselectinfo;
import bean.ShowAllVotePageDao;
import bean.VoteListInfo;

import com.opensymphony.xwork2.ActionSupport;

public class ShowVotesAction extends ActionSupport{
	private String curusername;
	private String create_time;
	private String vote_title;//������ŵı���
	private String vote_des;
	private Integer part_num;
    private Integer vote_createrID;
    private String vote_creater;
    private Integer voteID;//ͶƱ���
    private List<VoteListInfo> vote_list;
    //�����Ƿ�ҳҪ�õ��ı���
    private List<VoteListInfo> list;
    private int pageNow = 1 ; //��ʼ��Ϊ1,Ĭ�ϴӵ�һҳ��ʼ��ʾ
    private int pageSize = 5 ; //ÿҳ��ʾ2����¼
    private int pageTotle= 1 ;//��ҳ��
    private ShowAllVotePageDao pageDao = new ShowAllVotePageDao () ;
    public String getCurusername() {
		return curusername;
	}

	public void setCurusername(String curusername) {
		this.curusername = curusername;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
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

	public Integer getPart_num() {
		return part_num;
	}

	public void setPart_num(Integer part_num) {
		this.part_num = part_num;
	}
    


	public Integer getVote_createrID() {
		return vote_createrID;
	}

	public void setVote_creater(Integer vote_createrID) {
		this.vote_createrID = vote_createrID;
	}
    
	public String getVote_creater() {
		return vote_creater;
	}

	public void setVote_creater(String vote_creater) {
		this.vote_creater = vote_creater;
	}

	public Integer getVoteID() {
		return voteID;
	}

	public void setVoteID(Integer voteID) {
		this.voteID = voteID;
	}


	public List<VoteListInfo> getVote_list() {
		return vote_list;
	}

	public void setVote_list(List<VoteListInfo> vote_list) {
		this.vote_list = vote_list;
	}
	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotle() {
		return pageTotle;
	}

	public void setPageTotle(int pageTotle) {
		this.pageTotle = pageTotle;
	}



	public List<VoteListInfo> getList() {
		return list;
	}

	public void setList(List<VoteListInfo> list) {
		this.list = list;
	}

	public String execute() throws Exception{
		Configuration config=new Configuration().configure();
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		HttpServletRequest request=ServletActionContext.getRequest();
		curusername=(String) request.getSession().getAttribute("username");
		if(curusername==null)
		{
			addFieldError("username","�Բ����㻹û�е�¼�����ȵ�¼");
			System.out.println("��ʾ�㻹���е�¼");
			return INPUT;
		}
		else{//����û��Ѿ���¼�������������е�ͶƱ��Ϣ
			System.out.println("��ʼ�������ͶƱ��Ϣ");
			List allvote=session.createQuery("from Voteinfo").list();
			///////////////////////////////////////////////////////////////
			vote_list=new ArrayList<VoteListInfo>();
			for(int i=0;i<allvote.size();i++)
			{
				part_num=0;
				VoteListInfo Info=new VoteListInfo();
				Voteinfo vote=(Voteinfo) allvote.get(i);
				vote_title=vote.getVoteTitle();
				voteID=vote.getVoteId();
				create_time=vote.getVoteCreateTime();
				Info.setVoteID(voteID);
				Info.setCreate_time(create_time);
				Info.setVote_title(vote_title);//�����voteIDҲ����VoteListInfo bean�У�����
				
				vote_createrID=vote.getUserinfo().getUserId();
				Userinfo user=new Userinfo();
				user=(Userinfo) session.load(Userinfo.class, vote_createrID);
				vote_creater=user.getUserName();//�ҵ�ͶƱ������
				Info.setVote_creater(vote_creater);
				Voteinfo vo=(Voteinfo) session.load(Voteinfo.class, voteID);
				Criteria totalresult_cr=session.createCriteria(Totalresultinfo.class);
				totalresult_cr.add(Restrictions.eq("voteinfo", vo));
				Totalresultinfo totalresult=new Totalresultinfo();
				List totallist=totalresult_cr.list();
				Iterator total_it=totallist.iterator();
				while(total_it.hasNext())
				{
				    totalresult=(Totalresultinfo) total_it.next();
					part_num=part_num+totalresult.getTotalNum();
				}
				Info.setPart_num(part_num);
				vote_list.add(Info);
				
			}
			 //�õ�ÿҳ��ʾ�Ķ���ļ���list
	        list  = pageDao.pageQuery(pageSize, pageNow,vote_list);
	        
	        //�õ���ҳ��pageTotle
	        pageTotle=pageDao.pageTotle(pageSize, pageNow);
			return SUCCESS;
		}
		
	}

}
