package com.irl.openapi.generator.plugin;

import java.util.HashMap;
import java.util.Map;

import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpenapiCodegenPlugin implements Plugin<Project> {

    private static Logger LOGGER = LoggerFactory.getLogger(OpenapiCodegenPlugin.class);

    @Override
    public void apply(Project project) {
        NamedDomainObjectContainer<OpenapiContainer> openapiConfigContainer = project.container(OpenapiContainer.class);
        project.getExtensions().add("openapiSources", openapiConfigContainer);

        Map<String, Object> taskOptions = new HashMap<String, Object>();
        taskOptions.put("description", "Generate all openapi sources.");
        Task taskGroup = project.task(taskOptions, "openapiGenerateSources");

        openapiConfigContainer.all((delegate) -> {
            String name = delegate.getName();
            CodegenTask task = getSourceTask(project, taskGroup, delegate, name);

            delegate.codeTask = task;
        });
    }

    private CodegenTask getSourceTask(Project project, Task taskGroup, OpenapiContainer delegate, String name) {
        String configName = name.substring(0, 1).toUpperCase() + name.substring(1);

        String taskName = "openapiGenerate" + configName + "Sources";
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("type", CodegenTask.class);
        options.put("description", "Generates a source code from " + delegate.getName() + " the OpenAPI specification");

        CodegenTask task = (CodegenTask) project.task(options, taskName);
        task.setExtension(new CodegenConfigurationExtension());

        taskGroup.dependsOn(task);

        LOGGER.trace("create openapi generator task: " + taskName);

        return task;
    }
}