package myaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import bbs.database.hibernate.Singleresultinfo;
import bbs.database.hibernate.Totalresultinfo;
import bbs.database.hibernate.Userinfo;
import bbs.database.hibernate.Voteinfo;
import bbs.database.hibernate.Voteselectinfo;

import bean.ResultListInfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HandleVoteAction extends ActionSupport{
	private String[] selectItem;//从participatevote.jsp获得的用户选项信息 字符数组型
	
	private String voteTitle; //含有投票编号的投票主题
	private List<String> selectlist;//用来存储用户选项信息
	private String currentusername;//当前用户名
	private Integer voteID;        //当前所参与的投票id
	private Integer totalnum;      //当前所参与的投票的已参与总数
	private String votetitle; //不含投票编号的投票标题
	private List<ResultListInfo> resultlist;
	private Map application;
	private String vote_deadline;
	private Map application2;
	private  HttpServletRequest request;
	public String[] getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(String[] selectItem) {
		this.selectItem = selectItem;
	}

	public String getCurrentusername() {
		return currentusername;
	}

	public void setCurrentusername(String currentusername) {
		this.currentusername = currentusername;
	}

	public String getVoteTitle() {
		return voteTitle;
	}

	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}

	public Integer getVoteID() {
		return voteID;
	}

	public void setVoteID(Integer voteID) {
		this.voteID = voteID;
	}

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}
    
	public String getVotetitle() {
		return votetitle;
	}

	public void setVotetitle(String votetitle) {
		this.votetitle = votetitle;
	}

	public List<ResultListInfo> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<ResultListInfo> resultlist) {
		this.resultlist = resultlist;
	}

	public String getVote_deadline() {
		return vote_deadline;
	}

	public void setVote_deadline(String vote_deadline) {
		this.vote_deadline = vote_deadline;
	}

	public Map getApplication() {
		return application;
	}

	public void setApplication(Map application) {
		this.application = application;
	}

	public Map getApplication2() {
		return application2;
	}

	public void setApplication2(Map application2) {
		this.application2 = application2;
	}

	public String execute() throws Exception{
		System.out.println("进入到了HandleVoteAction里面");
		System.out.println("selectItem="+selectItem);
		request=ServletActionContext.getRequest();
		Boolean Isnoselect=false;
		if(selectItem.length==0)
		{
				Isnoselect = true;
				request.setAttribute("Isnoselect", "true");
			    System.out.println("对不起，您没有选择任何选项");
				return LOGIN;
				}
			request.setAttribute("Isnoselect", "false");
			System.out.println(selectItem.length);
			selectlist=new ArrayList<String>();
			for(int i=0;i<selectItem.length;i++)
			{
				System.out.println("第"+i+"项的内容是"+selectItem[i]);
			    selectlist.add(selectItem[i]);	
			}	
			System.out.println("selectlist的大小是"+selectlist.size());
			for(int i=0;i<selectlist.size();i++)
			{
						System.out.println("selectlist中的第"+i+"个选项是"+selectlist.get(i));
			}
			//下面获取当前用户信息
			HttpServletRequest user_request=ServletActionContext.getRequest();
			currentusername=(String) user_request.getSession().getAttribute("username");
			Userinfo user=new Userinfo();
			Configuration config=new Configuration().configure();
			SessionFactory sessionFactory=config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Criteria cr=session.createCriteria(Userinfo.class);
			cr.add(Restrictions.eq("userName", currentusername));
			List userlist=cr.list();
			Iterator it=userlist.iterator();
			while(it.hasNext())
			{
				user=(Userinfo) it.next();
			}
			session.close();
			//**************************************************************//
			//下面获取当前的投票信息
			session=sessionFactory.openSession();
			Voteinfo vote=new Voteinfo();
			vote=(Voteinfo) session.load(Voteinfo.class, voteID);
			votetitle=vote.getVoteTitle();
			vote_deadline=vote.getVoteDeadline();
			session.close();
			//下面判断当前主题的投票是否已截止
			DateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");		 
			Calendar ca1= Calendar.getInstance();
			String curdate1 = dt1.format(ca1.getTime());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date deadline=sdf.parse(vote_deadline);
			Date cur_date=sdf.parse(curdate1);
			Boolean Isdeadline=false;
			application2=ActionContext.getContext().getApplication();
			application2.put("Isdeadline", "false");
			if(deadline.before(cur_date))//如果投票已到截止日期，则返回提示不能在投票
			{
				Isdeadline=true;
				application2.put("Isdeadline", "true");
				return INPUT;
			}
			//如果投票尚未截止则下面接着判断当前用户是否已参与同一投票主题的投票
			session=sessionFactory.openSession();
			Criteria isvoted_cr=session.createCriteria(Singleresultinfo.class);
			isvoted_cr.add(Restrictions.and(Restrictions.eq("userinfo", user), Restrictions.eq("voteinfo", vote)));
		    List isvotedlist=isvoted_cr.list();
			session.close();
			if(!isvotedlist.isEmpty())//已经投过票
			{
				
			      application=ActionContext.getContext().getApplication();
			    application.put("hasvoted", "true");
				return ERROR;
			}
			else{//用户第一次为该主题投票
				application=ActionContext.getContext().getApplication();
			    application.put("hasvoted", "false");
			for(int i=0;i<selectlist.size();i++)
			{
			session=sessionFactory.openSession();
		    Transaction tx=session.beginTransaction();
		    Singleresultinfo singleresult=new Singleresultinfo();
		    singleresult.setUserinfo(user);//设置投票人信息
		    singleresult.setSelectDes(selectlist.get(i));//设置投票选项信息
		    Date date=new Date();
			DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		 
			Calendar ca= Calendar.getInstance();
			String curdate = dt.format(ca.getTime());
			singleresult.setVoteTime(curdate);//设置投票时间
			singleresult.setVoteinfo(vote); //设置所属投票主题
			session.save(singleresult);
			tx.commit();
			session.close();//以上完成对Singleresultinfo表的操作完成
			session=sessionFactory.openSession();
			Criteria total_cr=session.createCriteria(Totalresultinfo.class);
			total_cr.add(Restrictions.and(Restrictions.eq("voteinfo", vote), Restrictions.eq("totalSelectDes", selectlist.get(i))));
			List totallist=total_cr.list();//先确定同一个主题的投票的同一个选项在总结果表中是否已经出现
			session.close();
			if(totallist.size()==0)//如果同一投票主题的同一选项还没有人选过，则向表中插入一列
			{
				session=sessionFactory.openSession();
				tx=session.beginTransaction();
				Totalresultinfo totalresultinfo=new Totalresultinfo();
				totalnum=1;
				totalresultinfo.setTotalNum(totalnum);
				totalresultinfo.setTotalSelectDes(selectlist.get(i));
				totalresultinfo.setVoteinfo(vote);
				session.save(totalresultinfo);
				tx.commit();
				session.close();
			}
			else{//如果同一投票主题的同一选项已经有人选过则直接更新数目
				session=sessionFactory.openSession();
				tx=session.beginTransaction();
				Totalresultinfo totalresultinfo=(Totalresultinfo) totallist.get(0);
				totalnum=totalresultinfo.getTotalNum()+1;
				totalresultinfo.setTotalNum(totalnum);
				session.update(totalresultinfo);
				tx.commit();
				session.close();
			}//至此完成对Totalresultinfo表的操作
			}
			return SUCCESS;
			}//第一次投票处理完
			
			
	}
}