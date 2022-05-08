package com.algorithm.demo.designpatterns.factory_method.example.factory;

import com.algorithm.demo.designpatterns.factory_method.example.buttons.Button;
import com.algorithm.demo.designpatterns.factory_method.example.buttons.WindowsButton;

/**
 * Windows 对话框将产生 Windows 按钮。
 */
public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
