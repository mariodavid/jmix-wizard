package de.diedavids.jmix.jwexample.screen.checkoutwizard;

import de.diedavids.jmix.jwexample.entity.Address;
import de.diedavids.jmix.jwexample.entity.Customer;
import de.diedavids.jmix.jwexample.entity.Order;
import de.diedavids.jmix.jwexample.entity.OrderLine;
import io.jmix.core.TimeSource;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;

public class CheckoutDataContainerCoordinator  {

    protected InstanceContainer<Address> invoiceAddressDc;
    protected InstanceContainer<Customer> customerDc;
    protected InstanceContainer<Order> orderDc;
    protected DataContext dataContext;
    protected InstanceContainer<Address> deliveryAddressDc;
    protected CollectionPropertyContainer<OrderLine> orderLinesDc;
    private TimeSource timeSource;

    public CheckoutDataContainerCoordinator(
        InstanceContainer<Address> invoiceAddressDc,
        InstanceContainer<Customer> customerDc,
        InstanceContainer<Order> orderDc,
        InstanceContainer<Address> deliveryAddressDc,
        CollectionPropertyContainer<OrderLine> orderLinesDc,
        DataContext dataContext,
        TimeSource timeSource
    ) {
        this.invoiceAddressDc = invoiceAddressDc;
        this.customerDc = customerDc;
        this.orderDc = orderDc;
        this.dataContext = dataContext;
        this.deliveryAddressDc = deliveryAddressDc;
        this.orderLinesDc = orderLinesDc;
        this.timeSource = timeSource;

        Customer customer = initCustomerAddress();
        Address deliveryAddress = initDeliveryAddress(customer);
        initInvoiceAddress();
        initOrder(customer, deliveryAddress);
    }

    private Order initOrder(Customer customer, Address deliveryAddress) {
        Order order = createOrderForCustomerAndDeliveryAddress(customer, deliveryAddress);
        orderDc.setItem(order);
        return order;
    }

    private Order createOrderForCustomerAndDeliveryAddress(
        Customer customer,
        Address deliveryAddress
    ) {
        Order order = dataContext.create(Order.class);
        order.setOrderDate(timeSource.currentTimestamp());
        order.setDeliveryAddress(deliveryAddress);
        order.setInvoiceAddress(deliveryAddress);
        order.setCustomer(customer);
        return order;
    }

    private Customer initCustomerAddress() {
        Customer customer = dataContext.create(Customer.class);
        customerDc.setItem(customer);
        return customer;
    }

    private Address initDeliveryAddress(Customer customer) {
        Address deliveryAddress = createAddressFor(customer);
        deliveryAddressDc.setItem(deliveryAddress);
        return deliveryAddress;
    }

    private Address initInvoiceAddress() {
        Address invoiceAddress = dataContext.create(Address.class);
        invoiceAddressDc.setItem(invoiceAddress);
        return invoiceAddress;
    }


    private Address createAddressFor(Customer customer) {
        Address deliveryAddress = dataContext.create(Address.class);
        deliveryAddress.setCustomer(customer);
        return deliveryAddress;
    }


    public void saveCheckout() {
        if (isDeliveryAddressSameAsInvoiceAddress(invoiceAddressDc.getItem())) {
            removeInvoiceAddress();
        } else {
            setInvoiceAddressReferences(
                customerDc.getItem(),
                invoiceAddressDc.getItem(),
                orderDc.getItem()
            );
        }

        dataContext.commit();
    }

    private boolean isDeliveryAddressSameAsInvoiceAddress(Address invoiceAddress) {
        return invoiceAddress.getStreet() == null && invoiceAddress.getCity() == null
            && invoiceAddress.getCountry() == null && invoiceAddress.getPostalCode() == null;
    }

    private void setInvoiceAddressReferences(
        Customer customer,
        Address invoiceAddress,
        Order order) {
        invoiceAddress.setCustomer(customer);
        order.setInvoiceAddress(invoiceAddress);
    }

    private void removeInvoiceAddress() {
        dataContext.remove(invoiceAddressDc.getItem());
        invoiceAddressDc.setItem(null);
    }

}