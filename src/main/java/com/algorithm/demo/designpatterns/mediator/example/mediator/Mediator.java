package com.algorithm.demo.designpatterns.mediator.example.mediator;

import com.algorithm.demo.designpatterns.mediator.example.components.Component;

import javax.swing.*;

/**
 * EN: Common mediator interface.
 *
 * RU: Общий интерфейс посредников.
 */
public interface Mediator {
    void addNewNote(Note note);
    void deleteNote();
    void getInfoFromList(Note note);
    void saveChanges();
    void markNote();
    void clear();
    void sendToFilter(ListModel listModel);
    void setElementsList(ListModel list);
    void registerComponent(Component component);
    void hideElements(boolean flag);
    void createGUI();
}
