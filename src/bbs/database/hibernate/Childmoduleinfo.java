package bbs.database.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Childmoduleinfo entity. @author MyEclipse Persistence Tools
 */

public class Childmoduleinfo implements java.io.Serializable {

	// Fields

	private Integer childModuleId;
	private Moduleinfo moduleinfo;
	private String childModuleName;
	private Set topicinfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Childmoduleinfo() {
	}

	/** full constructor */
	public Childmoduleinfo(Moduleinfo moduleinfo, String childModuleName,
			Set topicinfos) {
		this.moduleinfo = moduleinfo;
		this.childModuleName = childModuleName;
		this.topicinfos = topicinfos;
	}

	// Property accessors

	public Integer getChildModuleId() {
		return this.childModuleId;
	}

	public void setChildModuleId(Integer childModuleId) {
		this.childModuleId = childModuleId;
	}

	public Moduleinfo getModuleinfo() {
		return this.moduleinfo;
	}

	public void setModuleinfo(Moduleinfo moduleinfo) {
		this.moduleinfo = moduleinfo;
	}

	public String getChildModuleName() {
		return this.childModuleName;
	}

	public void setChildModuleName(String childModuleName) {
		this.childModuleName = childModuleName;
	}

	public Set getTopicinfos() {
		return this.topicinfos;
	}

	public void setTopicinfos(Set topicinfos) {
		this.topicinfos = topicinfos;
	}

}