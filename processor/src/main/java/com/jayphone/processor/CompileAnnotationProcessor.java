package com.jayphone.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Created by JayPhone on 2020/5/15
 */
//支持的注解类型, 此处要填写全类名
@SupportedAnnotationTypes("com.jayphone.processor.CompileAnnotation")
//支持的JDK版本
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CompileAnnotationProcessor extends AbstractProcessor {
    //生成的类名前缀
    public static final String SUFFIX = "AutoGenerate";
    //生成的类名后缀
    public static final String PREFIX = "My_";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement te : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(te)) {
                // 准备在gradle的控制台打印信息
                Messager messager = processingEnv.getMessager();
                //打印
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.toString());
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.getSimpleName());
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.getEnclosingElement());

                //获取注解
                CompileAnnotation compileAnnotation = e.getAnnotation(CompileAnnotation.class);

                //获取元素名并将其首字母大写
                String name = e.getSimpleName().toString();
                char c = Character.toUpperCase(name.charAt(0));
                name = c + name.substring(1);

                // 包裹注解元素的元素, 也就是其父元素, 比如注解了成员变量或者成员函数, 其上层就是类
                Element enclosingElement = e.getEnclosingElement();
                // 获取父元素的全类名, 用来生成包名
                String enclosingQualifiedName;
                if (enclosingElement instanceof PackageElement) {
                    enclosingQualifiedName = ((PackageElement) enclosingElement).getQualifiedName().toString();
                } else {
                    enclosingQualifiedName = ((TypeElement) enclosingElement).getQualifiedName().toString();
                }

                //创建Java文件
                try {
                    //生成的包名
                    String generatePackageName = enclosingQualifiedName.substring(0, enclosingQualifiedName.lastIndexOf('.'));
                    //生成的类名
                    String generateClassName = PREFIX + enclosingElement.getSimpleName() + SUFFIX;

                    // 创建Java文件
                    JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(generateClassName);
                    // 在控制台输出文件路径
                    messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + fileObject.toUri());
                    Writer writer = fileObject.openWriter();

                    try {
                        PrintWriter printWriter = new PrintWriter(writer);
                        printWriter.println("package " + generatePackageName + ";");
                        printWriter.println("\npublic class " + generateClassName + " { ");
                        printWriter.println("\n    /** 打印值 */");
                        printWriter.println("    public static void print" + name + "() {");
                        printWriter.println("        // 注解的父元素: " + enclosingElement.toString());
                        printWriter.println("        System.out.println(\"代码生成的路径: " + fileObject.toUri() + "\");");
                        printWriter.println("        System.out.println(\"注解的元素: " + e.toString() + "\");");
                        printWriter.println("        System.out.println(\"注解的值: " + compileAnnotation.value() + "\");");
                        printWriter.println("    }");
                        printWriter.println("}");
                        printWriter.flush();
                    } finally {
                        writer.close();
                    }

                } catch (IOException ex) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, ex.toString());
                }
            }
        }
        return true;
    }
}
