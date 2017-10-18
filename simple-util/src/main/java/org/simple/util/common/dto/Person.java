package org.simple.util.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.simple.util.common.JaxbDateSerializer;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Person")
@XmlType(propOrder = { "name", "age", "salary", "role", "provinceList", "createTime" })
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "Name") // 不标示的话默认会使用属性的SimpleName来表示xml结构
	private String name;

	@XmlElement(name = "Age")
	private int age;

	@XmlElement(name = "Role")
	private Role role;

	@XmlElementWrapper(name = "Provinces")
	@XmlElement(name = "Province")
	private List<Province> provinceList;

	@XmlElement(name = "Salary")
	private Double salary;

	@XmlElement(name = "CreateTime")
	@XmlJavaTypeAdapter(JaxbDateSerializer.class)
	private Date createTime;

	public Person() {
		super();
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Province> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", role=" + role + ", provinceList=" + provinceList + ", salary=" + salary + ", createTime="
				+ createTime + "]";
	}

}
