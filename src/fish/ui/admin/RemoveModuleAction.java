package fish.ui.admin;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.Childmoduleinfo;
import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Moduleinfo;
import bbs.database.hibernate.Replyinfo;
import bbs.database.hibernate.Topicinfo;
import bbs.database.hibernate.Userinfo;

import com.opensymphony.xwork2.ActionSupport;

public class RemoveModuleAction extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request ;
	private int moduleId ;
	
	private boolean remove() throws Exception {
		Session se = HibernateSessionFactory.getSession() ;
		Moduleinfo curr = (Moduleinfo) se.load(Moduleinfo.class, moduleId) ;
		Iterator<Childmoduleinfo> child_it = curr.getChildmoduleinfos().iterator() ;
		
		Transaction tran = se.beginTransaction() ;
		tran.begin() ;
		
		//删除相应子模块及子模块中帖子及跟帖
		while(child_it.hasNext())
		{
			Childmoduleinfo currChild = child_it.next() ;
			Iterator<Topicinfo> topic_it = currChild.getTopicinfos().iterator() ;
			while(topic_it.hasNext())
			{
				Topicinfo currTop = topic_it.next() ;
				Iterator<Replyinfo> reply_it = currTop.getReplyinfos().iterator() ;
				while(reply_it.hasNext())
				{
					Replyinfo currRe = reply_it.next() ;
					se.delete(currRe) ;
				}
				
				se.delete(currTop) ;
			}
			
			se.delete(currChild) ;
		}
		
		//删除相应管理员
		if(curr.getUserinfoByModuleAdmin1() != null)
		{
			Userinfo currAd = curr.getUserinfoByModuleAdmin1() ;
			se.delete(currAd) ;
		}
		if(curr.getUserinfoByModuleAdmin2() != null)
		{
			Userinfo currAd = curr.getUserinfoByModuleAdmin2() ;
			se.delete(currAd) ;
		}
		if(curr.getUserinfoByModuleAdmin3() != null)
		{
			Userinfo currAd = curr.getUserinfoByModuleAdmin3() ;
			se.delete(currAd) ;
		}
		
		se.delete(curr) ;
		tran.commit() ;
		se.close() ;
		return true ;
	}
	
	public String execute() throws Exception{
		if(remove())
		{
			request.setAttribute("removeSuc", "删除成功") ;
			return SUCCESS;
		}
		
		return INPUT ;
	}
	
	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest req) {
		this.request = req ;
	}
}
