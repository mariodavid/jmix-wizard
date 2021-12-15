package de.diedavids.jmix.wizard.impl;

import de.diedavids.jmix.wizard.Wizard;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.ButtonsPanel;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.GroupBoxLayout;
import io.jmix.ui.component.ShortcutAction;
import io.jmix.ui.component.TabSheet;
import io.jmix.ui.xml.layout.ComponentLoader;
import org.dom4j.Element;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class WizardImpl extends AbstractWebWizard {

    protected List<TabSheet.Tab> tabList = new LinkedList<>();

    protected Wizard wizard;
    protected TabSheet.Tab currentTab;

    private WizardButtonsPanel buttonsPanel;

    @Override
    protected GroupBoxLayout createLayout() {

        buttonsPanel = new WizardButtonsPanel(
                uiComponents,
                messages
        );

        if (tabSheetLayout == null) {
            layoutWrapper = uiComponents.create(GroupBoxLayout.class);

            layoutWrapper.setWidthFull();
            layoutWrapper.setHeightFull();

            addWizardShortcutActions();
            tabSheetLayout = createTabSheetLayout();

            layoutWrapper.add(tabSheetLayout);
            layoutWrapper.expand(tabSheetLayout);
        } else {
            Collection<Component> components = tabSheetLayout.getComponents();
            for (Component component : components) {
                tabSheetLayout.remove(component);
            }
        }

        layoutWrapper.focusFirstComponent();
        ButtonsPanel buttonsPanel = createWizardButtonPanel();

        tabSheetLayout.setStyleName("centered-tabs equal-width-tabs icons-on-top");

        tabSheetLayout.addSelectedTabChangeListener(event -> {
            setCurrentTab(event);
            disableAllOtherTabs(event.getSelectedTab());
            refreshWizardButtonPanel();
        });

        layoutWrapper.add(buttonsPanel);

        return layoutWrapper;
    }

    private void addWizardShortcutActions() {
        layoutWrapper.addShortcutAction(new ShortcutAction("CTRL-ALT-ARROW_RIGHT",
                shortcutTriggeredEvent -> nextTab()));
        layoutWrapper.addShortcutAction(new ShortcutAction("CTRL-ALT-ARROW_LEFT",
                shortcutTriggeredEvent -> previousTab()));
    }

    @Override
    public Tab addTab(String name, Component component) {
        final Tab tab = super.addTab(name, component);

        tabList.add(tab);
        disableAllOtherTabs(currentTab);

        return tab;
    }

    @Override
    public Tab addLazyTab(String name, Element descriptor, ComponentLoader loader) {
        final Tab tab = super.addLazyTab(name, descriptor, loader);

        tabList.add(tab);
        disableAllOtherTabs(currentTab);

        return tab;
    }

    private TabSheet createTabSheetLayout() {
        TabSheet tabSheetLayout = uiComponents.create(TabSheet.class);
        tabSheetLayout.setWidthFull();
        return tabSheetLayout;
    }

    private void setCurrentTab(TabSheet.SelectedTabChangeEvent event) {
        currentTab = event.getSelectedTab();
    }

    private void refreshWizardButtonPanel() {
        buttonsPanel.refresh();
    }

    private void disableAllOtherTabs(TabSheet.Tab exludingTab) {

        for (TabSheet.Tab tab : tabList) {
            if (tab.equals(exludingTab)) {
                enableTab(tab);
            } else {
                disableTab(tab);
            }
        }
    }

    private void enableTab(TabSheet.Tab tab) {
        tab.setEnabled(true);
    }

    private void disableTab(TabSheet.Tab tab) {
        tab.setEnabled(false);
    }

    private ButtonsPanel createWizardButtonPanel() {

        return buttonsPanel.createWizardButtonPanel(
                asList(
                        WizardButtonsPanel.buttonDescriptor(
                                WizardButtonsPanel.WizardButtonType.CANCEL,
                                this::handleCancelClick,
                                () -> true
                        ),
                        WizardButtonsPanel.buttonDescriptor(
                                WizardButtonsPanel.WizardButtonType.PREVIOUS,
                                e -> previousTab(),
                                () -> !currentTabIsFirstTab()
                        ),
                        WizardButtonsPanel.buttonDescriptor(
                                WizardButtonsPanel.WizardButtonType.NEXT,
                                e -> nextTab(),
                                () -> !currentTabIsLastTab()
                        ),
                        WizardButtonsPanel.buttonDescriptor(
                                WizardButtonsPanel.WizardButtonType.FINISH,
                                this::handleFinishClick,
                                this::currentTabIsLastTab
                        )
                )
        );
    }

    private void handleCancelClick(Action.ActionPerformedEvent actionPerformedEvent) {
        getEventHub().publish(WizardCancelClickEvent.class, new WizardCancelClickEvent(this));
    }

    private void switchToTab(
            Tab destination,
            Direction direction
    ) {

        WizardTabPreChangeEvent tabPreChangeEvent = new WizardTabPreChangeEvent(
                this,
                currentTab,
                destination,
                direction
        );
        getEventHub().publish(WizardTabPreChangeEvent.class, tabPreChangeEvent);

        if (!tabPreChangeEvent.isTabChangePrevented()) {

            enableTab(destination);

            tabSheetLayout.setSelectedTab(destination);

            WizardTabChangeEvent wizardTabChangeEvent = new WizardTabChangeEvent(
                    this,
                    currentTab,
                    destination,
                    direction
            );
            getEventHub().publish(WizardTabChangeEvent.class, wizardTabChangeEvent);

        }
    }


    private void handleFinishClick(Action.ActionPerformedEvent actionPerformedEvent) {
        WizardFinishClickEvent finishClickEvent = new WizardFinishClickEvent(this);
        publish(WizardFinishClickEvent.class, finishClickEvent);
    }

    private boolean currentTabIsLastTab() {
        if (tabList.isEmpty()) {
            return false;
        }

        return getCurrentTabIndex() == tabList.size() - 1;

    }

    private boolean currentTabIsFirstTab() {
        return getCurrentTabIndex() == 0;
    }

    private TabSheet.Tab findNextTab() {
        int resultTabIndex = getCurrentTabIndex() + 1;
        return findTab(resultTabIndex);
    }

    private TabSheet.Tab findTab(int tabIndex) {
        if (tabIndex >= 0 && tabIndex <= tabList.size() - 1) {
            return tabList.get(tabIndex);
        } else {
            return null;
        }
    }

    private TabSheet.Tab findPrevTab() {
        return findTab(getCurrentTabIndex() - 1);
    }

    private int getCurrentTabIndex() {
        return getTabIndex(currentTab);
    }

    private int getTabIndex(TabSheet.Tab tabToFind) {
        int possibleCurrentTabIndex = 0;
        int result = 0;

        for (TabSheet.Tab tab : tabList) {
            if (tab == tabToFind) {
                result = possibleCurrentTabIndex;
            }
            possibleCurrentTabIndex++;
        }
        return result;
    }

    @Override
    public void nextTab() {
        switchToTab(findNextTab(), Direction.NEXT);
    }

    @Override
    public void previousTab() {
        switchToTab(findPrevTab(), Direction.PREVIOUS);
    }

    @Override
    public void setSelectedTab(Tab tab) {
        switchToTab(tab, determineDirection(tab));
    }

    private Direction determineDirection(Tab tab) {
        final int currentIndex = getTabIndex(currentTab);
        final int newIndex = getTabIndex(tab);

        if (currentIndex < newIndex) {
            return Direction.NEXT;
        } else {
            return Direction.PREVIOUS;
        }
    }

    @Override
    public void setSelectedTab(String name) {
        final Tab tab = getTab(name);
        switchToTab(tab, determineDirection(tab));
    }
}