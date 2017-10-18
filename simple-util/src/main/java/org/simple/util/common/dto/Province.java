package org.simple.util.common.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Province")
@XmlType(propOrder = { "name", "provCity" })
public class Province implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "ProvinceName")
	private String name;

	@XmlElement(name = "ProvinceCity")
	private String provCity;

	public Province() {
		super();
	}

	public Province(String name, String provCity) {
		super();
		this.name = name;
		this.provCity = provCity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvCity() {
		return provCity;
	}

	public void setProvCity(String provCity) {
		this.provCity = provCity;
	}

	@Override
	public String toString() {
		return "Province [name=" + name + ", provCity=" + provCity + "]";
	}

}
