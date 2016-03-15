package fish.session.admin;

import bbs.database.hibernate.Moduleinfo;
import bbs.database.hibernate.Userinfo;

public class AdminInfo {
	private Userinfo currAdmin ;
	private Moduleinfo currModule ;
	public Userinfo getCurrAdmin() {
		return currAdmin;
	}
	public void setCurrAdmin(Userinfo currAdmin) {
		this.currAdmin = currAdmin;
	}
	public Moduleinfo getCurrModule() {
		return currModule;
	}
	public void setCurrModule(Moduleinfo currModule) {
		this.currModule = currModule;
	}
}
