package fish.ui.admin;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Userinfo;

import com.opensymphony.xwork2.ActionSupport;

public class UserSilencedAction extends ActionSupport implements ServletRequestAware {
	private String userName ;
	private String type ;
	private HttpServletRequest request ;
	private List<Userinfo> silence_list ;
	
	private void userSilence() {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction trans = se.beginTransaction() ;
		Criteria crit = se.createCriteria(Userinfo.class);
    	crit.add(Restrictions.eq("userName", userName));
    	Userinfo currUser = (Userinfo) crit.list().iterator().next() ;
    	trans.begin() ;
    	currUser.setUserSilenced(1) ;
    	se.update(currUser) ;
    	trans.commit() ;
    	se.close() ;
	}
	
	private void userOpen() {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction trans = se.beginTransaction() ;
		Criteria crit = se.createCriteria(Userinfo.class);
    	crit.add(Restrictions.eq("userName", userName));
    	Userinfo currUser = (Userinfo) crit.list().iterator().next() ;
    	trans.begin() ;
    	currUser.setUserSilenced(0) ;
    	se.update(currUser) ;
    	trans.commit() ;
    	se.close() ;
	}
	
	private void getSilencedUser() {
		Session se = HibernateSessionFactory.getSession() ;
		Criteria silence_cri = se.createCriteria(Userinfo.class) ;
		silence_cri.add(Restrictions.eq("userSilenced", 1)) ;
		silence_list = silence_cri.list() ;
		Iterator<Userinfo> silence_it = silence_list.iterator() ;
	}
	
	public String execute() throws Exception{
		if(type != null && type.equals("silence")) //若禁言了某用户
		{
			userSilence() ;
			request.setAttribute("alterSuc", "禁言成功") ;
		}
		
		if(type != null && type.equals("open")) //若解除禁言状态
			userOpen() ;
		
		//显示被禁言的用户
		getSilencedUser() ;	
		request.setAttribute("silenceList", silence_list) ;
		return SUCCESS;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Userinfo> getSilence_list() {
		return silence_list;
	}

	public void setSilence_list(List<Userinfo> silence_list) {
		this.silence_list = silence_list;
	}
	
	
}
