package com.algorithm.demo.designpatterns.abstract_factory.example.buttons;

/**
 * 抽象工厂假设您有几个产品系列，它们被构造成单独的类层次结构（按钮/复选框）。 同一家族的所有产品都具有通用接口。
 * 这是button的通用接口.
 */
public interface Button {
    void paint();
}
