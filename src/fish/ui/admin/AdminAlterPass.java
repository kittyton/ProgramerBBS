package fish.ui.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Userinfo;
import bean.MD5;

import com.opensymphony.xwork2.ActionSupport;

public class AdminAlterPass extends ActionSupport implements ServletRequestAware {
	private String newPass ;
	private String renewPass ;
	private HttpServletRequest request ;
	
	public void validate() {
		if(newPass == null || newPass =="")
			this.addActionError("请输入密码") ;
		else if(!newPass.equals(renewPass))
				this.addActionError("两次输入密码不一致") ;
	}
	
	void alterPass() {
		Session se = HibernateSessionFactory.getSession() ;
		MD5 md5 = new MD5();  
		String currName = (String) request.getSession().getAttribute("username");
		Criteria crit = se.createCriteria(Userinfo.class);
    	crit.add(Restrictions.eq("userName", currName));
    	Userinfo currAdmin = (Userinfo) crit.list().iterator().next() ;
    	
    	Transaction trans = se.beginTransaction() ;
    	currAdmin.setPassword(md5.getMD5ofStr(newPass)) ;
    	se.update(currAdmin) ;
    	trans.commit() ;
    	se.close() ;
	}
	
	public String execute() throws Exception{
		alterPass();
		request.setAttribute("alterSuc", "") ;
		this.addActionMessage("修改成功") ;
		return SUCCESS;
	}
	
	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getRenewPass() {
		return renewPass;
	}

	public void setRenewPass(String renewPass) {
		this.renewPass = renewPass;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req ;
	}

}
