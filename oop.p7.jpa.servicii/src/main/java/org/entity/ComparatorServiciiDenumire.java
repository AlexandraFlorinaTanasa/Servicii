
package org.entity;

import java.util.Comparator;

public class ComparatorServiciiDenumire implements Comparator<Serviciu>{
	@Override
	public int compare(Serviciu s1 ,Serviciu s2) {
		return s1.getDenServiciu().compareTo(s2.getDenServiciu());
	}
}

