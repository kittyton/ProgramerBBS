package legnaaction;
 

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bbs.database.hibernate.Childmoduleinfo;
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Moduleinfo;
import bbs.database.hibernate.Topicinfo;
import bbs.database.hibernate.Userinfo;
import bean.MD5;

import com.opensymphony.xwork2.ActionSupport;
 

public class GoHomeAction extends ActionSupport{
	private List<Moduleinfo> moduleinfo;
	private Set<Childmoduleinfo> submodule;
	private Map<Moduleinfo,Set> mymap;
	private Set<Topicinfo> topiclt;
	private Map<Childmoduleinfo,Set> module_topic;
	
	 
	public List<Moduleinfo> getModuleinfo() {
		return moduleinfo;
	}

	public void setModuleinfo(List<Moduleinfo> moduleinfo) {
		this.moduleinfo = moduleinfo;
	}

	public Set<Childmoduleinfo> getSubmodule() {
		return submodule;
	}

	public void setSubmodule(Set<Childmoduleinfo> submodule) {
		this.submodule = submodule;
	}

	public Map<Moduleinfo, Set> getMymap() {
		return mymap;
	}

	public void setMymap(Map<Moduleinfo, Set> mymap) {
		this.mymap = mymap;
	}
	private void addAdmin() {
		MD5 md5 = new MD5();  
		Session se = HibernateSessionFactory.getSession();		
		Userinfo newUser = new Userinfo() ;
		newUser.setUserName("admin") ;
		newUser.setPassword(md5.getMD5ofStr("admin")) ;
		newUser.setUserType(3) ;
		
		Transaction tran = se.beginTransaction();
		se.save(newUser);		
		tran.commit() ;		
		HibernateSessionFactory.closeSession();
	}
	@Override
	public String execute() throws Exception {
		//Userinfo 
		// TODO Auto-generated method stub
		Session session  = HibernateSessionFactory.getSession();
		Criteria crit = session.createCriteria(Moduleinfo.class);
		Criteria crit1 = session.createCriteria(Userinfo.class);
		if(crit1.list().isEmpty())
			addAdmin(); 
		moduleinfo = crit.list();
		mymap = new LinkedHashMap<Moduleinfo,Set>();
		submodule = new TreeSet<Childmoduleinfo>();
		module_topic = new LinkedHashMap<Childmoduleinfo,Set>();
		 
		for(Moduleinfo item:moduleinfo)
		{			
			//submodule = item.getChildmoduleinfos();
			submodule =item.getChildmoduleinfos();
			mymap.put(item, submodule);	
			 
			for(Childmoduleinfo subitem:submodule)
			{				 
				topiclt = subitem.getTopicinfos();
				module_topic.put(subitem, topiclt);
			}
			
		}
		HibernateSessionFactory.closeSession();
		return SUCCESS;
	}
	
	
	
	

}
