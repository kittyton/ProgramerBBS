package fish.ui.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Moduleinfo;
import fish.session.admin.Admin;
import fish.session.admin.AdminInfo;

public class AddModuleAction extends ActionSupport implements ServletRequestAware {
	private List<AdminInfo> admininfo_list ;
	private List<Moduleinfo> module_list ;
	private HttpServletRequest request ;
	private String moduleName ;
	private String moduleDesc ;
	
	
	private void setData() {
		Admin curr = new Admin() ;
		curr.getData() ;
		this.admininfo_list = curr.getAdmininfo_list() ;
		this.module_list = curr.getModule_list() ;
		request.setAttribute("dataList", admininfo_list) ;
		request.setAttribute("optionList", module_list) ;
	}
	
	private boolean check(){
		Session se = HibernateSessionFactory.getSession();
		Criteria crit = se.createCriteria(Moduleinfo.class);
    	crit.add(Restrictions.eq("moduleName", moduleName));
    	
    	if(crit.list().size() != 0)
    	{
    		HibernateSessionFactory.closeSession() ;
    		return false ;
    	}
    	
    	else 
    	{
    		HibernateSessionFactory.closeSession() ;
    		return true ;
    	}
	}
	
	
	public void validate() {
		setData() ;
		if(moduleName == null || moduleName.isEmpty())
		{
			setData() ;
			this.addActionError("������ģ����") ;
		}
		if(moduleDesc == null || moduleDesc.isEmpty())
		{
			setData() ;
			this.addActionError("������ģ����") ;
		}
		
		if(!check())
		{
			setData() ;
			this.addActionError("��ģ�������Ѵ���") ;
		}
	}
	
	private void addModule() {
		Moduleinfo newModule = new Moduleinfo() ;
		newModule.setModuleName(moduleName) ;
		newModule.setModuleDescription(moduleDesc) ;
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		se.save(newModule) ;
		tran.commit() ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		setData() ;
		addModule() ;
		request.setAttribute("addSuc", "���ģ��ɹ�") ;
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
	
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}


	@Override
	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

}
