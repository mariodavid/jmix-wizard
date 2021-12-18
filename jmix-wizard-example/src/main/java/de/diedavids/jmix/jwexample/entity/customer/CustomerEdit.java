package de.diedavids.jmix.jwexample.entity.customer;

import io.jmix.ui.screen.*;
import de.diedavids.jmix.jwexample.entity.Customer;

@UiController("Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
public class CustomerEdit extends StandardEditor<Customer> {
}