package com.geovannycode.crud.web;

import com.geovannycode.crud.domain.Customer;
import com.geovannycode.crud.domain.CustomerService;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
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

        // additional components
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by name");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER); // Dispara el evento en cada pulsación de tecla
        crud.getCrudLayout().addFilterComponent(filter);

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

        // Configuración de las operaciones
        crud.setOperations(
                () -> {
                    String filterValue = filter.getValue();
                    if (filterValue == null || filterValue.isEmpty()) {
                        return customerService.findAll();
                    } else {
                        return customerService.findByNameContainingIgnoreCase(filterValue);
                    }
                },
                customerService::save,
                customerService::update,
                customerService::delete);

        // Configuración del filtro
        filter.addValueChangeListener(e -> crud.refreshGrid());
    }
}
