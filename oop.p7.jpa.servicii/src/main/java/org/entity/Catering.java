package org.entity;

import java.util.Objects;

import javax.persistence.Entity;
@Entity
public class Catering extends Serviciu {
	private Integer idTipServiciu;
 private String tipCatering; //bufet suedez, evenimente speciale
 private String perioada;
 
public Integer getIdTipServiciu() {
	return idTipServiciu;
}
public void setIdTipServiciu(Integer idTipServiciu) {
	this.idTipServiciu = idTipServiciu;
}
public String getTipCatering() {
	return tipCatering;
}
public void setTipCatering(String tipCatering) {
	this.tipCatering = tipCatering;
}
public String getPerioada() {
	return perioada;
}
public void setPerioada(String perioada) {
	this.perioada = perioada;
}
public Catering(Integer idServiciu, String denServiciu, Double pretServiciu, Integer idTipServiciu, String tipCatering,
		String perioada) {
	super(idServiciu, denServiciu, pretServiciu);
	this.idTipServiciu = idTipServiciu;
	this.tipCatering = tipCatering;
	this.perioada = perioada;
}
public Catering() {
	super();
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(idTipServiciu);
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
	Catering other = (Catering) obj;
	return Objects.equals(idTipServiciu, other.idTipServiciu);
}
@Override
public String toString() {
	return "Catering [idTipServiciu=" + idTipServiciu + ", tipCatering=" + tipCatering + ", perioada=" + perioada + "]";
}
 
}
