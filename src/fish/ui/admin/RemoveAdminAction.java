package fish.ui.admin;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import fish.session.admin.Admin;
import fish.session.admin.AdminInfo;

import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Moduleinfo;
import bbs.database.hibernate.Userinfo;

public class RemoveAdminAction extends ActionSupport implements ServletRequestAware {
	private int adminId ;
	private HttpServletRequest request ;
	private List<AdminInfo> admininfo_list ;
	private List<Moduleinfo> module_list ;
	
	void remove() {
		Session se = HibernateSessionFactory.getSession() ;
		Userinfo currUser = (Userinfo)se.load(Userinfo.class, adminId) ;
		Moduleinfo currModule = new Moduleinfo() ;
		
		if(!currUser.getModuleinfosForModuleAdmin1().isEmpty())
		{
			currModule = (Moduleinfo) currUser.getModuleinfosForModuleAdmin1().iterator().next() ;
			currModule.setUserinfoByModuleAdmin1(null) ;
		}
		else if(!currUser.getModuleinfosForModuleAdmin2().isEmpty())
		{
			currModule = (Moduleinfo) currUser.getModuleinfosForModuleAdmin2().iterator().next() ;			
			currModule.setUserinfoByModuleAdmin2(null) ;
		}
		else if(!currUser.getModuleinfosForModuleAdmin3().isEmpty())
		{
			currModule = (Moduleinfo) currUser.getModuleinfosForModuleAdmin3().iterator().next() ;
			currModule.setUserinfoByModuleAdmin3(null) ;
		}
		
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		se.update(currModule) ;
		se.delete(currUser) ;
		tran.commit() ;
		HibernateSessionFactory.closeSession() ;
	}
	
	private void setData() {
		Admin curr = new Admin() ;
		curr.getData() ;
		this.admininfo_list = curr.getAdmininfo_list() ;
		this.module_list = curr.getModule_list() ;
		request.setAttribute("dataList", admininfo_list) ;
		request.setAttribute("optionList", module_list) ;
	}
	
	public String execute() throws Exception {
		remove() ;
		setData() ;
		request.setAttribute("rmvSuc", "É¾³ý³É¹¦") ;
		return SUCCESS ;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req ;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
}
