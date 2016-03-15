package myaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bbs.database.hibernate.Userinfo;

import bean.MD5;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{
	private String username;
	private String password;
	private String rpassword;
	private String hobby;
	private String introduction;
	private String MD5password;
	private String temp;
	// ��װ�ϴ��ļ��������
    private File image;
    // ��װ�ϴ��ļ����͵�����
    private String imageContentType;
    // ��װ�ϴ��ļ���������
    private String imageFileName;
    // ��������ע�������
    //private String savePath="D:/MyEclipse/Workspaces/.metadata/.me_tcat/webapps/bbs3/images";
    private String savePath;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRpassword() {
		return rpassword;
	}
	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getMD5password() {
		return MD5password;
	}
	public void setMD5password(String mD5password) {
		MD5password = mD5password;
	}
	  public String getSavePath() throws Exception{
	        return ServletActionContext.getServletContext().getRealPath(savePath); 
	    }

	    public void setSavePath(String savePath) {
	        this.savePath = savePath;
	    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
    

    public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	private void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println("FileInputStream�ر�ʧ��");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                System.out.println("FileOutputStream�ر�ʧ��");
                e.printStackTrace();
            }
        }
    }
	public String execute()throws Exception{
		Configuration config=new Configuration().configure();
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		List result=session.createQuery("from Userinfo").list();
		if(username.length()>10||username.length()<6){
			addFieldError("username","�û������ȱ�����6-10���ַ�֮��");
			return INPUT;
		}
		for(int i=0;i<result.size();i++)
		{
			Userinfo user=(Userinfo) result.get(i);
			String usernameHas=user.getUserName();
			if(username.equals(usernameHas))
			{
				addFieldError("username","���û����ѱ�ע�ᣬ�뻻�����û���");
				return INPUT;
	
			}
		}
		if(password.isEmpty())
		{
			addFieldError("password","���벻��Ϊ�գ�����������");
			return INPUT;
		}
		if(!password.equals(rpassword)){
			addFieldError("rpassword","�����������벻һ�£�����������");
			return INPUT;
		}
	
		if(hobby.length()>4)
		{
			addFieldError("hobby","�û����ò��ɶ���10����");
			return INPUT;
		}
		if(introduction.length()>4)
		{
			addFieldError("introduction","���ҽ�����50��֮��");
			return INPUT;
		}
		//�ڸ����쳣������֮�����������
		else{
			//���������MD5����
		MD5 md5=new MD5();
		MD5password=md5.getMD5ofStr(password);
		//һ���Ƕ�ͷ�����ش���
		FileOutputStream fos = null;
        FileInputStream fis = null;
        if(image==null)
        	{
        		temp="."+"\\"+"images"+"\\"+"default.jpg";
        		//System.out.println("û���ϴ�ͷ��");
                //System.out.println("getImageFileName="+getImageFileName());
                }
        else{
        try {
        	System.out.println("������RegisterAction ��ʾ���뵽���ļ�������");
            System.out.println("�ļ��ϴ��ɹ����ϴ����ĵ�ַ��"+getSavePath());
        	fos = new FileOutputStream(getSavePath() + "\\" + getImageFileName());
        	
        	temp="."+"\\"+"images"+"\\"+getImageFileName();
            
            //System.out.println("�������ϴ�ͷ��ľ���·��"+temp);
            // �����ļ��ϴ���
            fis = new FileInputStream(getImage());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            
        } catch (Exception e) {
            System.out.println("�ļ��ϴ�ʧ��");
            e.printStackTrace();
            addFieldError("image","�ļ��ϴ�ʧ��");
           
        } finally {
            close(fos, fis);
        }
        }
		Userinfo newuser=new Userinfo();
		newuser.setUserName(username);
		newuser.setPassword(MD5password);
		newuser.setUserHobby(hobby);
		newuser.setUserIntroduction(introduction);
		newuser.setUserLevel("1");
		newuser.setUserType(1);
		newuser.setUserSilenced(0);
		newuser.setUserPicture(temp);
		session.save(newuser);
		Transaction tx=null;
		try{
			tx=session.beginTransaction();
			tx.commit();
		    return SUCCESS;
		}catch(RuntimeException e)
		{
			if(tx!=null)
				tx.rollback();
			throw e;
		}finally {
			session.close();
			sessionFactory.close();
		}
		
		}
		
	}
	

}
