package org.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

@Entity
	
	
	public class LiniiFacturi {
@Id
		
		@GeneratedValue(strategy = AUTO)
		private Integer idLinie;
		@ManyToOne
		Serviciu serviciu;
		Double perContractata;// perioada pe care este contractat serviciul (in zile)
		@ManyToOne
		Factura factura;
		private Double TVALinie;
		private Double valoareLinie;
		
		
		//Proprietati	
		public Integer getIdLinie() {
			return idLinie;
		}
		public void setIdLinie(Integer idLinie) {
			this.idLinie = idLinie;
		}
		public Serviciu getServiciu() {
			return serviciu;
		}
		public void setServiciu(Serviciu serviciu) {
			this.serviciu = serviciu;
			
		}
		public Double getPerContractata() {
			return perContractata;
		}
		public void setPerContractata(Double perContractata) {
			this.perContractata = perContractata;
		}
		public Factura getFactura() {
			return factura;
		}
		public void setFactura(Factura factura) {
			this.factura = factura;
		}
		
		public Double getTVALinie() {
			if(TVALinie==null || TVALinie==0) TVALinie=calcTVALinie();
			return TVALinie;
		}
		public Double getValoareLinie() {
			if(valoareLinie==null || valoareLinie==0.0) valoareLinie=calcValLinie();
			return valoareLinie;
		}
		Double calcValLinie() {
			Double val=null;
			if(serviciu!=null && perContractata!=null)
				val=serviciu.getPretServiciu()*perContractata;
			return val;
		}
		Double calcTVALinie() {
			Double valTVA=null;
			if(serviciu!=null && perContractata!=null)
				valTVA=0.19/1.19*(serviciu.getPretServiciu()*perContractata);
			return valTVA;
		}
		
		
		//Constructori
		public LiniiFacturi() {}
		public LiniiFacturi(Integer idLinie, Serviciu serviciu, Double perContractata) {
			super();
			this.idLinie = idLinie;
			this.serviciu = serviciu;
			this.perContractata = perContractata;
		}
		public LiniiFacturi(Integer idLinie, Serviciu serviciu, Double perContractata, Factura factura) {
			super();
			this.idLinie = idLinie;
			this.serviciu = serviciu;
			this.perContractata = perContractata;
			this.factura = factura;
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
			LiniiFacturi other = (LiniiFacturi) obj;
			if (idLinie == null) {
				if (other.idLinie != null)
					return false;
			} else if (!idLinie.equals(other.idLinie))
				return false;
			return true;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idLinie == null) ? 0 : idLinie.hashCode());
			return result;
		}
		@Override
		public String toString() {
			return "LiniiFacturi [idLinie=" + idLinie + ", serviciu=" + serviciu + ", perioada contractata=" + perContractata
					+ ", valoarea TVA serviciu=" + getTVALinie() + ", valoare Serviciu=" + getValoareLinie() + "]";
		}
	}
		
		
		
		
		
	
