package legnaaction;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

 
import bbs.database.hibernate.Childmoduleinfo;
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Topicinfo;
import bbs.database.hibernate.Userinfo;

import com.opensymphony.xwork2.ActionSupport;

public class DoPost extends ActionSupport implements ServletRequestAware {
	private String head;
	private String body;
	private String choose;
	private String state;
	private List<Childmoduleinfo>  module;
	private HttpServletRequest request ;
	
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
	public List<Childmoduleinfo> getModule() {
		return module;
	}
	public void setModule(List<Childmoduleinfo> module) {
		this.module = module;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		// TODO Auto-generated method stub
		this.request = req ;
		
	}

	Session session = HibernateSessionFactory.getSession();
	Criteria cria = session.createCriteria(Childmoduleinfo.class);
	 
	@Override
	public String execute() throws Exception {
		
		   module = cria.list();		   
		   if(state!=null&&state.equals("ok"))
			{
				   int moduleid = Integer.parseInt(choose);
				   Childmoduleinfo childmoduleinfo = (Childmoduleinfo) session.load(Childmoduleinfo.class, moduleid);
				  
				   String currName = (String) request.getSession().getAttribute("username");
				   if(currName ==null ||"".equals(currName))
				   {
					  
					   request.setAttribute("illegal", "no");
					   return "fail";
				   }
			 
				   else{
					   
					   Criteria crit = session.createCriteria(Userinfo.class);
				       crit.add(Restrictions.eq("userName", currName));
					   Userinfo curuser = (Userinfo) crit.list().iterator().next() ;
					   if(curuser.getUserSilenced()==1)
					   {
						   HibernateSessionFactory.closeSession();
						   return "error";
					   }
					   if(body == null||"".equals(body)||head == null||"".equals(head))
					   {
						   HibernateSessionFactory.closeSession();
						   return "silenced";
						   
					   }
					   SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");      
					   Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间      
					   String   PublicTime   =   formatter.format(curDate);  
					   System.out.printf(curuser.getUserName()+"\n"+PublicTime+"\n"+childmoduleinfo.getChildModuleName()+"\n"+head+body+"\n");
					   Topicinfo  newtopic =  new Topicinfo();
					   newtopic.setChildmoduleinfo(childmoduleinfo);
					   newtopic.setTopicContent(body);
					   newtopic.setTopicPublicTime(PublicTime);
					   newtopic.setTopicTitle(head);
					   newtopic.setUserinfo(curuser);
					   Transaction trans  = session.beginTransaction();
					   try
						 {			 
							 session.save(newtopic);
							 trans.commit();
							 
						 }catch(Exception e)
						 {
							 e.printStackTrace();
							 trans.rollback();
							 
						 }	
					   HibernateSessionFactory.closeSession();
							return "ok";
				}
			}
		   HibernateSessionFactory.closeSession();
		   return SUCCESS;
	}
	

}
