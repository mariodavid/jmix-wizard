package de.diedavids.jmix.jwexample.entity.customer;

import io.jmix.ui.screen.*;
import de.diedavids.jmix.jwexample.entity.Customer;

@UiController("Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
public class CustomerBrowse extends StandardLookup<Customer> {
}