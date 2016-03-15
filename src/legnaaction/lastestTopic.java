package legnaaction;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.*;

import org.hibernate.Criteria;
import org.hibernate.Session;

 
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Topicinfo;
import bbs.myother.MyList;

import com.opensymphony.xwork2.ActionSupport;

public class lastestTopic extends ActionSupport{
	 
	private Integer replycs;
	 
	private Calendar ca;
 
	private Map<Topicinfo,MyList> term;
	
	 

	public Calendar getCa() {
		return ca;
	}

	public void setCa(Calendar ca) {
		this.ca = ca;
	}

	 
	public Integer getReplycs() {
		return replycs;
	}

	public void setReplycs(Integer replycs) {
		this.replycs = replycs;
	} 

	public Map<Topicinfo, MyList> getTerm() {
		return term;
	}

	public void setTerm(Map<Topicinfo, MyList> term) {
		this.term = term;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
	    
		 term = new HashMap<Topicinfo,MyList>();
		 
		 Session searchsn = HibernateSessionFactory.getSession();
		 Criteria cria = searchsn.createCriteria(Topicinfo.class);
		 List<Topicinfo> setopics;
		 setopics = cria.list();
		 
		 DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 //获取当前的日期时间		 
		 Calendar c1= Calendar.getInstance();
		 ca = Calendar.getInstance();			 
		
		 for(Topicinfo tp:setopics)
		 {
			 //将数据库中的字符串转化为日期			
			 String date = tp.getTopicPublicTime();
			 c1.setTime(dt.parse(date));
			 //将当前日期转换成秒
			 MyList test = new MyList();
			 long milliseconds1 = ca.getTimeInMillis();
			 long milliseconds2 = c1.getTimeInMillis();
			 long diff = milliseconds2 - milliseconds1;	
			 //利用相差的秒数来计算相差的天数
			 long diffDays = diff / (24 * 60 * 60 * 1000);	
			 //将最近一周更新的话题加到链表中
			 if(diffDays >- 7)
			 {
				 
				 String myname = tp.getUserinfo().getUserName();
				 replycs=tp.getReplyinfos().size() ;
				 
				test.setKey(replycs);
				test.setValue(myname);
				System.out.printf(myname);
				 term.put(tp, test);
				  
			 } 
			 
		 }
		 
		 HibernateSessionFactory.closeSession();
		 return SUCCESS;
	}

	

}
