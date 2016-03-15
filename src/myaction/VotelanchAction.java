package myaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.Userinfo;
import bbs.database.hibernate.Voteinfo;
import bbs.database.hibernate.Voteselectinfo;

import com.opensymphony.xwork2.ActionSupport;

public class VotelanchAction extends ActionSupport{
	private String vote_title;
	private String vote_des;
	private String name;
	private String vote_deadline;
	private  HttpServletRequest request;
	private HttpServletRequest nodeadline_request;
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

	public String getVote_deadline() {
		return vote_deadline;
	}
	public void setVote_deadline(String vote_deadline) {
		this.vote_deadline = vote_deadline;
	}
	@SuppressWarnings("deprecation")
	public String execute() throws Exception{
		Userinfo user=new Userinfo();
		Configuration config=new Configuration().configure();
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		HttpServletRequest user_request=ServletActionContext.getRequest();
		name=(String) user_request.getSession().getAttribute("username");
		nodeadline_request=ServletActionContext.getRequest();
		Boolean Isnodeadline=false;
		if(vote_deadline.isEmpty())
		{
			Isnodeadline=true;
			nodeadline_request.setAttribute("Isnodeadline", "true");
			return LOGIN; 
		}
		nodeadline_request.setAttribute("Isnodeadline", "false");
		if(name==null)
		{
			addFieldError("username","对不起，你还没有登录，请先登录");
			return INPUT;
		}
		else{//表示已经登录
			Criteria cr=session.createCriteria(Userinfo.class);
			cr.add(Restrictions.eq("userName", name));
			List user1=cr.list();
			Iterator it=user1.iterator();
			while(it.hasNext())
			{
				user=(Userinfo) it.next();
			}   //获取当前登陆的用户
			//下面是比较截止日期和当前日期,确定日期的输入是否有效
			DateFormat dt1=new SimpleDateFormat("yyyy-MM-dd");
			Calendar ca1=Calendar.getInstance();
			String curdate1=dt1.format(ca1.getTime());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date deadline=sdf.parse(vote_deadline);
			Date dd=sdf.parse(curdate1);
			Boolean deadlineIsError=false;
			request=ServletActionContext.getRequest();
			if(deadline.before(dd))
			{
				deadlineIsError=true;
				request.setAttribute("deadlineIsError", "true");
				return ERROR;
			}
			//////////////////////////////////////////
			else{  //获取所有合法参数
	
				request.setAttribute("deadlinIsError","false");
		HttpServletRequest request1=ServletActionContext.getRequest();
		Enumeration paraNames= request1.getParameterNames();
		ArrayList paraNameList = new ArrayList(); 
		while (paraNames.hasMoreElements()) 
		{ 
		paraNameList.add(paraNames.nextElement()); 
		} //把用户参数都加到paraNameList里面
		for (int i = 0; i < paraNameList.size(); i++) 
		{ 
		String httpParamName = (String) paraNameList.get(i); 
		String httpParamValue = request.getParameter(httpParamName); 
		}//获取用户输入的参数
		Transaction tx=session.beginTransaction();
		Voteinfo vote=new Voteinfo();
		vote.setVoteTitle(vote_title);
		vote.setVoteDescription(vote_des);
		vote.setVoteDeadline(vote_deadline);
		vote.setUserinfo(user);
		Date date=new Date();
		DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		 
		Calendar ca= Calendar.getInstance();
		String curdate = dt.format(ca.getTime());
		vote.setVoteCreateTime(curdate);
		session.save(vote);
		tx.commit();
		session.close();//以上完成对Voteinfo表的操作
		//下面完成对Voteselectinfo表的操作
		for (int i = 0; i < paraNameList.size(); i++)
		{
			String httpParamName1 = (String) paraNameList.get(i); 
			String httpParamValue1 = request.getParameter(httpParamName1); 
			if((!httpParamValue1.equals(vote_deadline))&&(!httpParamValue1.equals(vote_title))&&(!httpParamValue1.equals(vote_des)))
			{
				session=sessionFactory.openSession();
				tx=session.beginTransaction();
				Voteselectinfo select=new Voteselectinfo();
				select.setVoteinfo(vote);
				select.setSelectDes(httpParamValue1);
				session.save(select);
				tx.commit();
				session.close();
			}
		}
		return SUCCESS;
			}
		}
	}

}
