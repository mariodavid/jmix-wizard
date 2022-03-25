package de.diedavids.jmix.jwexample.entity.orderline;

import io.jmix.ui.screen.*;
import de.diedavids.jmix.jwexample.entity.OrderLine;

@UiController("OrderLine.edit")
@UiDescriptor("order-line-edit.xml")
@EditedEntityContainer("orderLineDc")
public class OrderLineEdit extends StandardEditor<OrderLine> {
}