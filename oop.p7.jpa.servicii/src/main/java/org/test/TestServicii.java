package org.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.entity.Catering;
import org.entity.Livrare;
import org.entity.Serviciu;

public class TestServicii {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("ServiciiJPA");
		EntityManager em=emf.createEntityManager();
		

	
		//POPULARE SERVICII
		List <Serviciu> lstServiciiPersistente=em.createQuery("Select s From Serviciu s",Serviciu.class).getResultList();
		if(!lstServiciiPersistente.isEmpty()) {
			em.getTransaction().begin();
			for(Serviciu s:lstServiciiPersistente) em.remove(s);
			em.getTransaction().commit();
		}
		List<Serviciu> catalogServicii=new ArrayList<Serviciu>();
		//Initializare explicita  a unor servicii oferite
		Catering s1=new Catering(1,"Catering_1",250.0,200,"Bufet Suedez","Craciun");
		Catering s2=new Catering (2,"Catering_2",540.0,201,"Evenimente speciale","Paste");
		catalogServicii.add(s1);
		catalogServicii.add(s2);
		
		Livrare s3= new Livrare(3,"Livrare_1",300.0,300,"urgent",400,"gratuit");
		Livrare s4= new Livrare(4,"Livrare_2",230.0,301,"uzual",401,"plata in avans" );
		catalogServicii.add(s3);
		catalogServicii.add(s4);
		
		
		
		em.getTransaction().begin();
		catalogServicii.stream().forEach(s->em.persist(s));
		em.getTransaction().commit();
		//Read after create
		lstServiciiPersistente=em.createQuery("Select s From Serviciu s",Serviciu.class).getResultList();
		System.out.println("Lista serviciilor persistente/salvate in baza de date");
		for (Serviciu s:lstServiciiPersistente)
			System.out.println("Id: "+s.getIdServiciu()+", denumire: "+s.getDenServiciu()+", pret: "+s.getPretServiciu().toString());
	}
	}
