package com.home.skydance.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * 执行javac编译命令，和java运行指令。
 */
public class CommandUtil {
    /**
     * run 方法，要执行一个命令就要创建一个进程来运行一个命令。
     * 要进行编译和运行，来确定这个类中的参数，包含3个参数。
     * @param cmd 表示要执行的命令, 比如 javac
     * @param stdoutFile stdoutFile 指定标准输出写到哪个文件中
     * @param stderrFile stderrFile 执行标准错误写到哪个文件中
     * @return 进程的返回值用0来表示进程结束，-1表示的是进程没有结束，或者进程异常。
     * @throws IOException
     * @throws InterruptedException
     */
    public static int run(String cmd, String stdoutFile,
                          String stderrFile) throws IOException, InterruptedException {
        // 进行多进程操作。
        // 用 Runtime 这样的类表示进程的创建。Runtime这个类在创建的时候，不用手动去创建。
        // 里边的实例只有一个，这就是设计模式中的“单例模式”，直接调用里边的.getRunTime().exec()方法即可。
        // 下边表示创建一个进程，用process表示。
        Process process = Runtime.getRuntime().exec(cmd);
        // 当新的进程跑起来之后, 就需要获取到新进程的输出结果，结果无非有以下几种。
        // 1.要是标准输出中有内容，也就是下边的文件的内容不是空的，给标准输出进行重定向。
        if (stdoutFile != null) {
            // getInputStream 得到的是标准输出，得到里边的内容，为了后边进行重定向。
            InputStream stdoutFrom = process.getInputStream();
            // 可以理解为先弄一个存放标准输出的箱子，将上边的标准输出的内容重定向在这个箱子里。
            FileOutputStream stdoutTo = new FileOutputStream(stdoutFile);
            // 接下来就从新进程这边依次读取每个字节, 写入到 stdoutTo 这个文件里，循环读取。
            while (true) {
                // 从刚的输出中去读
                int ch = stdoutFrom.read();
                if (ch == -1) {
                    break;
                }
                // 给刚那个箱子里边写
                stdoutTo.write(ch);
            }
            // 文件读写完毕, 记得要关闭，否则会导致内存的堆积，进而导致进程挂掉。
            stdoutFrom.close();
            stdoutTo.close();
        }

        // 同上，再针对标准错误进行重定向
        // 要是标准错误文件中有内容，就将输出的内容进行重定向。
        if (stderrFile != null) {
            // getErrorStream 得到的是标准错误
            InputStream stderrFrom = process.getErrorStream();
            // 表示的弄了一个箱子，用来存放标准错误中的内容。
            FileOutputStream stderrTo = new FileOutputStream(stderrFile);
            while (true) {
                int ch = stderrFrom.read();
                if (ch == -1) {
                    break;
                }
                stderrTo.write(ch);
            }
            // 操作完文件，进行文件的关闭。
            stderrFrom.close();
            stderrTo.close();
        }
        // 等待新的进程的结束，在结束退出码。就是等待刚创建的run进程的结束才获取退出码的
        int exitCode = process.waitFor();// 等待一下，等新的进程运行结束
        return exitCode;
    }

    /**
     * 读取文件内容
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(filePath);) {
            while (true) {
                int ch = fileInputStream.read();
                if (ch == -1) {
                    break;
                }
                // 每次 read 方法只是读到 一个字节 .
                // read 设计成返回 int 的原因只是为了能多表示一下 -1 这个情况
                // 实际把读取的结果往 stringBuilder 里插入的时候, 还得再转换回 byte 类型
                // 预期是一次往 StringBuilder 写一个字符给 StringBuilder, 而不是一次写 四个字节
                stringBuilder.append((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    // 把 content 中的内容一次写入到 filePath 对应的文件中
    public static void writeFile(String filePath, String content) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            // 进行写文件操作
            fileOutputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
