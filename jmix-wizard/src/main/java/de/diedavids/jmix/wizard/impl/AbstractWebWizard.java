package de.diedavids.jmix.wizard.impl;


import de.diedavids.jmix.wizard.Wizard;
import io.jmix.core.Messages;
import io.jmix.core.common.event.Subscription;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.ComponentsHelper;
import io.jmix.ui.component.GroupBoxLayout;
import io.jmix.ui.component.SupportsChildrenSelection;
import io.jmix.ui.component.TabSheet;
import io.jmix.ui.component.UiPermissionAware;
import io.jmix.ui.component.impl.CssLayoutImpl;
import io.jmix.ui.component.impl.TabSheetImpl;
import io.jmix.ui.security.UiPermissionDescriptor;
import io.jmix.ui.widget.JmixCssActionsLayout;
import io.jmix.ui.xml.layout.ComponentLoader;
import org.dom4j.Element;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class AbstractWebWizard extends CssLayoutImpl
    implements Wizard, UiPermissionAware, SupportsChildrenSelection, InitializingBean {


    @Autowired
    protected UiComponents uiComponents;

    @Autowired
    protected Messages messages;

    protected GroupBoxLayout layoutWrapper;

    protected TabSheet tabSheetLayout;

    public AbstractWebWizard() {

        component = new JmixCssActionsLayout();

    }


    @Override
    public void afterPropertiesSet() {
        initComponent();
    }

    private void initComponent() {
        layoutWrapper = createLayout();
        com.vaadin.ui.Component unwrap = ComponentsHelper.getComposition(layoutWrapper);
        component.addComponent(unwrap);
    }


    public void setTabSheetLayout(TabSheet tabSheetLayout) {
        this.tabSheetLayout = tabSheetLayout;
    }

    @Override
    public TabSheet.Tab addTab(String name, Component component) {
        return tabSheetLayout.addTab(name, component);
    }

    @Override
    public TabSheet.Tab addLazyTab(
        String name,
        Element descriptor,
        ComponentLoader loader
    ) {
        return tabSheetLayout.addLazyTab(name, descriptor, loader);
    }

    @Override
    public void removeTab(String name) {
        tabSheetLayout.removeTab(name);
    }

    @Override
    public void removeAllTabs() {
        tabSheetLayout.removeAllTabs();
    }

    @Override
    public TabSheet.Tab getSelectedTab() {
        return tabSheetLayout.getSelectedTab();
    }


    @Override
    public TabSheet.Tab getTab(String name) {
        return tabSheetLayout.getTab(name);
    }

    @Override
    public Component getTabComponent(String name) {
        return tabSheetLayout.getTabComponent(name);
    }

    @Override
    public Collection<TabSheet.Tab> getTabs() {
        return tabSheetLayout.getTabs();
    }

    @Override
    public boolean isTabCaptionsAsHtml() {
        return tabSheetLayout.isTabCaptionsAsHtml();
    }

    @Override
    public void setTabCaptionsAsHtml(boolean tabCaptionsAsHtml) {
        tabSheetLayout.setTabCaptionsAsHtml(tabCaptionsAsHtml);
    }

    @Override
    public boolean isTabsVisible() {
        return tabSheetLayout.isTabsVisible();
    }

    @Override
    public void setTabsVisible(boolean tabsVisible) {
        tabSheetLayout.setTabsVisible(tabsVisible);
    }

    @Override
    public Subscription addSelectedTabChangeListener(
        Consumer<TabSheet.SelectedTabChangeEvent> listener
    ) {
        return tabSheetLayout.addSelectedTabChangeListener(listener);
    }

    protected abstract GroupBoxLayout createLayout();

    @Override
    public void focus() {
        tabSheetLayout.focus();
    }

    @Override
    public int getTabIndex() {
        return tabSheetLayout.getTabIndex();
    }

    @Override
    public void setTabIndex(int tabIndex) {
        tabSheetLayout.setTabIndex(tabIndex);
    }

    @Override
    public void setChildSelected(Component childComponent) {
        ((TabSheetImpl) tabSheetLayout).setChildSelected(childComponent);
    }

    @Override
    public boolean isChildSelected(Component component) {
        return ((TabSheetImpl) tabSheetLayout).isChildSelected(component);
    }

    @Override
    public void applyPermission(UiPermissionDescriptor permissionDescriptor) {
        ((TabSheetImpl) tabSheetLayout).applyPermission(permissionDescriptor);
    }


    @Override
    public Component getOwnComponent(String id) {
        return layoutWrapper.getOwnComponent(id);
    }

    @Nullable
    @Override
    public Component getComponent(String id) {
        return layoutWrapper.getComponent(id);
    }

    @Override
    public Collection<Component> getOwnComponents() {
        return layoutWrapper.getOwnComponents();
    }

    @Override
    public Stream<Component> getOwnComponentsStream() {
        return layoutWrapper.getOwnComponentsStream();
    }

    @Override
    public Collection<Component> getComponents() {
        return layoutWrapper.getComponents();
    }


    @Override
    public Subscription addWizardTabChangeListener(Consumer<WizardTabChangeEvent> listener) {
        return getEventHub().subscribe(WizardTabChangeEvent.class, listener);
    }

    @Override
    public void removeWizardTabChangeListener(Consumer<WizardTabChangeEvent> listener) {
        getEventHub().unsubscribe(WizardTabChangeEvent.class, listener);
    }

    @Override
    public Subscription addWizardTabPreChangeListener(Consumer<WizardTabPreChangeEvent> listener) {
        return getEventHub().subscribe(WizardTabPreChangeEvent.class, listener);
    }

    @Override
    public void removeWizardTabPreChangeListener(Consumer<WizardTabPreChangeEvent> listener) {
        getEventHub().unsubscribe(WizardTabPreChangeEvent.class, listener);
    }

    @Override
    public void removeWizardCancelClickListener(Consumer<WizardCancelClickEvent> listener) {
        getEventHub().unsubscribe(WizardCancelClickEvent.class, listener);
    }

    @Override
    public Subscription addWizardCancelClickListener(Consumer<WizardCancelClickEvent> listener) {
        return getEventHub().subscribe(WizardCancelClickEvent.class, listener);
    }

    @Override
    public Subscription addWizardFinishClickListener(Consumer<WizardFinishClickEvent>  listener) {
        return getEventHub().subscribe(WizardFinishClickEvent.class, listener);
    }

    @Override
    public void removeWizardFinishClickListener(Consumer<WizardFinishClickEvent> listener) {
        getEventHub().unsubscribe(WizardFinishClickEvent.class, listener);
    }

}