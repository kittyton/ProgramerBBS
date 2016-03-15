package fish.ui.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Moduleinfo;
import bbs.database.hibernate.Userinfo;
import bean.MD5;

import com.opensymphony.xwork2.ActionSupport;

import fish.session.admin.Admin;
import fish.session.admin.AdminInfo;

public class AddAdminAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request ;
	private String adminName ;
	private String password ;
	private String repassword ;
	private int module ;
	private List<AdminInfo> admininfo_list ;
	private List<Moduleinfo> module_list ;
	
	private int count() {
		int count_admin = 0 ;
		Session se = HibernateSessionFactory.getSession();		
		Moduleinfo currModule = (Moduleinfo)se.load(Moduleinfo.class, module) ;
		if(currModule.getUserinfoByModuleAdmin1() == null)
			count_admin = 0 ;
		else if(currModule.getUserinfoByModuleAdmin2() == null)
			count_admin = 1 ;
		else if(currModule.getUserinfoByModuleAdmin3() == null)
			count_admin = 2 ;
		else
			count_admin = 3 ;

		return count_admin ;
	}
	
	private boolean checkUser() {
		Session se = HibernateSessionFactory.getSession();
		
		Criteria crit = se.createCriteria(Userinfo.class);
    	crit.add(Restrictions.eq("userName", adminName));
    	
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
	
	private void addAdmin() {
		MD5 md5 = new MD5();  
		Session se = HibernateSessionFactory.getSession();		
		Userinfo newUser = new Userinfo() ;
		newUser.setUserName(adminName) ;
		newUser.setPassword(md5.getMD5ofStr(password)) ;
		newUser.setUserType(2) ;
		
		Transaction tran = se.beginTransaction();
		se.save(newUser);
		
		int count = count() ;
		Moduleinfo currModule = (Moduleinfo)se.load(Moduleinfo.class, module) ;
		if(count == 0)
			currModule.setUserinfoByModuleAdmin1(newUser) ;
		else if(count == 1)
			currModule.setUserinfoByModuleAdmin2(newUser) ;
		else 
			currModule.setUserinfoByModuleAdmin3(newUser) ;
		se.update(currModule) ;
		tran.commit() ;
		
		
		HibernateSessionFactory.closeSession();
	}
	
	private void setData() {
		Admin curr = new Admin() ;
		curr.getData() ;
		this.admininfo_list = curr.getAdmininfo_list() ;
		this.module_list = curr.getModule_list() ;
		request.setAttribute("dataList", admininfo_list) ;
		request.setAttribute("optionList", module_list) ;
	}
	
	public void validate() {
		setData() ;
		if(password != null && repassword != null){
			if(!repassword.equals(password))
				this.addActionError("两次输入密码不一致");
		}
		
		if(!checkUser())
			this.addActionError("此用户名已存在");
		
		if(count() == 3)
			this.addActionError("此模块管理员数量已满");
	}
	
	public String execute() throws Exception{
		addAdmin();
		setData() ;
		request.setAttribute("addSuc", "添加管理员成功") ;
		return SUCCESS;
	}
	
	public String getAdminName() {
		return adminName;
	}


	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRepassword() {
		return repassword;
	}


	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}


	public int getModule() {
		return module;
	}


	public void setModule(int module) {
		this.module = module;
	}


	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req ;
		
	}

}
