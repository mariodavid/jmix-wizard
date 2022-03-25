package de.diedavids.jmix.jwexample.entity.product;

import io.jmix.ui.screen.*;
import de.diedavids.jmix.jwexample.entity.Product;

@UiController("Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
public class ProductBrowse extends StandardLookup<Product> {
}