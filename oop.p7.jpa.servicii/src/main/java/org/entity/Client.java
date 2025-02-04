package org.entity;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer id;
	private String nume;
	private String tip_client; //fizica/juridica
	
	//Proprietati
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getTip_client() {
		return tip_client;
	}
	public void setTip_client(String tip_client) {
		this.tip_client = tip_client;
	}
	
	//Constructori
	public Client() {
		super();
	}
	public Client(Integer id, String nume, String tip_client) {
		super();
		this.id = id;
		this.nume = nume;
		this.tip_client = tip_client;
	}

	//Criteriu egalitate
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", nume=" + nume + ", tip_client=" + tip_client + "]";
	}
	
	
}
