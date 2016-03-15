package fish.session.admin;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Moduleinfo;
import bbs.database.hibernate.Userinfo;

public class Admin extends ActionSupport implements ServletRequestAware { 
	private List<AdminInfo> admininfo_list ;
	private List<Moduleinfo> module_list ;
	private Userinfo currAdmin ;
	private Moduleinfo currModule ;
	private HttpServletRequest request ;

	public void getData() {
		Session se = HibernateSessionFactory.getSession() ;
		Criteria admin_cri = se.createCriteria(Userinfo.class) ;
		admin_cri.add(Restrictions.eq("userType", 2)) ;
		List<Userinfo> admin_list = admin_cri.list() ;
		Iterator<Userinfo> admin_it = admin_list.iterator() ;
		admininfo_list = new LinkedList<AdminInfo>() ;
		
		Criteria module_cri = se.createCriteria(Moduleinfo.class) ;
		module_list = module_cri.list() ;
		
		while(admin_it.hasNext())
		{
			AdminInfo currInfo = new AdminInfo() ;
			currAdmin = admin_it.next() ;
			if(!currAdmin.getModuleinfosForModuleAdmin1().isEmpty())
				currModule = (Moduleinfo) currAdmin.getModuleinfosForModuleAdmin1().iterator().next() ;			
			else if(!currAdmin.getModuleinfosForModuleAdmin2().isEmpty())
				currModule = (Moduleinfo) currAdmin.getModuleinfosForModuleAdmin2().iterator().next() ;			
			else if(!currAdmin.getModuleinfosForModuleAdmin3().isEmpty())
				currModule = (Moduleinfo) currAdmin.getModuleinfosForModuleAdmin3().iterator().next() ;
			
			currInfo.setCurrAdmin(currAdmin) ;
			currInfo.setCurrModule(currModule) ;
			admininfo_list.add(currInfo) ; 
		}
		se.close() ;
	}
	
	public String execute() throws Exception{
		getData();
		request.setAttribute("dataList", admininfo_list) ;
		request.setAttribute("optionList", module_list) ;
		return SUCCESS;
	}

	public List<AdminInfo> getAdmininfo_list() {
		return admininfo_list;
	}

	public void setAdmininfo_list(List<AdminInfo> admininfo_list) {
		this.admininfo_list = admininfo_list;
	}

	public List<Moduleinfo> getModule_list() {
		return module_list;
	}

	public void setModule_list(List<Moduleinfo> module_list) {
		this.module_list = module_list;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req ;
	}
	
}
