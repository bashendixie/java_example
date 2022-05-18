package com.home.skydance.service;


import com.home.skydance.utils.CommandUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class CompileWithCmdService
{
    private String CLASS = "Solution";
    // 先生成唯一的id, 根据这个 id 来拼装出目录的名字
    private String WORK_DIR = "D:/skydance/" + UUID.randomUUID().toString() + "/";
    // 然后再生成后续的这些文件名
    private String CODE = WORK_DIR + CLASS + ".java";
    private String STDOUT = WORK_DIR + "stdout.txt";
    private String STDERR = WORK_DIR + "stderr.txt";
    private String COMPILE_ERROR = WORK_DIR + "compile_error.txt";

    public String compileAndRun(String class_content)  {
        try {
            File file = new File(WORK_DIR);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 将代码写入文件
            CommandUtil.writeFile(CODE, class_content);

            // String compileCmd = "javac -encoding utf-8 " + CODE + " -d " + WORK_DIR;
            String compileCmd = String.format("javac -encoding utf-8 %s -d %s", CODE, WORK_DIR);
            System.out.println("编译命令: " + compileCmd);
            // 创建子进程进行编译, 此处不关心 javac 的标准输出(没输出啥内容)
            // 只关心 javac 的标准错误. 标准错误中就包含了编译出错的信息
            CommandUtil.run(compileCmd, null, COMPILE_ERROR);
            // 此处判定一下编译是否出错. 看一下 COMPILE_ERROR 这个文件是不是空着就知道了
            String compileError = CommandUtil.readFile(COMPILE_ERROR);

            if (!compileError.equals("")) {
                return compileError;
            }

            // java运行类
            String runCmd = String.format("java -classpath %s %s", WORK_DIR, CLASS);
            System.out.println("runCmd: " + runCmd);
            CommandUtil.run(runCmd, STDOUT, STDERR);

            // 尝试读取 STDERR 这个文件里的内容, 如果不为空, 就认为是运行出错
            // 如果程序抛出异常, 异常的调用栈信息就是通过 stderr 来输出的
            String runError = CommandUtil.readFile(STDERR);
            if (runError!=null && !runError.equals("")) {
                return runError;
            }
            return CommandUtil.readFile(STDOUT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
