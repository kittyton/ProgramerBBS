package fish.ui.admin;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bbs.database.hibernate.Childmoduleinfo;
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Replyinfo;
import bbs.database.hibernate.Topicinfo;

import com.opensymphony.xwork2.ActionSupport;

public class RemoveChildModuleAction extends ActionSupport implements ServletRequestAware {
	private int childId ;
	private String childName ;
	private HttpServletRequest request ;

	private boolean remove() throws Exception {
		Session se = HibernateSessionFactory.getSession() ;
		Childmoduleinfo currChild = (Childmoduleinfo)se.load(Childmoduleinfo.class, childId) ;
	
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;

		Iterator<Topicinfo> topic_it = currChild.getTopicinfos().iterator() ;
		while(topic_it.hasNext())
		{
			Topicinfo currTop = topic_it.next() ;
			System.out.print(currTop.getTopicContent()) ;
			Iterator<Replyinfo> reply_it = currTop.getReplyinfos().iterator() ;
			while(reply_it.hasNext())
			{
				Replyinfo currRe = reply_it.next() ;
				se.delete(currRe) ;
			}
			
			se.delete(currTop) ;
		}
		
		se.delete(currChild) ;
		
		tran.commit() ;
		se.close() ;
		return true ;
	}
	
	public String execute() throws Exception{
		if(remove())
		{
			request.setAttribute("removeSuc", "É¾³ý³É¹¦") ;
			return SUCCESS;
		}
		return INPUT ;
	}
	
	public int getChildId() {
		return childId;
	}

	public void setChildId(int childId) {
		this.childId = childId;
	}
	
	

	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req ;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
}
