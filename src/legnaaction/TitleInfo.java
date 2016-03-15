package legnaaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;

 
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Replyinfo;
import bbs.database.hibernate.Topicinfo;
import bbs.database.hibernate.Userinfo;
import bbs.myother.Cmp;
import bbs.myother.MyTitleInfo;

import com.opensymphony.xwork2.ActionSupport;

public class TitleInfo extends ActionSupport{
	private String tid;
	private List<MyTitleInfo> teinfo; 
	private String myname;
	private String title;
	private Topicinfo mytcio;
	private int size;
	
	
	 
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Topicinfo getMytcio() {
		return mytcio;
	}

	public void setMytcio(Topicinfo mytcio) {
		this.mytcio = mytcio;
	}

	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTid() {
		return tid;
	}
 
	public void setTid(String tid) {
		this.tid = tid;
	}
 
	public List<MyTitleInfo> getTeinfo() {
		return teinfo;
	}

	public void setTeinfo(List<MyTitleInfo> teinfo) {
		this.teinfo = teinfo;
	}
	Session searchsn = HibernateSessionFactory.getSession();
	Criteria cria = searchsn.createCriteria(Topicinfo.class);
    Replyinfo myryinfo;
	Userinfo tcurinfo;
	Userinfo ryurinfo;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		List<Topicinfo> tcinfo = cria.list(); 
		
		Set<Replyinfo> rinfo;
		List<Replyinfo> rinfolt = new ArrayList<Replyinfo>();
		
		teinfo = new ArrayList<MyTitleInfo>();
		for(Topicinfo t:tcinfo)
		{
			if(t.getTopicId().toString().equals(tid))
			{
				mytcio=t;
				rinfo=t.getReplyinfos();
				size = rinfo.size();
				tcurinfo=t.getUserinfo();
				title = t.getTopicTitle();
				myname = tcurinfo.getUserName();
				rinfolt.addAll(rinfo);				 
				Cmp comparator=new Cmp();				 
				Collections.sort(rinfolt, comparator);
				for(Replyinfo r:rinfolt)
				{
					MyTitleInfo my = new MyTitleInfo();
					my.setMyryinfo(r);					 
					ryurinfo = r.getUserinfo();					 
					my.setRyurinfo(ryurinfo);
					teinfo.add(my);					 
				}
				return SUCCESS;			
			}
			
		}
		HibernateSessionFactory.closeSession();
		return SUCCESS;
	}
	
	

}
