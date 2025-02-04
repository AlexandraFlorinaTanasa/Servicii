package org.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.entity.Client;

public class TestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

				List<Client> clienti = new ArrayList<Client>();

				clienti.add(new Client(10, "Popescu Mihail", "fizica"));
				clienti.add(new Client(11, "Sandu Maria", "fizica"));
				clienti.add(new Client(12, "Ivascu Elena", "fizica"));
				clienti.add(new Client(13, "Scara Roxana", "fizica"));
				clienti.add(new Client(14, "Smarandeanu Mihai", "fizica"));
				clienti.add(new Client(15, "ALFA", "juridica"));
				clienti.add(new Client(16, "BETA", "juridica"));
				clienti.add(new Client(17, "GAMA", "juridica"));
				clienti.add(new Client(18, "DELTA", "juridica"));
				clienti.add(new Client(19, "Apostol Daria", "fizica"));
				clienti.add(new Client(20, "Burdea Paraschiv", "fizica"));
				clienti.add(new Client(21, "NORD", "juridica"));
				clienti.add(new Client(22, "Barsan Angela", "fizica"));
				clienti.add(new Client(23, "VEST", "juridica"));
				clienti.add(new Client(24, "Cojocaru Carmen", "fizica"));
				clienti.add(new Client(25, "Tanase Camelia", "fizica"));

				EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServiciiJPA");
				EntityManager em = emf.createEntityManager();

				// Clean-up
				em.getTransaction().begin();
				em.createQuery("Delete From Client c").executeUpdate();
				em.getTransaction().commit();

				// Create
				em.persist(clienti.get(0));
				em.persist(clienti.get(1));
				em.persist(clienti.get(2));
				em.persist(clienti.get(3));
				em.persist(clienti.get(4));
				em.persist(clienti.get(5));
				em.persist(clienti.get(6));
				em.persist(clienti.get(7));
				em.persist(clienti.get(8));
				em.persist(clienti.get(9));
				em.persist(clienti.get(10));
				em.persist(clienti.get(11));
				em.persist(clienti.get(12));
				em.persist(clienti.get(13));
				em.persist(clienti.get(14));
				em.persist(clienti.get(15));
				em.getTransaction().begin();
				em.getTransaction().commit();
				em.clear();

				// Read after create
				List<Client>ClientiPersistenti = em.
						createQuery("Select c From Client c", Client.class).getResultList();

				System.out.println("Lista clienti persistenti/salvati in baza de date");
				for (Client c : ClientiPersistenti)
					System.out.println("Id: " + c.getId() + ", nume: " + c.getNume() + ", Tip client: " + c.getTip_client());
							

				// Update/Remove
				em.getTransaction().begin();
				Client c10 = em.find(Client.class, 10);
				if (c10 != null) {
					c10.setNume("Ungureanu Marian");
					c10.setTip_client("fizica");
					
				}
				// Read/Remove

				Client c21 = em.find(Client.class, 21);
				if (c21 != null)
					em.remove(c21);

				// Realizare tranzactie
				em.getTransaction().commit();
				em.clear();

				ClientiPersistenti = em.createQuery("Select c From Client c", Client.class).getResultList();
				System.out.println("Lista finala clienti persistenti (salvati in baza de date):");
				for (Client c: ClientiPersistenti)
					System.out.println("Id: " + c.getId() + ", nume: " + c.getNume() + ", Tip client: "
							+ c.getTip_client());
			}

		}
	


