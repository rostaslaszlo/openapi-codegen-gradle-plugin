package com.irl.openapi.generator.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.codegen.config.CodegenConfigurator;

public class CodegenTask extends DefaultTask {

    private CodegenConfigurationExtension extension;

    public CodegenConfigurationExtension getExtension() {
        return extension;
    }

    public void setExtension(CodegenConfigurationExtension extension) {
        this.extension = extension;
    }

    @TaskAction
    public void generate() {
        if (extension == null) {
            extension = this.getExtensions().findByType(CodegenConfigurationExtension.class);
        }

        System.out.println("futas: " + extension.getLanguage());

        execute(extension);
    }

    private void execute(CodegenConfigurationExtension extension) {
		CodegenConfigurator configurator = extension.getCodegenConfigurator();

		if (extension.getSkip()) {
            // getLog().info("Code generation is skipped.");
            // Even when no new sources are generated, the existing ones should
            // still be compiled if needed.
            // addCompileSourceRootIfConfigured();
            return;
        }


        final ClientOptInput input = configurator.toClientOptInput();
        //final CodegenConfig config = input.getConfig();

        /*if (configOptions != null) {
            for (CliOption langCliOption : config.cliOptions()) {
                if (configOptions.containsKey(langCliOption.getOpt())) {
                    input.getConfig().additionalProperties()
                            .put(langCliOption.getOpt(), configOptions.get(langCliOption.getOpt()));
                }
            }
        }*/

        try {
            new DefaultGenerator().opts(input).generate();
        } catch (Exception e) {
            // Maven logs exceptions thrown by plugins only if invoked with -e
            // I find it annoying to jump through hoops to get basic diagnostic information,
            // so let's log it in any case:
            //getLog().error(e);
            //throw new MojoExecutionException(
            //        "Code generation failed. See above for the full exception.");
        }

    }
}