package legnaaction;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate.criterion.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Topicinfo;

import com.opensymphony.xwork2.ActionSupport;

public class doSearch extends ActionSupport{
	private String search;
	private List<Topicinfo> topiclist ;
	private int size;

	 
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Topicinfo> getTopiclist() {
		return topiclist;
	}

	public void setTopiclist(List<Topicinfo> topiclist) {
		this.topiclist = topiclist;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	//Session session  = HibernateSessionFactory.getSession();
	//Criteria crit = session.createCriteria(Moduleinfo.class);
	Session searchsn = HibernateSessionFactory.getSession();
	Criteria cria = searchsn.createCriteria(Topicinfo.class);
	
	
	
	//searchca.add(Restriction.like("topicContent",search,MatchMode.ANYWHERE));
	//topiclist = cria.add(Restrictions.like("topicContent", search, MatchMode.ANYWHERE)).list();

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception { 
		
		topiclist = new ArrayList<Topicinfo>();
		//searchca.add(Restriction.like("topicContent",search,MatchMode.ANYWHERE));
		topiclist = (List<Topicinfo>)cria.add(Restrictions.like("topicTitle", search, MatchMode.ANYWHERE)).list();
		size=topiclist.size();
		// TODO Auto-generated method stub
		//return super.execute();
		System.out.printf(search);
		for(Topicinfo tofo:topiclist )
			System.out.printf(tofo.getTopicContent()+"\n");		
		return SUCCESS;
		
	}
	

}
