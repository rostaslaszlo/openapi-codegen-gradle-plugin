package com.irl.openapi.generator.plugin;

import java.io.File;

import groovy.lang.Closure;
import groovy.lang.DelegatesTo;

public class OpenapiContainer {

    final String name;

    public OpenapiContainer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public CodegenTask codeTask;

    public CodegenTask code(@DelegatesTo(CodegenTask.class) Closure<?> closure) {
        closure.setDelegate(codeTask.getExtension());
        closure.call();

        return codeTask;
    }

    void setSchema(File inputFile) {
        codeTask.getExtension().setInputSpec(inputFile);
    }
}
