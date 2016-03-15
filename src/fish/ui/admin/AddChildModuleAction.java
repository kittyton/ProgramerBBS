package fish.ui.admin;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import bbs.database.hibernate.Childmoduleinfo;
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Moduleinfo;
import bbs.database.hibernate.Userinfo;

public class AddChildModuleAction extends ActionSupport implements ServletRequestAware {
	private Set<Childmoduleinfo> childModule_list ;
	private int fatherId ;
	private String currName ;
	private HttpServletRequest request ;
	private int id ;
	
	private void setData() {
		setId(fatherId) ;
		Session se = HibernateSessionFactory.getSession() ;
		Moduleinfo currfa = (Moduleinfo) se.load(Moduleinfo.class, fatherId) ;
		childModule_list = currfa.getChildmoduleinfos() ;
		Iterator<Childmoduleinfo> child_it = childModule_list.iterator() ;
		request.setAttribute("childModule_list",childModule_list) ;
	}

	private boolean check(){
		Session se = HibernateSessionFactory.getSession();
		Criteria crit = se.createCriteria(Childmoduleinfo.class);
    	crit.add(Restrictions.eq("childModuleName", currName));
    	
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
	
	private void add() {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		Moduleinfo fatherModule = (Moduleinfo) se.load(Moduleinfo.class, fatherId) ;
		Childmoduleinfo curr = new Childmoduleinfo() ;
		curr.setChildModuleName(currName) ;
		curr.setModuleinfo(fatherModule) ;
		se.save(curr) ;
		tran.commit() ;
		se.close() ;
	}

	public void validate() {
		if(currName == null || currName.isEmpty())
		{
			setData() ;
			this.addActionError("请输入子模块名称") ;
		}
		
		if(!check())
		{
			setData() ;
			this.addActionError("此子模块名称已存在") ;
		}
	}
	
	
	public String execute() throws Exception{
		setData() ;
		add() ;
		this.addActionMessage("添加成功") ;
		return SUCCESS ;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		request = req ;
	}

	public int getFatherId() {
		return fatherId;
	}

	public void setFatherId(int fatherId) {
		this.fatherId = fatherId;
	}

	public String getCurrName() {
		return currName;
	}

	public void setCurrName(String currName) {
		this.currName = currName;
	}
	
	
	public Set<Childmoduleinfo> getChildModule_list() {
		return childModule_list;
	}

	public void setChildModule_list(Set<Childmoduleinfo> childModule_list) {
		this.childModule_list = childModule_list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
