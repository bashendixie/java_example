package com.algorithm.demo.designpatterns.factory_method.example;

import com.algorithm.demo.designpatterns.factory_method.example.factory.Dialog;
import com.algorithm.demo.designpatterns.factory_method.example.factory.HtmlDialog;
import com.algorithm.demo.designpatterns.factory_method.example.factory.WindowsDialog;

/**
 * Demo class.
 */
public class Demo {
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    /**
     * 具体工厂通常根据配置或环境选项进行选择.
     */
    static void configure() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    /**
     * 所有客户端代码都应该通过抽象接口与工厂和产品一起工作。 这样，它就不会关心它与哪个工厂合作以及它返回什么样的产品。
     */
    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}
