package de.diedavids.jmix.jwexample.entity.productprice;

import io.jmix.ui.screen.*;
import de.diedavids.jmix.jwexample.entity.ProductPrice;

@UiController("ProductPrice.edit")
@UiDescriptor("product-price-edit.xml")
@EditedEntityContainer("productPriceDc")
public class ProductPriceEdit extends StandardEditor<ProductPrice> {
}