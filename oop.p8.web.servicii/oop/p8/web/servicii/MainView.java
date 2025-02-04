package org.app.servicii.oop.p8.web.servicii;

import org.app.servicii.web.views.clienti.FormClientView;
import org.app.servicii.web.views.clienti.NavigableGridClientiView;
import org.app.servicii.web.views.servicii.FormServiciiView;
import org.app.servicii.web.views.servicii.NavigableGridServiciiView;
import com.vaadin.flow.component.UI;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

/**
 * The main view contains a button and a click listener.
 */
@Route
public class MainView extends VerticalLayout implements RouterLayout {
public MainView() {
setMenuBar();
}
private void setMenuBar() {
MenuBar mainMenu = new MenuBar();
MenuItem homeMenu = mainMenu.addItem("Home");
homeMenu.addClickListener(event -> UI.getCurrent().navigate(MainView.class));
//
MenuItem gridFormsClientiMenu = mainMenu.addItem("Clienti");
SubMenu gridFormsClientiMenuBar = gridFormsClientiMenu.getSubMenu();
gridFormsClientiMenuBar.addItem("Lista Clienti...",
event -> UI.getCurrent().navigate(NavigableGridClientiView.class));
gridFormsClientiMenuBar.addItem("Form Editare Client...",
event -> UI.getCurrent().navigate(FormClientView.class));
//


MenuItem gridFormsServiciiMenu = mainMenu.addItem("Servicii");
SubMenu gridFormsServiciiMenuBar = gridFormsServiciiMenu.getSubMenu();
gridFormsServiciiMenuBar.addItem("Lista Servicii...",
event -> UI.getCurrent().navigate(NavigableGridServiciiView.class));
gridFormsServiciiMenuBar.addItem("Form Editare Serviciu...",
event -> UI.getCurrent().navigate(FormServiciiView.class));
//
add(new HorizontalLayout(mainMenu));
}
}
