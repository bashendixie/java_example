package com.home.skydance.controller;

import com.home.skydance.service.CompileAndRunService;
import com.home.skydance.service.CompileWithCmdService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final CompileAndRunService compileAndRunService;
    private final CompileWithCmdService compileWithCmdService;

    public UserController(CompileAndRunService compileAndRunService, CompileWithCmdService compileWithCmdService) {
        this.compileAndRunService = compileAndRunService;
        this.compileWithCmdService = compileWithCmdService;
    }

    /**
     * 调用第一种编译方式
     */
    @PostMapping("/compileAndRun")
    public String compileAndRun()
    {
        String res = this.compileAndRunService.compileAndRun();

        return res==null ? "success" : res;
    }

    /**
     * 调用第二种编译方式
     */
    @PostMapping("/compileAndRunWithCmd")
    public String compileAndRunWithCmd(String class_content)
    {
        String res = this.compileWithCmdService.compileAndRun(class_content);
        return res==null ? "success" : res;
    }

}
