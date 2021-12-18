package de.diedavids.jmix.jwexample.entity.product;

import io.jmix.ui.screen.*;
import de.diedavids.jmix.jwexample.entity.Product;

@UiController("Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
public class ProductEdit extends StandardEditor<Product> {
}