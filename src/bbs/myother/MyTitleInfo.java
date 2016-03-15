package bbs.myother;

import java.util.Set;

import bbs.database.hibernate.Replyinfo;
import bbs.database.hibernate.Userinfo;

 

public class MyTitleInfo {
	 
	private  Replyinfo myryinfo;
	private Userinfo ryurinfo;
  
	public Replyinfo getMyryinfo() {
		return myryinfo;
	}
	public void setMyryinfo(Replyinfo myryinfo) {
		this.myryinfo = myryinfo;
	}
	public Userinfo getRyurinfo() {
		return ryurinfo;
	}
	public void setRyurinfo(Userinfo ryurinfo) {
		this.ryurinfo = ryurinfo;
	}
	 
	 

}
