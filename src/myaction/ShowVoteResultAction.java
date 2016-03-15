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
	private String voteTitle;//���б�ŵ�ͶƱ����
    private Integer voteID;  //��ǰͶƱ��ID
    private String currentusername;//��ǰ�û���
    private List<ResultListInfo> resultlist;  //������ÿһ��ͶƱ��Ŀ��ͶƱ���
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
		vote=(Voteinfo) session.load(Voteinfo.class, voteID);//��ȡ��ǰ��ͶƱ��Ϣ
		vote_deadline=vote.getVoteDeadline();
		session.close();
		//�����ж��Ƿ��ѵ�ͶƱ�Ľ�ֹ����
		
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
		}//��ȡ��ǰ���û���Ϣ
		session.close();
		//�����ж��û��Ƿ��Ѿ�����ͶƱ
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
			List list=result_cr.list();//�õ�����ĳһͶƱ�����б�ѡ��ѡ�����ѡ����
			System.out.println("�õ��ĺͱ���ͶƱ�����йص�totalresultinfo���еı�����"+list.size()+"��");
			session.close();
			Totalresultinfo tolresinfo=new Totalresultinfo();
			
			for(int i=0;i<list.size();i++)//���ڿ�ʼ��ÿһ����в���
			{
				ResultListInfo resultlistinfo=new ResultListInfo();//һ��Ҫ��for����
				tolresinfo=(Totalresultinfo) list.get(i);
				resultlistinfo.setSelect_Des(tolresinfo.getTotalSelectDes());
				resultlistinfo.setTotal_num(tolresinfo.getTotalNum());
				resultlist.add(resultlistinfo);
			}
			return SUCCESS;
		}
		
	}

}
