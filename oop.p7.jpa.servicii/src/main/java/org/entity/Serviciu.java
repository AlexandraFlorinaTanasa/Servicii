package org.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class Serviciu {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Integer idServiciu;
	private String denServiciu;
	private Double pretServiciu;
	
	//Proprietati
		public Integer getIdServiciu() {
			return idServiciu;
		}

		public void setIdServiciu(Integer idServiciu) {
			this.idServiciu = idServiciu;
		}

		public String getDenServiciu() {
			return denServiciu;
		}

		public void setDenServiciu(String denServiciu) {
			this.denServiciu = denServiciu;
		}

		public Double getPretServiciu() {
			return pretServiciu;
		}

		public void setPretServiciu(Double pretServiciu) {
			this.pretServiciu = pretServiciu;
		}

		//Constructori (default si cu parametri)
		public Serviciu() {
			super();
		}

		public Serviciu(Integer idServiciu, String denServiciu, Double pretServiciu) {
			super();
			this.idServiciu = idServiciu;
			this.denServiciu = denServiciu;
			this.pretServiciu = pretServiciu;
		}

		//Metoda HashCode()
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idServiciu == null) ? 0 : idServiciu.hashCode());
			return result;
		}

		//Metoda equals
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Serviciu other = (Serviciu) obj;
			if (idServiciu == null) {
				if (other.idServiciu != null)
					return false;
			} else if (!idServiciu.equals(other.idServiciu))
				return false;
			return true;
		}

		//toString
		@Override
		public String toString() {
			return "Serviciu [idServiciu=" + idServiciu + ", denServiciu=" + denServiciu + ", pretServiciu=" + pretServiciu
					+ "]";
		}
	
}
