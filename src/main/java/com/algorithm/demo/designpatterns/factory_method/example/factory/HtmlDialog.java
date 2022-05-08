package com.algorithm.demo.designpatterns.factory_method.example.factory;

import com.algorithm.demo.designpatterns.factory_method.example.buttons.Button;
import com.algorithm.demo.designpatterns.factory_method.example.buttons.HtmlButton;

/**
 * EN: HTML Dialog 将产生 HTML 按钮。
 */
public class HtmlDialog extends Dialog {

    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
