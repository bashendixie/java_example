package com.algorithm.demo.designpatterns.abstract_factory.example;

import com.algorithm.demo.designpatterns.abstract_factory.example.app.Application;
import com.algorithm.demo.designpatterns.abstract_factory.example.factories.GUIFactory;
import com.algorithm.demo.designpatterns.abstract_factory.example.factories.MacOSFactory;
import com.algorithm.demo.designpatterns.abstract_factory.example.factories.WindowsFactory;

/**
 * Demo class
 */
public class Demo {

    /**
     * 应用程序根据配置或环境变量选择工厂类型并在运行时（通常在初始化阶段）创建它。
     */
    private static Application configureApplication() {
        Application app;
        GUIFactory factory;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
            app = new Application(factory);
        } else {
            factory = new WindowsFactory();
            app = new Application(factory);
        }
        return app;
    }

    public static void main(String[] args) {
        Application app = configureApplication();
        app.paint();
    }
}
