package myaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import bbs.database.hibernate.Userinfo;

import bean.MD5;

import com.opensymphony.xwork2.ActionSupport;

public class ModifySelfAction extends ActionSupport {
	
	private String currentname;
	private String rpass;  //从表单中接收的
	private String rrpass;//从表单中接收的
	private String rhobby;
	private String rintroduction;
	private String MD5rpass;
	private String rtemp;
    private File rimage;
    private String rimageContentType;
    private String rimageFileName;
    private String savePath;
	public String getRpass() {
		return rpass;
	}
	public void setRpass(String rpass) {
		this.rpass = rpass;
	}
	public String getRrpass() {
		return rrpass;
	}
	public void setRrpass(String rrpass) {
		this.rrpass = rrpass;
	}
	public String getRhobby() {
		return rhobby;
	}
	public void setRhobby(String rhobby) {
		this.rhobby = rhobby;
	}
	public String getRintroduction() {
		return rintroduction;
	}
	public void setRintroduction(String rintroduction) {
		this.rintroduction = rintroduction;
	}
	
	public String getCurrentname() {
		return currentname;
	}
	public void setCurrentname(String currentname) {
		this.currentname = currentname;
	}
	
	public String getMD5rpass() {
		return MD5rpass;
	}
	public void setMD5rpass(String mD5rpass) {
		MD5rpass = mD5rpass;
	}
	 public String getSavePath() throws Exception{
	        return ServletActionContext.getServletContext().getRealPath(savePath); 
	    }

	    public void setSavePath(String savePath) {
	        this.savePath = savePath;
	    }

 
    public File getRimage() {
			return rimage;
		}
		public void setRimage(File rimage) {
			this.rimage = rimage;
		}
		public String getRimageContentType() {
			return rimageContentType;
		}
		public void setRimageContentType(String rimageContentType) {
			this.rimageContentType = rimageContentType;
		}
		public String getRimageFileName() {
			return rimageFileName;
		}
		public void setRimageFileName(String rimageFileName) {
			this.rimageFileName = rimageFileName;
		}
public String getRtemp() {
	return rtemp;
}
public void setRtemp(String rtemp) {
	this.rtemp = rtemp;
}
private void close(FileOutputStream fos, FileInputStream fis) {
     if (fis != null) {
         try {
             fis.close();
         } catch (IOException e) {
             System.out.println("FileInputStream关闭失败");
             e.printStackTrace();
         }
     }
     if (fos != null) {
         try {
             fos.close();
         } catch (IOException e) {
             System.out.println("FileOutputStream关闭失败");
             e.printStackTrace();
         }
     }
 }
	public String execute() throws Exception{
		
		if(!rrpass.equals(rpass)){
			addFieldError("rpass","两次密码输入不一致，请重新输入");
			return INPUT;
		}
		if(rhobby.length()>4)
		{
			addFieldError("rhobby","用户爱好不可多余10个字");
			return INPUT;
		}
		if(rintroduction.length()>4)
		{
			addFieldError("rintroduction","自我介绍在50字之内");
			return INPUT;
		}
		else{
			Configuration config=new Configuration().configure();
			SessionFactory sessionFactory=config.buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx=null;
			Userinfo user=new Userinfo();
			HttpServletRequest request=ServletActionContext.getRequest();
			currentname=(String) request.getSession().getAttribute("username");
			Criteria cr=session.createCriteria(Userinfo.class);
			cr.add(Restrictions.eq("userName", currentname));
			List userlist=cr.list();
			Iterator it=userlist.iterator();
			while(it.hasNext())
			{
				
				user=(Userinfo) it.next();
			}
			MD5 md5=new MD5();
			
			//如果文件没有输入
			if(rimage==null)
			{
				
				rtemp=user.getUserPicture();
				System.out.println("头像图片仍为"+rtemp);
			}
			else{//头像图片改变
				
			FileOutputStream fos = null;
	        FileInputStream fis = null;
	        try {
	            // 建立文件输出流
	        	fos = new FileOutputStream(getSavePath() + "\\" + getRimageFileName());
	            rtemp="."+"\\"+"images"+"\\"+getRimageFileName();
	            // 建立文件上传流
	            fis = new FileInputStream(getRimage());
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            while ((len = fis.read(buffer)) > 0) {
	                fos.write(buffer, 0, len);
	            }
	        } catch (Exception e) {
	            System.out.println("文件上传失败");
	            e.printStackTrace();
	            addFieldError("image","文件上传失败");
	        } finally {
	            close(fos, fis);
	        }
			}
			//如果密码没有输入
			if(rpass.equals(""))
			{
				MD5rpass=user.getPassword();
			}
			else MD5rpass=md5.getMD5ofStr(rpass);
			//如果爱好没有输入
			if(rhobby.equals(""))
			{
				rhobby=user.getUserHobby();
			}
			//如果签名没有输入
			if(rintroduction.equals(""))
			{
				rintroduction=user.getUserIntroduction();
			}
			if(user.getPassword().equals(MD5rpass)&&user.getUserHobby().equals(rhobby)
					&&user.getUserIntroduction().equals(rintroduction)
					&&user.getUserPicture().equals(rtemp)){
				addFieldError("rpass","对不起，您没有修改任何资料");
				return INPUT;
			}
			else
			{
				//判断密码是否改变
				
				try{
				tx=session.beginTransaction();
				user.setUserHobby(rhobby);
				user.setUserIntroduction(rintroduction);
				user.setUserPicture(rtemp);
				user.setPassword(MD5rpass);
				session.update(user);
				tx.commit();
				}catch(RuntimeException e)
				{
					if(tx!=null)
					{
						tx.rollback();
					}
					throw e;
				}finally{
					session.close();
				}
				return SUCCESS;
			}
			
		}
	}

}
