package com.algorithm.demo.designpatterns.factory_method.example;

import com.algorithm.demo.designpatterns.factory_method.example.factory.Dialog;
import com.algorithm.demo.designpatterns.factory_method.example.factory.HtmlDialog;
import com.algorithm.demo.designpatterns.factory_method.example.factory.WindowsDialog;

/**
 * EN: Demo class. Everything comes together here.
 *
 * RU: Демо-класс. Здесь всё сводится воедино.
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
     * EN: All of the client code should work with factories and products
     * through abstract interfaces. This way it does not care which factory it
     * works with and what kind of product it returns.
     *
     * RU: Весь остальной клиентский код работает с фабрикой и продуктами только
     * через общий интерфейс, поэтому для него неважно какая фабрика была
     * создана.
     */
    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}
