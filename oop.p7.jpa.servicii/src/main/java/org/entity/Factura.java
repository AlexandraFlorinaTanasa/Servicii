package org.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
@Entity
public class Factura {
	@Id
@GeneratedValue(strategy = AUTO)
private Integer nrFactura;
@Temporal(DATE)

private Date dataFactura=new Date();
@OneToMany(mappedBy = "factura")
private List<LiniiFacturi> liniiFacturi=new ArrayList<LiniiFacturi>();
@ManyToOne
private Client client;


//Proprietati
public Integer getNrFactura() {
	return nrFactura;
}
public void setNrFactura(Integer nrFactura) {
	this.nrFactura = nrFactura;
}
public Date getDataFactura() {
	return dataFactura;
}
public void setDataFactura(Date dataFactura) {
	this.dataFactura = dataFactura;
}
public List<LiniiFacturi> getLiniiFacturi() {
	return liniiFacturi;
}
public void setLiniiFacturi(List<LiniiFacturi> liniiFacturi) {
	this.liniiFacturi = liniiFacturi;
}
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}
public Double getTotalFactura() {
	if(liniiFacturi.isEmpty()) return null;
	//return totalFactura;
	Double totalFactura=0.0;
	for(LiniiFacturi lF:liniiFacturi)
		totalFactura+=lF.getValoareLinie();
	return totalFactura;
}
Double calcValFactura() {
	Double totalFactura=.0;
	for(LiniiFacturi lF:liniiFacturi) totalFactura +=lF.getValoareLinie();
	return totalFactura;
}
public Double getTotalTVA() {
	if(liniiFacturi.isEmpty())return null;
	Double totalFactura=calcValFactura();
	return 0.19/1.19*totalFactura;
}

//Constructori
public Factura() {}
public Factura(Integer nrFactura, Date dataFactura) {
	super();
	this.nrFactura = nrFactura;
	this.dataFactura = dataFactura;
}



public Factura(Integer nrFactura, Date dataFactura, Client client) {
	super();
	this.nrFactura = nrFactura;
	this.dataFactura = dataFactura;
	this.client = client;
}
public Factura(Integer nrFactura, Date dataFactura, List<LiniiFacturi> liniiFacturi, Client client) {
	super();
	this.nrFactura = nrFactura;
	this.dataFactura = dataFactura;
	this.liniiFacturi = liniiFacturi;
	this.client = client;
}


public Factura(Integer nrFactura, Date dataFactura, List<LiniiFacturi> liniiFacturi, Client client,
		Double totalFactura, Double totalTVA) {
	super();
	this.nrFactura = nrFactura;
	this.dataFactura = dataFactura;
	this.liniiFacturi = liniiFacturi;
	this.client = client;
	
}
//Criteriu de egalitate
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Factura other = (Factura) obj;
	if (nrFactura == null) {
		if (other.nrFactura != null)
			return false;
	} else if (!nrFactura.equals(other.nrFactura))
		return false;
	return true;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nrFactura == null) ? 0 : nrFactura.hashCode());
	return result;
}

//Operatii specifice logicii modelului afacerii
public void adaugaLinie (LiniiFacturi linieFactura) {
	liniiFacturi.add(linieFactura);
}
public void adauga(Serviciu serviciu, Double perContractata) {
	LiniiFacturi lF=new LiniiFacturi();
	lF.setFactura(this);
	lF.setServiciu(serviciu);
	lF.setPerContractata(perContractata);
	this.liniiFacturi.add(lF);
}

public Boolean verificaServiciu (Serviciu serviciu) {
	return null;
}
@Override
public String toString() {
	return "Factura [Numar Factura=" + nrFactura + ", dataFactura=" + dataFactura + ", client=" + client
			+ ", valoare totala=" + getTotalFactura() + ", valoare TVA totala=" + getTotalTVA() + "]";
}
}

