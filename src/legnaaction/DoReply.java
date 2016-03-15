package legnaaction;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

 

import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Replyinfo;
import bbs.database.hibernate.Topicinfo;
import bbs.database.hibernate.Userinfo;

import com.opensymphony.xwork2.ActionSupport;

public class DoReply  extends ActionSupport implements ServletRequestAware {
	 
 
	//回复的内容
	private String content;
	//被回复的主题id
	private String tid;
	private String size;
	//string num="55";
 
	private HttpServletRequest request ;
	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}
	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}

	 @Override
	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		 this.request = req ;
	}

	Session session = HibernateSessionFactory.getSession();
		//Criteria criaTopic = session.createCriteria(Topicinfo.class);
	Criteria criaUser = session.createCriteria(Userinfo.class);
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub	 
		String currName = (String) request.getSession().getAttribute("username");
		Criteria crit = session.createCriteria(Userinfo.class);
	    crit.add(Restrictions.eq("userName", currName));
	    if(currName ==null ||"".equals(currName))
		   {
			  
			   request.setAttribute("nologin", "no");
			   return "fail";
		   }
	    else{
			   if(content.equals(null)||"".equals(content))
			   {			  
				   	   
				   HibernateSessionFactory.closeSession();
				   return SUCCESS;
			   }
			   else{
					Transaction trans  = session.beginTransaction();
					List<Userinfo> user = criaUser.list();
					//Userinfo userinfo = new Userinfo();
					SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");      
					Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间      
				    String   replyTime   =   formatter.format(curDate);   
				    
				 Userinfo userinfo = (Userinfo) crit.list().iterator().next() ;
				 if(userinfo.getUserSilenced()==1)
				   {
					   HibernateSessionFactory.closeSession();
					   return "error";
				   }
				 int id = Integer.parseInt(tid);
				Topicinfo  topicinfo =  (Topicinfo) session.load(Topicinfo.class, id);
				System.out.printf("topic 的内容是："+topicinfo.getTopicTitle()+"\n");
				//第几楼
				System.out.printf(size+"\n");
				int floorNumber=Integer.parseInt(size)+1;
				String replyContent = content ;	 
				//获取当前日期时间		 
				 Replyinfo reply = new Replyinfo();
				 reply.setFloorNumber(floorNumber);
				 reply.setReplyContent(replyContent);	 
				 reply.setReplyTime(replyTime);
			    reply.setTopicinfo(topicinfo);
				reply.setUserinfo(userinfo);
				 try
				 {			 
					// trans=session.beginTransaction();
					 session.save(reply);
					 trans.commit();
					 System.out.printf("success\n");
					 
				 }catch(Exception e)
				 {
					 e.printStackTrace();
					 trans.rollback();
					 
				 }		 
				HibernateSessionFactory.closeSession();
				return SUCCESS;
	   }
	}
	}
	

}
