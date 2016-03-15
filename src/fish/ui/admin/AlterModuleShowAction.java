package fish.ui.admin;

import org.hibernate.Session;

import bbs.database.hibernate.HibernateSessionFactory;
import bbs.database.hibernate.Moduleinfo;

import com.opensymphony.xwork2.ActionSupport;

public class AlterModuleShowAction extends ActionSupport {
	private int id ;
	private String currName ;
	private String currDesc ;

	public void getData() {
		Session se = HibernateSessionFactory.getSession() ;
		Moduleinfo currModule = (Moduleinfo) se.load(Moduleinfo.class, id) ;
		setCurrName(currModule.getModuleName()) ;
		setCurrDesc(currModule.getModuleDescription()) ;
		se.close() ;
	}
	
	public String execute() throws Exception{
		getData() ;
		return SUCCESS;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurrName() {
		return currName;
	}

	public void setCurrName(String currName) {
		this.currName = currName;
	}

	public String getCurrDesc() {
		return currDesc;
	}

	public void setCurrDesc(String currDesc) {
		this.currDesc = currDesc;
	}
}
