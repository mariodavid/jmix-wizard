package de.diedavids.jmix.jwexample.entity.address;

import io.jmix.ui.screen.*;
import de.diedavids.jmix.jwexample.entity.Address;

@UiController("Address.edit")
@UiDescriptor("address-edit.xml")
@EditedEntityContainer("addressDc")
public class AddressEdit extends StandardEditor<Address> {
}