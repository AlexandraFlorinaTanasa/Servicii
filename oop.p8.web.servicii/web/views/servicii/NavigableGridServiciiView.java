package org.app.servicii.web.views.servicii;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;




import org.entity.Serviciu;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.app.servicii.oop.p8.web.servicii.MainView;



	@PageTitle("servicii")
	@Route(value = "servicii", layout = MainView.class)

	public class NavigableGridServiciiView  extends VerticalLayout implements HasUrlParameter<Integer>{
		
		
		// Definire model date
		private EntityManager em;
		private List<Serviciu> servicii = new ArrayList<>();
		private Serviciu serviciu = null;
		private Binder<Serviciu> binder = new BeanValidationBinder<>(Serviciu.class);
		
		// Definire componente view
		private H1 titluForm = new H1("Lista Servicii");
		
		// Definire componente suport navigare
		private VerticalLayout gridLayoutToolbar;
		private TextField filterText = new TextField();
		private Button cmdEditServiciu = new Button("Editeaza serviciu...");
		private Button cmdAdaugaServiciu = new Button("Adauga serviciu...");
		private Button cmdStergeServiciu = new Button("Sterge serviciu...");
		private Grid<Serviciu> grid = new Grid<>(Serviciu.class);
		
		// init Data Model
		private void initDataModel(){
		System.out.println("DEBUG START FORM >>> ");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ServiciiJPA");
		em = emf.createEntityManager();
		List<Serviciu> lst = em
		.createQuery("SELECT s FROM Serviciu s ORDER BY s.idServiciu", Serviciu.class)
		.getResultList();
		servicii.addAll(lst);
		if (lst != null && !lst.isEmpty()){
		Collections.sort(this.servicii, (s1, s2) ->  s1.getIdServiciu().compareTo(s2.getIdServiciu()));
		this.serviciu = servicii.get(0);
		System.out.println("DEBUG: serviciu init >>> " + serviciu.getIdServiciu());
		}
		//
		grid.setItems(this.servicii);
		binder.setBean(this.serviciu);
		grid.asSingleSelect().setValue(this.serviciu);
		}
		// init View Model
		private void initViewLayout() {
		// Layout navigare -------------------------------------//
		// Toolbar navigare
		filterText.setPlaceholder("Filter by nume...");
		filterText.setClearButtonVisible(true);
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
		HorizontalLayout gridToolbar = new HorizontalLayout(filterText,
		cmdEditServiciu, cmdAdaugaServiciu, cmdStergeServiciu);
		// Grid navigare
		grid.setColumns("idServiciu", "denServiciu");
		grid.addComponentColumn(item -> createGridActionsButtons(item)).setHeader("Actiuni");
		// Init Layout navigare
		gridLayoutToolbar = new VerticalLayout(gridToolbar, grid);
		// ---------------------------
		this.add(titluForm, gridLayoutToolbar);
		//
		}
		private Component createGridActionsButtons(Serviciu item) {
			//
			Button cmdEditItem = new Button("Edit");
			cmdEditItem.addClickListener(e -> {
			grid.asSingleSelect().setValue(item);
			editServiciu();
			});
			Button cmdDeleteItem = new Button("Sterge");
			cmdDeleteItem.addClickListener(e -> {
			System.out.println("Sterge item: " + item);
			grid.asSingleSelect().setValue(item);
			stergeServiciu();
			refreshForm();
			} );
			//
			return new HorizontalLayout(cmdEditItem, cmdDeleteItem);
			}
		
		// init Controller components
		private void initControllerActions() {
		// Navigation Actions
		filterText.addValueChangeListener(e -> updateList());
		cmdEditServiciu.addClickListener(e -> {
		editServiciu();
		});
		cmdAdaugaServiciu.addClickListener(e -> {
		adaugaServiciu();
		});
		cmdStergeServiciu.addClickListener(e -> {
		stergeServiciu();
		refreshForm();
		});
		}
		// CRUD actions
		// Adaugare: delegare catre Formular detalii serviciu
		private void adaugaServiciu() {
		this.getUI().ifPresent(ui -> ui.navigate(FormServiciiView.class, 999));
		}
		// Editare: delegare catre Formular detalii serviciu
		private void editServiciu() {
		this.serviciu = this.grid.asSingleSelect().getValue();
		System.out.println("Selected serviciu:: " + serviciu);
		if (this.serviciu != null) {
		this.getUI().ifPresent(ui -> ui.navigate(
		FormServiciiView.class, this.serviciu.getIdServiciu())
		);
		}
		}
		// CRUD actions
		// Stergere: tranzactie locala cu EntityManager
		private void stergeServiciu() {
		this.serviciu = this.grid.asSingleSelect().getValue();
		System.out.println("To remove: " + this.serviciu);
		this.servicii.remove(this.serviciu);
		if (this.em.contains(this.serviciu)) {
		this.em.getTransaction().begin();
		this.em.remove(this.serviciu);
		this.em.getTransaction().commit();
		}
		if (!this.servicii.isEmpty())
		this.serviciu = this.servicii.get(0);
		else
		this.serviciu = null;
		}
		// Start Form
		public NavigableGridServiciiView() {
		//
		initDataModel();
		//
		initViewLayout();
		//
		initControllerActions();
		}
		// Populare grid cu set de date din model - filtrare
		private void updateList() {
		try {
		List<Serviciu> lstServiciiFiltered = this.servicii;
		if (filterText.getValue() != null) {
		lstServiciiFiltered = this.servicii.stream()
		.filter(s -> s.getDenServiciu().contains(filterText.getValue()))
		.toList();
		grid.setItems(lstServiciiFiltered);
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		// Resincronizare componente-view cu modelul de date
		private void refreshForm() {
		System.out.println("Serviciu curent: " + this.serviciu);
		if (this.serviciu != null) {
		grid.setItems(this.servicii);
		binder.setBean(this.serviciu);
		grid.select(this.serviciu);
		}
		}
		
		// … … //
		// Navigation Management:
		// URL-ul http://localhost:8080/clienti/3 asigură selecția clientului cu ID 3
		@Override
		public void setParameter(BeforeEvent event, @OptionalParameter Integer idServiciu) {
		if (idServiciu != null) {
		this.serviciu = em.find(Serviciu.class, idServiciu);
		System.out.println("Back serviciu: " + serviciu);
		if (this.serviciu == null) {
		// DELETED Item
		if (!this.servicii.isEmpty())
		this.serviciu = this.servicii.get(0);
		}
		// else: EDITED or NEW Item
		}
		this.refreshForm();
		}
		// … … //
		}
	 
