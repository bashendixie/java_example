package com.algorithm.demo.designpatterns.mediator.example.components;

import com.algorithm.demo.designpatterns.mediator.example.mediator.Mediator;

/**
 * EN: Common component interface.
 *
 * RU: Общий интерфейс компонентов.
 */
public interface Component {
    void setMediator(Mediator mediator);
    String getName();
}
