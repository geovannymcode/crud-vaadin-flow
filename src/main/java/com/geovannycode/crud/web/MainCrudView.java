package com.geovannycode.crud.web;

import com.geovannycode.crud.domain.Customer;
import com.geovannycode.crud.domain.CustomerService;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import com.vaadin.flow.component.icon.Icon;

@PageTitle("List of Customers")
@Route("")
@Uses(Icon.class)
public class MainCrudView extends VerticalLayout {

    public MainCrudView(@Autowired CustomerService customerService) {
        GridCrud<Customer> crud = new GridCrud<>(Customer.class);

        // Configuración del Grid
        crud.getGrid().setColumns("id","name", "email", "phone", "address", "city", "state", "zip", "country");
        crud.getGrid().setColumnReorderingAllowed(true);

        // Configuración del Formulario
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getCrudFormFactory().setVisibleProperties(
                "name", "email", "phone", "address", "city", "state", "zip", "country");
        crud.getCrudFormFactory().setVisibleProperties(
                CrudOperation.ADD,
                "name", "email", "phone", "address", "city", "state", "zip", "country");

        // Configuración del Layout
        setSizeFull();
        add(crud);
        crud.setFindAllOperationVisible(false);

        crud.setOperations(
                customerService::findAll,
                customerService::save,
                customerService::update,
                customerService::delete);

    }
}
