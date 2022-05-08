package com.algorithm.demo.designpatterns.factory_method.example.buttons;

/**
 * TML 按钮实现。
 *
 */
public class HtmlButton implements Button {

    public void render() {
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    public void onClick() {
        System.out.println("Click! Button says - 'Hello World!'");
    }
}
