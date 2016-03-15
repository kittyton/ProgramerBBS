package fish.ui.admin;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.Childmoduleinfo;
import bbs.database.hibernate.HibernateSessionFactory;

import com.opensymphony.xwork2.ActionSupport;

public class AlterChildModuleAction extends ActionSupport implements ServletRequestAware {
	private int childId ;
	private String childName ;
	private Set<Childmoduleinfo> childModule_list ;
	private HttpServletRequest request ;
	
	public void validate() {
		if(childName == null || childName.isEmpty())
		{
			setData() ;
			this.addActionError("请输入子模块名称") ;
		}
		
		if(!check())
		{
			setData() ;
			this.addActionError("此子模块名称已经存在") ;
		}
	}
	
	private boolean check(){
		Session se = HibernateSessionFactory.getSession();
		Criteria crit = se.createCriteria(Childmoduleinfo.class);
    	crit.add(Restrictions.eq("childModuleName", childName));
    	
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
	
	private void alter() {
		Session se = HibernateSessionFactory.getSession() ;
		Childmoduleinfo curr = (Childmoduleinfo) se.load(Childmoduleinfo.class, childId) ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		curr.setChildModuleName(childName) ;
		se.update(curr) ;
		tran.commit() ;
		se.close() ;
	}
	
	private void setData() {
		Session se = HibernateSessionFactory.getSession() ;
		Childmoduleinfo curr = (Childmoduleinfo) se.load(Childmoduleinfo.class, childId) ;
		childModule_list = curr.getModuleinfo().getChildmoduleinfos() ;
		request.setAttribute("childModule_list",childModule_list) ;
	}
	
	public String execute() throws Exception{
		alter() ;
		setData() ;
		this.addActionMessage("修改成功") ;
		return SUCCESS ;
	}

	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	@Override
	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

}
