package fish.ui.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.Childmoduleinfo;
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Moduleinfo;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.ws.api.server.Module;

public class AlterModuleAction extends ActionSupport implements ServletRequestAware {
	private String moduleName ;
	private String moduleDesc ;
	private int moduleId ;
	private HttpServletRequest request ;
	private int id ;
	private String currName ;
	private String currDesc ;

	private void alter() {
		Session se = HibernateSessionFactory.getSession() ;
		Moduleinfo curr = (Moduleinfo) se.load(Moduleinfo.class, moduleId) ;
		
		Transaction tran = se.beginTransaction();
		curr.setModuleName(moduleName) ;
		curr.setModuleDescription(moduleDesc) ;
		se.update(curr) ;
		tran.commit() ;
		se.close() ;
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
	
	private void setData() {
		Session se = HibernateSessionFactory.getSession() ;
		Moduleinfo curr = (Moduleinfo) se.load(Moduleinfo.class, moduleId) ;
		this.currName = curr.getModuleName() ;
		this.currDesc = curr.getModuleDescription() ;
		this.id = this.moduleId ;
		se.close() ;
	}
	
	public void validate() {
		if(moduleName == null || moduleName.isEmpty())
		{
			setData() ;
			this.addActionError("请输入模块名") ;
		}
		if(moduleDesc == null || moduleDesc.isEmpty())
		{
			setData() ;
			this.addActionError("请输入模块简介") ;
		}
		
		if(!check())
		{
			setData() ;
			this.addActionError("此模块名称已存在") ;
		}
	}
	
	public String execute() throws Exception{
		alter() ;
		this.addActionMessage("修改成功") ;
		request.setAttribute("alterSuc", "") ;
		return SUCCESS;
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
	
	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurrName() {
		return currName;
	}

	public void setCurrName(String currName) {
		this.currName = currName;
	}

	public String getCurrDesc() {
		return currDesc;
	}

	public void setCurrDesc(String currDesc) {
		this.currDesc = currDesc;
	}


	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req ;
	}
	
}
