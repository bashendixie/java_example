package com.algorithm.demo.designpatterns.visitor.example.visitor;

import com.algorithm.demo.designpatterns.visitor.example.shapes.Circle;
import com.algorithm.demo.designpatterns.visitor.example.shapes.CompoundShape;
import com.algorithm.demo.designpatterns.visitor.example.shapes.Dot;
import com.algorithm.demo.designpatterns.visitor.example.shapes.Rectangle;

public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}
