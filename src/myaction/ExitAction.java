package myaction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExitAction extends ActionSupport{
	private String currentusername;
	
	public String execute() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
	    currentusername=(String) request.getSession().getAttribute("username");
		ActionContext.getContext().getSession().remove("username");
		HttpServletRequest request1=ServletActionContext.getRequest();
	    currentusername=(String) request1.getSession().getAttribute("username");
	    if(currentusername==null)
	    {
	    	System.out.println("�û��˳��ɹ�");
	    }
	    else{
	    	System.out.println("�û��˳�ʧ��");
	    }
	    
	    request.getSession().removeAttribute("admin") ;
		
		return SUCCESS;
	}

}
