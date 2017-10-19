package org.simple.util.common.dto.test;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Role")
@XmlType(propOrder = { "name", "desc" })
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "Name")
	private String name;

	@XmlElement(name = "Desc")
	private String desc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", desc=" + desc + "]";
	}

}
