package myaction;


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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	private String username;
	private String password;
	private String MD5password;
	private String code;
	private String rand;
	private String name;
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
	
	public String getMD5password() {
		return MD5password;
	}
	public void setMD5password(String mD5password) {
		MD5password = mD5password;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String execute() throws Exception{
		
		Configuration config=new Configuration().configure();
		SessionFactory sessionFactory=config.buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx=null;
		List result=session.createQuery("from Userinfo").list();
		Boolean hasRegister=false;
		Boolean rightPassword=false;
		
		//���ж��û��Ƿ��Ѿ�ע��
		for(int i=0;i<result.size();i++)
		{
			Userinfo user=(Userinfo) result.get(i);
			String usernameHas=user.getUserName();
			if(username.equals(usernameHas))//����û����Ѿ�����
			{   
				
				hasRegister=true;
				break;
			}
			
		}
		//���û��ע�ᣬ��ʾ��ע��
		if(!hasRegister)
		{
			addFieldError("username","����û��ע�ᣬ������ע��");
			return INPUT;
		}
		//����Ѿ�ע����ʼ�ж�ͬһ�û����������Ƿ���ȷ
		else{
			
			MD5 md5=new MD5();
			MD5password=md5.getMD5ofStr(password);
			for(int j=0;j<result.size();j++)
			{
				Userinfo user=(Userinfo) result.get(j);
				String passwordHas=user.getPassword();
				String userHas=user.getUserName();
				if(MD5password.equals(passwordHas)&&username.equals(userHas))
				{
					rightPassword=true;
					break;
				}
				}
			if(rightPassword){//�����֤�������Ƿ���ȷ
				HttpServletRequest request=ServletActionContext.getRequest();
				rand=(String) request.getSession().getAttribute("rand");
				if(rand.equals(code))
				{
					ActionContext.getContext().getSession().put("username", username);
					ActionContext.getContext().getSession().put("password", password);
					Userinfo user=new Userinfo();
					HttpServletRequest request1=ServletActionContext.getRequest();
					name=(String) request1.getSession().getAttribute("username");
					Criteria cr=session.createCriteria(Userinfo.class);
					cr.add(Restrictions.eq("userName", name));
					List<Userinfo> user1=cr.list();
					Iterator<Userinfo> it=user1.iterator();
					user=(Userinfo) it.next();
					
					int s=user.getUserType();
					if(s==3)
						return LOGIN;
					else if(s==2)
					{
						ActionContext.getContext().getSession().put("admin", 2);
						return NONE;
					}
					else
						return SUCCESS;
					
				}
				else{
					addFieldError("code","��֤�����������������");
					return ERROR;
					}
			}
			else{
				addFieldError("password","����������벻��ȷ������������");
				return ERROR;
			}
			}
		}
			
		
		
	}


