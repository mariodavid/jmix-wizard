package de.diedavids.jmix.wizard.loader;

import de.diedavids.jmix.wizard.Wizard;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.TabSheet;
import io.jmix.ui.xml.layout.ComponentLoader;
import io.jmix.ui.xml.layout.loader.LayoutLoader;
import io.jmix.ui.xml.layout.loader.TabComponentLoader;
import io.jmix.ui.xml.layout.loader.TabSheetLoader;
import org.dom4j.Element;

import java.util.List;

public class WizardLoader extends TabSheetLoader {


    @Override
    public void createComponent() {
        resultComponent = factory.create(Wizard.NAME);
        loadId(resultComponent, element);

        LayoutLoader layoutLoader = getLayoutLoader();

        List<Element> tabElements = element.elements("tab");
        for (Element tabElement : tabElements) {
            final String name = tabElement.attributeValue("id");

            boolean lazy = Boolean.parseBoolean(tabElement.attributeValue("lazy"));
            ComponentLoader tabComponentLoader = layoutLoader
                .getLoader(tabElement, TabComponentLoader.class);
            TabSheet.Tab tab;
            if (lazy) {
                tab = resultComponent.addLazyTab(name, tabElement, tabComponentLoader);
            } else {
                tabComponentLoader.createComponent();

                Component tabComponent = tabComponentLoader.getResultComponent();

                tab = resultComponent.addTab(name, tabComponent);

                pendingLoadComponents.add(tabComponentLoader);
            }

            pendingLoadTabs.put(tabElement, tab);
        }
    }
}

