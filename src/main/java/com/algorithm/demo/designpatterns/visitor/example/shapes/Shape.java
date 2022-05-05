package com.algorithm.demo.designpatterns.visitor.example.shapes;

import com.algorithm.demo.designpatterns.visitor.example.visitor.Visitor;

public interface Shape {
    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);
}
