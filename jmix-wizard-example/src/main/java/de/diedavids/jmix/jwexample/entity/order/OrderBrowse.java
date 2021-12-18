package de.diedavids.jmix.jwexample.entity.order;

import io.jmix.ui.screen.*;
import de.diedavids.jmix.jwexample.entity.Order;

@UiController("Order_.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
public class OrderBrowse extends StandardLookup<Order> {
}