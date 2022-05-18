package com.home.skydance.service;

import com.home.skydance.utils.MyClassLoader;
import org.springframework.stereotype.Service;

import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CompileAndRunService {

    private static String outDir = "D:\\skydance";

    public void compile(String name, String content)
    {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        StrSrcJavaObject srcObject = new StrSrcJavaObject(name, content);
        List<StrSrcJavaObject> fileObjects = Collections.singletonList(srcObject);
        String flag = "-d";
        System.out.println("输出目录为" + outDir);
        Iterable<String> options = Arrays.asList(flag, outDir);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, fileObjects);
        boolean result = task.call();
        if (result) System.out.println("Compile it successfully.");
    }

    public String compileAndRun()
    {
        //原来作者的意思是
        //String content = "package play; public class Test{ public static void main(String[] args){System.out.println(\"compile test.\");} }";
        compile("Solution", "public class Solution {\n" +
                "\tpublic static void main(String[] args) {\n" +
                "\t\tSystem.out.println(\"123\");\n" +
                "\t\tString s = \"ddddd\";\n" +
                "\t\tSystem.out.println(s.length());\n" +
                "\t}\n" +
                "}");


        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            Class c = myClassLoader.loadClass("Solution");
            Method method = c.getMethod("main", String[].class);
            Object obj = method.invoke(null, new Object[]{new String[]{}});
            //req.setAttribute("resp","成功运行真棒");
        } catch (Exception e) {
            return e.toString();
            //e.printStackTrace();
        }
        return null;
    }

    private static class StrSrcJavaObject extends SimpleJavaFileObject {
        private final String content;
        public StrSrcJavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }
}
