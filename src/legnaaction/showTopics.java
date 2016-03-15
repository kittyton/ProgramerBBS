package legnaaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

 
import bbs.database.hibernate.Childmoduleinfo;
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Topicinfo;
import bbs.myother.MyList;

import com.opensymphony.xwork2.ActionSupport;

public class showTopics extends ActionSupport{
	private String id;
	private Set<Topicinfo> mytopics ;
	private String name;
	private Map<Topicinfo,MyList> mytopic;
	 
	public Map<Topicinfo, MyList> getMytopic() {
		return mytopic;
	}


	public void setMytopic(Map<Topicinfo, MyList> mytopic) {
		this.mytopic = mytopic;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public Set<Topicinfo> getMytopics() {
		return mytopics;
	}


	public void setMytopics(Set<Topicinfo> mytopics) {
		this.mytopics = mytopics;
	}

	Session searchsn = HibernateSessionFactory.getSession();
	Criteria cria = searchsn.createCriteria(Childmoduleinfo.class);
	//List<Topicinfo> tem = cria.list();
	
	
	@Override
	public String execute() throws Exception {
		List<Childmoduleinfo> tem1 = cria.list(); 
		mytopic = new HashMap<Topicinfo,MyList>();
		for(Childmoduleinfo t: tem1)
		{
			 MyList test = new MyList();
			if(id.equals(t.getChildModuleId().toString()))
			{
				
				 mytopics = t.getTopicinfos();
				for(Topicinfo tofo:mytopics )
				{
					String myname = tofo.getUserinfo().getUserName();
					 Integer replycs=tofo.getReplyinfos().size() ;
					 test.setKey(replycs);
					 test.setValue(myname);
					 mytopic.put(tofo, test);
					System.out.printf(tofo.getTopicContent()+"\n");	
				}
				return SUCCESS;
			}
		}		 
		HibernateSessionFactory.closeSession();
		return SUCCESS;
	}
	
	

}
