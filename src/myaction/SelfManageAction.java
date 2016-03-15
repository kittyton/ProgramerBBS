package myaction;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.Userinfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SelfManageAction extends ActionSupport{
	
	private String name;
	private String pass;
	private String ho;
	private String intro;
	private String lev;
    private Integer pid;
    private String pic;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getLev() {
		return lev;
	}

	public void setLev(String lev) {
		this.lev = lev;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String execute() throws Exception{
		System.out.println("进入了Selfmanage action 里面");
	Userinfo user=new Userinfo();
	Configuration config=new Configuration().configure();
	SessionFactory sessionFactory=config.buildSessionFactory();
	Session session=sessionFactory.openSession();
	HttpServletRequest request=ServletActionContext.getRequest();
	name=(String) request.getSession().getAttribute("username");
	if(name==null)
	{
		addFieldError("username","对不起，你还没有登录，请先登录");
		return INPUT;
	}
	else{
		Criteria cr=session.createCriteria(Userinfo.class);
		cr.add(Restrictions.eq("userName", name));
		List user1=cr.list();
		Iterator it=user1.iterator();
		while(it.hasNext())
		{
			
			user=(Userinfo) it.next();
		}
		pass=user.getPassword();
		ho=user.getUserHobby();
		intro=user.getUserIntroduction();
		lev=user.getUserLevel();
		pic=user.getUserPicture();
		session.close();
	    return SUCCESS;    
	}
	}


}
