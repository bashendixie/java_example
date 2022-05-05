package com.algorithm.demo.designpatterns.abstract_factory.example.app;

import com.algorithm.demo.designpatterns.abstract_factory.example.buttons.Button;
import com.algorithm.demo.designpatterns.abstract_factory.example.checkboxes.Checkbox;
import com.algorithm.demo.designpatterns.abstract_factory.example.factories.GUIFactory;

/**
 * EN: 工厂用户并不关心他们使用哪个具体工厂，因为他们通过抽象接口与工厂和产品打交道。
 */
public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
