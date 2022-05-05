package com.algorithm.demo.designpatterns.mediator.example;

import com.algorithm.demo.designpatterns.mediator.example.components.*;
import com.algorithm.demo.designpatterns.mediator.example.mediator.Editor;
import com.algorithm.demo.designpatterns.mediator.example.mediator.Mediator;

import javax.swing.*;

/**
 * EN: Demo class. Everything comes together here.
 *
 * RU: Демо-класс. Здесь всё сводится воедино.
 */
public class Demo {
    public static void main(String[] args) {
        Mediator mediator = new Editor();

        mediator.registerComponent(new Title());
        mediator.registerComponent(new TextBox());
        mediator.registerComponent(new AddButton());
        mediator.registerComponent(new DeleteButton());
        mediator.registerComponent(new SaveButton());
        mediator.registerComponent(new List(new DefaultListModel()));
        mediator.registerComponent(new Filter());

        mediator.createGUI();
    }
}
