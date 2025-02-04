package org.entity;

import java.util.Objects;

import javax.persistence.Entity;
@Entity
public class Livrare extends Serviciu {
	
	private Integer nrLivrare;
	private String tipLivrare; //urgent, uzual
	
	private Integer Transport;
	private String tipTransport; //gratuit, inclus in pret, plata in avans
	
	public Integer getNrLivrare() {
		return nrLivrare;
	}
	public void setNrLivrare(Integer nrLivrare) {
		this.nrLivrare = nrLivrare;
	}
	public String getTipLivrare() {
		return tipLivrare;
	}
	public void setTipLivrare(String tipLivrare) {
		this.tipLivrare = tipLivrare;
	}
	public Integer getTransport() {
		return Transport;
	}
	public void setTransport(Integer transport) {
		Transport = transport;
	}
	public String getTipTransport() {
		return tipTransport;
	}
	public void setTipTransport(String tipTransport) {
		this.tipTransport = tipTransport;
	}
	public Livrare(Integer idServiciu, String denServiciu, Double pretServiciu, Integer nrLivrare, String tipLivrare,
			Integer transport, String tipTransport) {
		super(idServiciu, denServiciu, pretServiciu);
		this.nrLivrare = nrLivrare;
		this.tipLivrare = tipLivrare;
		Transport = transport;
		this.tipTransport = tipTransport;
	}
	public Livrare() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nrLivrare);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livrare other = (Livrare) obj;
		return Objects.equals(nrLivrare, other.nrLivrare);
	}
	@Override
	public String toString() {
		return "Livrare [nrLivrare=" + nrLivrare + ", tipLivrare=" + tipLivrare + ", Transport=" + Transport
				+ ", tipTransport=" + tipTransport + "]";
	}
	
	
}