package bbs.database.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Moduleinfo entity. @author MyEclipse Persistence Tools
 */

public class Moduleinfo implements java.io.Serializable {

	// Fields

	private Integer moduleId;
	private Userinfo userinfoByModuleAdmin3;
	private Userinfo userinfoByModuleAdmin1;
	private Userinfo userinfoByModuleAdmin2;
	private String moduleName;
	private String moduleDescription;
	private Set childmoduleinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Moduleinfo() {
	}

	/** full constructor */
	public Moduleinfo(Userinfo userinfoByModuleAdmin3,
			Userinfo userinfoByModuleAdmin1, Userinfo userinfoByModuleAdmin2,
			String moduleName, String moduleDescription, Set childmoduleinfos) {
		this.userinfoByModuleAdmin3 = userinfoByModuleAdmin3;
		this.userinfoByModuleAdmin1 = userinfoByModuleAdmin1;
		this.userinfoByModuleAdmin2 = userinfoByModuleAdmin2;
		this.moduleName = moduleName;
		this.moduleDescription = moduleDescription;
		this.childmoduleinfos = childmoduleinfos;
	}

	// Property accessors

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Userinfo getUserinfoByModuleAdmin3() {
		return this.userinfoByModuleAdmin3;
	}

	public void setUserinfoByModuleAdmin3(Userinfo userinfoByModuleAdmin3) {
		this.userinfoByModuleAdmin3 = userinfoByModuleAdmin3;
	}

	public Userinfo getUserinfoByModuleAdmin1() {
		return this.userinfoByModuleAdmin1;
	}

	public void setUserinfoByModuleAdmin1(Userinfo userinfoByModuleAdmin1) {
		this.userinfoByModuleAdmin1 = userinfoByModuleAdmin1;
	}

	public Userinfo getUserinfoByModuleAdmin2() {
		return this.userinfoByModuleAdmin2;
	}

	public void setUserinfoByModuleAdmin2(Userinfo userinfoByModuleAdmin2) {
		this.userinfoByModuleAdmin2 = userinfoByModuleAdmin2;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDescription() {
		return this.moduleDescription;
	}

	public void setModuleDescription(String moduleDescription) {
		this.moduleDescription = moduleDescription;
	}

	public Set getChildmoduleinfos() {
		return this.childmoduleinfos;
	}

	public void setChildmoduleinfos(Set childmoduleinfos) {
		this.childmoduleinfos = childmoduleinfos;
	}

}