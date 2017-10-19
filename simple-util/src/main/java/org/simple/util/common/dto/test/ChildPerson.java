package org.simple.util.common.dto.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ChildPerson", namespace = "name_string")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "childName" })
public class ChildPerson extends Person {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "ChildName")
	private String childName;

	public ChildPerson() {
		super();
	}

	public ChildPerson(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	@Override
	public String toString() {
		return "ChildPerson [childName=" + childName + ", getName()=" + getName() + ", getAge()=" + getAge() + ", getRole()=" + getRole()
				+ ", getProvinceList()=" + getProvinceList() + ", getSalary()=" + getSalary() + ", getCreateTime()=" + getCreateTime() + "]";
	}

}
