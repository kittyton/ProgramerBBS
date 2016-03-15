package fish.ui.admin;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Replyinfo;
import bbs.database.hibernate.Topicinfo;

import com.opensymphony.xwork2.ActionSupport;

public class RmvTopicAction extends ActionSupport {
	private String topicId ;
	
	private void rmv() {
		Session se = HibernateSessionFactory.getSession() ;
		Transaction trans = se.beginTransaction() ;
		trans.begin() ;
		Topicinfo currTop = (Topicinfo) se.load(Topicinfo.class, Integer.parseInt(topicId)) ;
		Iterator<Replyinfo> reply_it = currTop.getReplyinfos().iterator() ;
		Replyinfo currRep = new Replyinfo() ;
		while(reply_it.hasNext())
		{
			currRep = reply_it.next() ;
			se.delete(currRep) ;
		}
		
		se.delete(currTop) ;
		trans.commit() ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		rmv() ;
		return SUCCESS;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	
}
