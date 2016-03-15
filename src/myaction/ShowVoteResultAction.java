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
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.Singleresultinfo;
import bbs.database.hibernate.Totalresultinfo;
import bbs.database.hibernate.Userinfo;
import bbs.database.hibernate.Voteinfo;

import bean.ResultListInfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShowVoteResultAction extends ActionSupport{
	private String voteTitle;//含有标号的投票主题
    private Integer voteID;  //当前投票的ID
    private String currentusername;//当前用户名
    private List<ResultListInfo> resultlist;  //包含有每一项投票数目的投票结果
    private Map application;
    private String vote_deadline;
	public String getVoteTitle() {
		return voteTitle;
	}

	public void setVoteTitle(String voteTitle) {
		this.voteTitle = voteTitle;
	}
	
	public List<ResultListInfo> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<ResultListInfo> resultlist) {
		this.resultlist = resultlist;
	}

	public Integer getVoteID() {
		return voteID;
	}

	public void setVoteID(Integer voteID) {
		this.voteID = voteID;
	}
	public Map getApplication() {
		return application;
	}

	public void setApplication(Map application) {
		this.application = application;
	}

	public String execute() throws Exception{
		Configuration config=new Configuration().configure();
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Voteinfo vote=new Voteinfo();
		vote=(Voteinfo) session.load(Voteinfo.class, voteID);//获取当前的投票信息
		vote_deadline=vote.getVoteDeadline();
		session.close();
		//下面判断是否已到投票的截止日期
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date deadline=sdf.parse(vote_deadline);
		DateFormat dt=new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca=Calendar.getInstance();
		String curdate=dt.format(ca.getTime());	
		Date dd=sdf.parse(curdate);
		Boolean Afterdeadline=false;
		if(dd.after(deadline))
		{
			Afterdeadline=true;
		}
		/////////////////////////
		HttpServletRequest request=ServletActionContext.getRequest();
		currentusername=(String) request.getSession().getAttribute("username");
		session=sessionFactory.openSession();
		Userinfo user=new Userinfo();
		Criteria cr=session.createCriteria(Userinfo.class);
		cr.add(Restrictions.eq("userName", currentusername));
		List userlist=cr.list();
		Iterator it=userlist.iterator();
		while(it.hasNext())
		{
			
			user=(Userinfo) it.next();
		}//获取当前的用户信息
		session.close();
		//下面判断用户是否已经参与投票
		session=sessionFactory.openSession();
		Criteria isvoted_cr=session.createCriteria(Singleresultinfo.class);
		isvoted_cr.add(Restrictions.and(Restrictions.eq("userinfo", user), Restrictions.eq("voteinfo", vote)));
		List isvotedlist=isvoted_cr.list();
		session.close();
		if(isvotedlist.isEmpty()&&(!Afterdeadline))
		{
			application=ActionContext.getContext().getApplication();
		    application.put("isvoted", "false");
			return ERROR;
		}
		else{
			application=ActionContext.getContext().getApplication();
		    application.put("isvoted", "true");
			resultlist=new ArrayList<ResultListInfo>();
			session=sessionFactory.openSession();
			Criteria result_cr=session.createCriteria(Totalresultinfo.class);
			result_cr.add(Restrictions.eq("voteinfo", vote));
			List list=result_cr.list();//得到关于某一投票的所有被选的选项和所选人数
			System.out.println("得到的和本次投票主题有关的totalresultinfo表中的表项有"+list.size()+"项");
			session.close();
			Totalresultinfo tolresinfo=new Totalresultinfo();
			
			for(int i=0;i<list.size();i++)//现在开始对每一项进行操作
			{
				ResultListInfo resultlistinfo=new ResultListInfo();//一定要在for里面
				tolresinfo=(Totalresultinfo) list.get(i);
				resultlistinfo.setSelect_Des(tolresinfo.getTotalSelectDes());
				resultlistinfo.setTotal_num(tolresinfo.getTotalNum());
				resultlist.add(resultlistinfo);
			}
			return SUCCESS;
		}
		
	}

}
