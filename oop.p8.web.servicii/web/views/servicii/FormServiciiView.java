package org.app.servicii.web.views.servicii;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import org.entity.Serviciu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.app.servicii.oop.p8.web.servicii.MainView;


	@PageTitle("serviciu")
		@Route(value = "serviciu", layout = MainView.class)
	public class FormServiciiView extends VerticalLayout implements HasUrlParameter<Integer>{

		
		// Definire model date
		private EntityManager em;
		private Serviciu serviciu = null;
		private Binder<Serviciu> binder = new BeanValidationBinder<>(Serviciu.class);
		// Definire componente view
		// Definire Form
		private VerticalLayout formLayoutToolbar;
		private H1 titluForm = new H1("Form Serviciu");
		private IntegerField id = new IntegerField("ID serviciu:");
		private TextField nume = new TextField("Nume serviciu: ");
		// Definire componente actiuni Form-Controller
		private Button cmdAdaugare = new Button("Adauga");
		private Button cmdSterge = new Button("Sterge");
		private Button cmdAbandon = new Button("Abandon");
		private Button cmdSalveaza = new Button("Salveaza");
			// … … //
			// Navigation Management:
			// URL-ul http://localhost:8080/clienti/3 asigură afișare detaliilor clientului cu ID 3
			@Override
			public void setParameter(BeforeEvent event, @OptionalParameter Integer idServiciu) {
			System.out.println("Serviciu ID: " + idServiciu);
			if (idServiciu != null) {
			// EDIT Item
			this.serviciu = em.find(Serviciu.class, idServiciu);
			System.out.println("Selected serviciu to edit:: " + serviciu);
			if (this.serviciu == null) {
			System.out.println("ADD serviciu:: " + serviciu);
			// NEW Item
			this.adaugaServiciu();
			this.serviciu.setIdServiciu(idServiciu);
			this.serviciu.setDenServiciu("Serviciu NOU " + idServiciu);
			}
			}
			this.refreshForm();
			}
			
			// init Data Model
			private void initDataModel(){
			System.out.println("DEBUG START FORM >>> ");
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServiciiJPA");
			this.em = emf.createEntityManager();
			this.serviciu = em
			.createQuery("SELECT s FROM Serviciu s ORDER BY s.idServiciu", Serviciu.class)
			.getResultStream().findFirst().get();
			//
			binder.forField(id).bind("idServiciu");
			binder.forField(nume).bind("denServiciu");
			//
			refreshForm();
			}
			// init View Model
			private void initViewLayout() {
			// Form-Master-Details -----------------------------------//
			// Form-Master
			FormLayout formLayout = new FormLayout();
			formLayout.add(id, nume);
			formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));
			formLayout.setMaxWidth("400px");
			// Toolbar-Actions-Master
			HorizontalLayout actionToolbar =
			new HorizontalLayout(cmdAdaugare, cmdSterge, cmdAbandon, cmdSalveaza);
			actionToolbar.setPadding(false);
			//
			this.formLayoutToolbar = new VerticalLayout(formLayout, actionToolbar);
			// ---------------------------
			this.add(titluForm, formLayoutToolbar);
			//
			}
			// init Controller components
			private void initControllerActions() {
			// Transactional Master Actions
			cmdAdaugare.addClickListener(e -> {
			adaugaServiciu();
			refreshForm();
			});
			cmdSterge.addClickListener(e -> {
			stergeServiciu();
			// Navigate back to NavigableGridClienteForm
			this.getUI().ifPresent(ui -> ui.navigate(
			NavigableGridServiciiView.class)
			);
			});
			cmdAbandon.addClickListener(e -> {
			// Navigate back to NavigableGridClienteForm
			this.getUI().ifPresent(ui -> ui.navigate(
			NavigableGridServiciiView.class, this.serviciu.getIdServiciu())
			);
			});
			cmdSalveaza.addClickListener(e -> {
			salveazaServiciu();
			// refreshForm();
			// Navigate back to NavigableGridClienteForm
			this.getUI().ifPresent(ui -> ui.navigate(
			NavigableGridServiciiView.class, this.serviciu.getIdServiciu())
			);
			});
			}
			private void refreshForm() {
				System.out.println("Serviciu curent: " + this.serviciu);
				if (this.serviciu != null) {
				binder.setBean(this.serviciu);
				}
				}
			// CRUD actions
			private void salveazaServiciu() {
			try {
			this.em.getTransaction().begin();
			this.serviciu = this.em.merge(this.serviciu);
			this.em.getTransaction().commit();
			System.out.println("Serviciu Salvat");
			} catch (Exception ex) {
			if (this.em.getTransaction().isActive())
			this.em.getTransaction().rollback();
			System.out.println("*** EntityManager Validation ex: " + ex.getMessage());
			throw new RuntimeException(ex.getMessage());
			}
			}
			// CRUD actions
			private void adaugaServiciu() {
			this.serviciu = new Serviciu();
			this.serviciu.setIdServiciu(999);  // ID arbitrar, inexistent în baza de date
			this.serviciu.setDenServiciu("Serviciu Nou");
			}
			// CRUD actions
			private void stergeServiciu() {
			System.out.println("To remove: " + this.serviciu);
			if (this.em.contains(this.serviciu)) {
			this.em.getTransaction().begin();
			this.em.remove(this.serviciu);
			this.em.getTransaction().commit();
			}
			}
			// Start Form
			public FormServiciiView() {
			//
			initDataModel();
			//
			initViewLayout();
			//
			initControllerActions();
			}
		}
		
