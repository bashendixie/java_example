package com.algorithm.demo.designpatterns.abstract_factory.example.buttons;

/**
 * 所有产品系列都有相同的品种（MacOS/Windows）。
 *
 * 这是按钮的另一种变体.
 */
public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created WindowsButton.");
    }
}
