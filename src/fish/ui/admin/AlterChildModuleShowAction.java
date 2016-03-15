package fish.ui.admin;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;

import bbs.database.hibernate.Childmoduleinfo;
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Moduleinfo;

import com.opensymphony.xwork2.ActionSupport;

public class AlterChildModuleShowAction extends ActionSupport implements ServletRequestAware {
	private int id ;
	private String fatherName ;
	private Moduleinfo currModule ;
	private Set<Childmoduleinfo> childModule_list ;
	private Iterator<Childmoduleinfo> child_it ;
	private HttpServletRequest request ; 
	
	public void getData() {
		Session se = HibernateSessionFactory.getSession() ;
		currModule = (Moduleinfo) se.load(Moduleinfo.class, id) ;
		childModule_list = currModule.getChildmoduleinfos() ;
		child_it = childModule_list.iterator() ; //奇葩。。没这句不行。。
		fatherName = currModule.getModuleName() ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		getData() ;
		request.setAttribute("childModule_list",childModule_list) ;
		return SUCCESS;
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

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req ; 
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
}
