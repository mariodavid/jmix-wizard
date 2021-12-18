package de.diedavids.jmix.jwexample.entity.order;

import io.jmix.ui.screen.*;
import de.diedavids.jmix.jwexample.entity.Order;

@UiController("Order_.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
public class OrderEdit extends StandardEditor<Order> {
}