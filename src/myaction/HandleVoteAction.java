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
	private String[] selectItem;//��participatevote.jsp��õ��û�ѡ����Ϣ �ַ�������
	
	private String voteTitle; //����ͶƱ��ŵ�ͶƱ����
	private List<String> selectlist;//�����洢�û�ѡ����Ϣ
	private String currentusername;//��ǰ�û���
	private Integer voteID;        //��ǰ�������ͶƱid
	private Integer totalnum;      //��ǰ�������ͶƱ���Ѳ�������
	private String votetitle; //����ͶƱ��ŵ�ͶƱ����
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
		System.out.println("���뵽��HandleVoteAction����");
		System.out.println("selectItem="+selectItem);
		request=ServletActionContext.getRequest();
		Boolean Isnoselect=false;
		if(selectItem.length==0)
		{
				Isnoselect = true;
				request.setAttribute("Isnoselect", "true");
			    System.out.println("�Բ�����û��ѡ���κ�ѡ��");
				return LOGIN;
				}
			request.setAttribute("Isnoselect", "false");
			System.out.println(selectItem.length);
			selectlist=new ArrayList<String>();
			for(int i=0;i<selectItem.length;i++)
			{
				System.out.println("��"+i+"���������"+selectItem[i]);
			    selectlist.add(selectItem[i]);	
			}	
			System.out.println("selectlist�Ĵ�С��"+selectlist.size());
			for(int i=0;i<selectlist.size();i++)
			{
						System.out.println("selectlist�еĵ�"+i+"��ѡ����"+selectlist.get(i));
			}
			//�����ȡ��ǰ�û���Ϣ
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
			//�����ȡ��ǰ��ͶƱ��Ϣ
			session=sessionFactory.openSession();
			Voteinfo vote=new Voteinfo();
			vote=(Voteinfo) session.load(Voteinfo.class, voteID);
			votetitle=vote.getVoteTitle();
			vote_deadline=vote.getVoteDeadline();
			session.close();
			//�����жϵ�ǰ�����ͶƱ�Ƿ��ѽ�ֹ
			DateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");		 
			Calendar ca1= Calendar.getInstance();
			String curdate1 = dt1.format(ca1.getTime());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Date deadline=sdf.parse(vote_deadline);
			Date cur_date=sdf.parse(curdate1);
			Boolean Isdeadline=false;
			application2=ActionContext.getContext().getApplication();
			application2.put("Isdeadline", "false");
			if(deadline.before(cur_date))//���ͶƱ�ѵ���ֹ���ڣ��򷵻���ʾ������ͶƱ
			{
				Isdeadline=true;
				application2.put("Isdeadline", "true");
				return INPUT;
			}
			//���ͶƱ��δ��ֹ����������жϵ�ǰ�û��Ƿ��Ѳ���ͬһͶƱ�����ͶƱ
			session=sessionFactory.openSession();
			Criteria isvoted_cr=session.createCriteria(Singleresultinfo.class);
			isvoted_cr.add(Restrictions.and(Restrictions.eq("userinfo", user), Restrictions.eq("voteinfo", vote)));
		    List isvotedlist=isvoted_cr.list();
			session.close();
			if(!isvotedlist.isEmpty())//�Ѿ�Ͷ��Ʊ
			{
				
			      application=ActionContext.getContext().getApplication();
			    application.put("hasvoted", "true");
				return ERROR;
			}
			else{//�û���һ��Ϊ������ͶƱ
				application=ActionContext.getContext().getApplication();
			    application.put("hasvoted", "false");
			for(int i=0;i<selectlist.size();i++)
			{
			session=sessionFactory.openSession();
		    Transaction tx=session.beginTransaction();
		    Singleresultinfo singleresult=new Singleresultinfo();
		    singleresult.setUserinfo(user);//����ͶƱ����Ϣ
		    singleresult.setSelectDes(selectlist.get(i));//����ͶƱѡ����Ϣ
		    Date date=new Date();
			DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		 
			Calendar ca= Calendar.getInstance();
			String curdate = dt.format(ca.getTime());
			singleresult.setVoteTime(curdate);//����ͶƱʱ��
			singleresult.setVoteinfo(vote); //��������ͶƱ����
			session.save(singleresult);
			tx.commit();
			session.close();//������ɶ�Singleresultinfo��Ĳ������
			session=sessionFactory.openSession();
			Criteria total_cr=session.createCriteria(Totalresultinfo.class);
			total_cr.add(Restrictions.and(Restrictions.eq("voteinfo", vote), Restrictions.eq("totalSelectDes", selectlist.get(i))));
			List totallist=total_cr.list();//��ȷ��ͬһ�������ͶƱ��ͬһ��ѡ�����ܽ�������Ƿ��Ѿ�����
			session.close();
			if(totallist.size()==0)//���ͬһͶƱ�����ͬһѡ�û����ѡ����������в���һ��
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
			else{//���ͬһͶƱ�����ͬһѡ���Ѿ�����ѡ����ֱ�Ӹ�����Ŀ
				session=sessionFactory.openSession();
				tx=session.beginTransaction();
				Totalresultinfo totalresultinfo=(Totalresultinfo) totallist.get(0);
				totalnum=totalresultinfo.getTotalNum()+1;
				totalresultinfo.setTotalNum(totalnum);
				session.update(totalresultinfo);
				tx.commit();
				session.close();
			}//������ɶ�Totalresultinfo��Ĳ���
			}
			return SUCCESS;
			}//��һ��ͶƱ������
			
			
	}
}