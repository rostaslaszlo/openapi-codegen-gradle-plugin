package com.irl.openapi.generator.plugin;

import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyAdditionalPropertiesKvp;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyAdditionalPropertiesKvpList;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyImportMappingsKvp;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyImportMappingsKvpList;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyInstantiationTypesKvp;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyInstantiationTypesKvpList;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyLanguageSpecificPrimitivesCsv;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyLanguageSpecificPrimitivesCsvList;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyReservedWordsMappingsKvp;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyReservedWordsMappingsKvpList;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyTypeMappingsKvp;
import static io.swagger.codegen.config.CodegenConfiguratorUtils.applyTypeMappingsKvpList;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.codegen.CodegenConstants;
import io.swagger.codegen.config.CodegenConfigurator;

public class CodegenConfigurationExtension {

    private boolean verbose;

    /**
     * Client language to generate. required
     */
    private String language;

    /**
     * Location of the output directory.
     */
    private File output;

    /**
     * Location of the swagger spec, as URL or file. required
     */
    private File inputSpec;

    /**
     * Git user ID, e.g. swagger-api.
     */
    private String gitUserId;

    /**
     * Git repo ID, e.g. swagger-codegen.
     */
    private String gitRepoId;

    /**
     * Folder containing the template files.
     */
    private File templateDirectory;

    /**
     * Adds authorization headers when fetching the swagger definitions remotely. " Pass in a
     * URL-encoded string of name:header with a comma separating multiple values
     */
    private String auth;

    /**
     * Path to separate json configuration file.
     */
    private String configurationFile;

    /**
     * Specifies if the existing files should be overwritten during the generation.
     */
    private Boolean skipOverwrite;

    /**
     * Specifies if the existing files should be overwritten during the generation.
     */
    private Boolean removeOperationIdPrefix;

    /**
     * The package to use for generated api objects/classes
     */
    private String apiPackage;

    /**
     * The package to use for generated model objects/classes
     */
    private String modelPackage;

    /**
     * The package to use for the generated invoker objects
     */
    private String invokerPackage;

    /**
     * groupId in generated pom.xml
     */
    private String groupId;

    /**
     * artifactId in generated pom.xml
     */
    private String artifactId;

    /**
     * artifact version in generated pom.xml
     */
    private String artifactVersion;

    /**
     * Sets the library
     */
    private String library;

    /**
     * Sets the prefix for model enums and classes
     */
    private String modelNamePrefix;

    /**
     * Sets the suffix for model enums and classes
     */
    private String modelNameSuffix;

    /**
     * Sets an optional ignoreFileOverride path
     */
    private String ignoreFileOverride;

    /**
     * A map of language-specific parameters as passed with the -c option to the command line
     */
    private Map<?, ?> configOptions;

    /**
     * A map of types and the types they should be instantiated as
     */
    private List<String> instantiationTypes;

    /**
     * A map of classes and the import that should be used for that class
     */
    private List<String> importMappings;

    /**
     * A map of swagger spec types and the generated code types to use for them
     */
    private List<String> typeMappings;

    /**
     * A map of additional language specific primitive types
     */
    private List<String> languageSpecificPrimitives;

    /**
     * A map of additional properties that can be referenced by the mustache templates
     */
    private List<String> additionalProperties;

    /**
     * A map of reserved names and how they should be escaped
     */
    private List<String> reservedWordsMappings;

    /**
     * Generate the apis
     */
    private Boolean generateApis = true;

    /**
     * Generate the models
     */
    private Boolean generateModels = true;

    /**
     * A comma separated list of models to generate. All models is the default.
     */
    private String modelsToGenerate = "";

    /**
     * Generate the supporting files
     */
    private Boolean generateSupportingFiles = true;

    /**
     * A comma separated list of models to generate. All models is the default.
     */
    private String supportingFilesToGenerate = "";

    /**
     * Generate the model tests
     */
    private Boolean generateModelTests = true;

    /**
     * Generate the model documentation
     */
    private Boolean generateModelDocumentation = true;

    /**
     * Generate the api tests
     */
    private Boolean generateApiTests = true;

    /**
     * Generate the api documentation
     */
    private Boolean generateApiDocumentation = true;

    /**
     * Generate the api documentation
     */
    private Boolean withXml = false;

    /**
     * Skip the execution.
     */
    private Boolean skip = false;

    /**
     * Add the output directory to the project as a source root, so that the generated java types
     * are compiled and included in the project artifact.
     */
    private boolean addCompileSourceRoot = true;

    protected Map<String, String> environmentVariables = new HashMap<String, String>();

    protected Map<String, String> originalEnvironmentVariables = new HashMap<String, String>();

    private boolean configHelp = false;

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public File getOutput() {
        return output;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public File getInputSpec() {
        return inputSpec;
    }

    public void setInputSpec(File inputSpec) {
        this.inputSpec = inputSpec;
    }

    public String getGitUserId() {
        return gitUserId;
    }

    public void setGitUserId(String gitUserId) {
        this.gitUserId = gitUserId;
    }

    public String getGitRepoId() {
        return gitRepoId;
    }

    public void setGitRepoId(String gitRepoId) {
        this.gitRepoId = gitRepoId;
    }

    public File getTemplateDirectory() {
        return templateDirectory;
    }

    public void setTemplateDirectory(File templateDirectory) {
        this.templateDirectory = templateDirectory;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getConfigurationFile() {
        return configurationFile;
    }

    public void setConfigurationFile(String configurationFile) {
        this.configurationFile = configurationFile;
    }

    public Boolean getSkipOverwrite() {
        return skipOverwrite;
    }

    public void setSkipOverwrite(Boolean skipOverwrite) {
        this.skipOverwrite = skipOverwrite;
    }

    public Boolean getRemoveOperationIdPrefix() {
        return removeOperationIdPrefix;
    }

    public void setRemoveOperationIdPrefix(Boolean removeOperationIdPrefix) {
        this.removeOperationIdPrefix = removeOperationIdPrefix;
    }

    public String getApiPackage() {
        return apiPackage;
    }

    public void setApiPackage(String apiPackage) {
        this.apiPackage = apiPackage;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getInvokerPackage() {
        return invokerPackage;
    }

    public void setInvokerPackage(String invokerPackage) {
        this.invokerPackage = invokerPackage;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getArtifactVersion() {
        return artifactVersion;
    }

    public void setArtifactVersion(String artifactVersion) {
        this.artifactVersion = artifactVersion;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getModelNamePrefix() {
        return modelNamePrefix;
    }

    public void setModelNamePrefix(String modelNamePrefix) {
        this.modelNamePrefix = modelNamePrefix;
    }

    public String getModelNameSuffix() {
        return modelNameSuffix;
    }

    public void setModelNameSuffix(String modelNameSuffix) {
        this.modelNameSuffix = modelNameSuffix;
    }

    public String getIgnoreFileOverride() {
        return ignoreFileOverride;
    }

    public void setIgnoreFileOverride(String ignoreFileOverride) {
        this.ignoreFileOverride = ignoreFileOverride;
    }

    public Map<?, ?> getConfigOptions() {
        return configOptions;
    }

    public void setConfigOptions(Map<?, ?> configOptions) {
        this.configOptions = configOptions;
    }

    public List<String> getInstantiationTypes() {
        return instantiationTypes;
    }

    public void setInstantiationTypes(List<String> instantiationTypes) {
        this.instantiationTypes = instantiationTypes;
    }

    public List<String> getImportMappings() {
        return importMappings;
    }

    public void setImportMappings(List<String> importMappings) {
        this.importMappings = importMappings;
    }

    public List<String> getTypeMappings() {
        return typeMappings;
    }

    public void setTypeMappings(List<String> typeMappings) {
        this.typeMappings = typeMappings;
    }

    public List<String> getLanguageSpecificPrimitives() {
        return languageSpecificPrimitives;
    }

    public void setLanguageSpecificPrimitives(List<String> languageSpecificPrimitives) {
        this.languageSpecificPrimitives = languageSpecificPrimitives;
    }

    public List<String> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(List<String> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public List<String> getReservedWordsMappings() {
        return reservedWordsMappings;
    }

    public void setReservedWordsMappings(List<String> reservedWordsMappings) {
        this.reservedWordsMappings = reservedWordsMappings;
    }

    public Boolean getGenerateApis() {
        return generateApis;
    }

    public void setGenerateApis(Boolean generateApis) {
        this.generateApis = generateApis;
    }

    public Boolean getGenerateModels() {
        return generateModels;
    }

    public void setGenerateModels(Boolean generateModels) {
        this.generateModels = generateModels;
    }

    public String getModelsToGenerate() {
        return modelsToGenerate;
    }

    public void setModelsToGenerate(String modelsToGenerate) {
        this.modelsToGenerate = modelsToGenerate;
    }

    public Boolean getGenerateSupportingFiles() {
        return generateSupportingFiles;
    }

    public void setGenerateSupportingFiles(Boolean generateSupportingFiles) {
        this.generateSupportingFiles = generateSupportingFiles;
    }

    public String getSupportingFilesToGenerate() {
        return supportingFilesToGenerate;
    }

    public void setSupportingFilesToGenerate(String supportingFilesToGenerate) {
        this.supportingFilesToGenerate = supportingFilesToGenerate;
    }

    public Boolean getGenerateModelTests() {
        return generateModelTests;
    }

    public void setGenerateModelTests(Boolean generateModelTests) {
        this.generateModelTests = generateModelTests;
    }

    public Boolean getGenerateModelDocumentation() {
        return generateModelDocumentation;
    }

    public void setGenerateModelDocumentation(Boolean generateModelDocumentation) {
        this.generateModelDocumentation = generateModelDocumentation;
    }

    public Boolean getGenerateApiTests() {
        return generateApiTests;
    }

    public void setGenerateApiTests(Boolean generateApiTests) {
        this.generateApiTests = generateApiTests;
    }

    public Boolean getGenerateApiDocumentation() {
        return generateApiDocumentation;
    }

    public void setGenerateApiDocumentation(Boolean generateApiDocumentation) {
        this.generateApiDocumentation = generateApiDocumentation;
    }

    public Boolean getWithXml() {
        return withXml;
    }

    public void setWithXml(Boolean withXml) {
        this.withXml = withXml;
    }

    public Boolean getSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }

    public boolean isAddCompileSourceRoot() {
        return addCompileSourceRoot;
    }

    public void setAddCompileSourceRoot(boolean addCompileSourceRoot) {
        this.addCompileSourceRoot = addCompileSourceRoot;
    }

    public Map<String, String> getEnvironmentVariables() {
        return environmentVariables;
    }

    public void setEnvironmentVariables(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    public Map<String, String> getOriginalEnvironmentVariables() {
        return originalEnvironmentVariables;
    }

    public void setOriginalEnvironmentVariables(Map<String, String> originalEnvironmentVariables) {
        this.originalEnvironmentVariables = originalEnvironmentVariables;
    }

    public boolean isConfigHelp() {
        return configHelp;
    }

    public void setConfigHelp(boolean configHelp) {
        this.configHelp = configHelp;
    }

    public CodegenConfigurator getCodegenConfigurator() {
        // attempt to read from config file
        CodegenConfigurator configurator = CodegenConfigurator.fromFile(configurationFile);

        // if a config file wasn't specified or we were unable to read it
        if (configurator == null) {
            configurator = new CodegenConfigurator();
        }

        configurator.setVerbose(verbose);

        if (skipOverwrite != null) {
            configurator.setSkipOverwrite(skipOverwrite);
        }

        if (removeOperationIdPrefix != null) {
            configurator.setRemoveOperationIdPrefix(removeOperationIdPrefix);
        }

        if (inputSpec != null) {
            configurator.setInputSpec(inputSpec.getAbsolutePath());
        }

        if (isNotEmpty(gitUserId)) {
            configurator.setGitUserId(gitUserId);
        }

        if (isNotEmpty(gitRepoId)) {
            configurator.setGitRepoId(gitRepoId);
        }

        if (isNotEmpty(ignoreFileOverride)) {
            configurator.setIgnoreFileOverride(ignoreFileOverride);
        }

        configurator.setLang(language);

        configurator.setOutputDir(output.getAbsolutePath());

        if (isNotEmpty(auth)) {
            configurator.setAuth(auth);
        }

        if (isNotEmpty(apiPackage)) {
            configurator.setApiPackage(apiPackage);
        }

        if (isNotEmpty(modelPackage)) {
            configurator.setModelPackage(modelPackage);
        }

        if (isNotEmpty(invokerPackage)) {
            configurator.setInvokerPackage(invokerPackage);
        }

        if (isNotEmpty(groupId)) {
            configurator.setGroupId(groupId);
        }

        if (isNotEmpty(artifactId)) {
            configurator.setArtifactId(artifactId);
        }

        if (isNotEmpty(artifactVersion)) {
            configurator.setArtifactVersion(artifactVersion);
        }

        if (isNotEmpty(library)) {
            configurator.setLibrary(library);
        }

        if (isNotEmpty(modelNamePrefix)) {
            configurator.setModelNamePrefix(modelNamePrefix);
        }

        if (isNotEmpty(modelNameSuffix)) {
            configurator.setModelNameSuffix(modelNameSuffix);
        }

        if (null != templateDirectory) {
            configurator.setTemplateDir(templateDirectory.getAbsolutePath());
        }

        // Set generation options
        if (null != generateApis && generateApis) {
            System.setProperty(CodegenConstants.APIS, "");
        } else {
            System.clearProperty(CodegenConstants.APIS);
        }

        if (null != generateModels && generateModels) {
            System.setProperty(CodegenConstants.MODELS, modelsToGenerate);
        } else {
            System.clearProperty(CodegenConstants.MODELS);
        }

        if (null != generateSupportingFiles && generateSupportingFiles) {
            System.setProperty(CodegenConstants.SUPPORTING_FILES, supportingFilesToGenerate);
        } else {
            System.clearProperty(CodegenConstants.SUPPORTING_FILES);
        }

        System.setProperty(CodegenConstants.MODEL_TESTS, generateModelTests.toString());
        System.setProperty(CodegenConstants.MODEL_DOCS, generateModelDocumentation.toString());
        System.setProperty(CodegenConstants.API_TESTS, generateApiTests.toString());
        System.setProperty(CodegenConstants.API_DOCS, generateApiDocumentation.toString());
        System.setProperty(CodegenConstants.WITH_XML, withXml.toString());

        if (configOptions != null) {
            // Retained for backwards-compataibility with configOptions -> instantiation-types
            if (instantiationTypes == null && configOptions.containsKey("instantiation-types")) {
                applyInstantiationTypesKvp(configOptions.get("instantiation-types").toString(),
                        configurator);
            }

            // Retained for backwards-compataibility with configOptions -> import-mappings
            if (importMappings == null && configOptions.containsKey("import-mappings")) {
                applyImportMappingsKvp(configOptions.get("import-mappings").toString(),
                        configurator);
            }

            // Retained for backwards-compataibility with configOptions -> type-mappings
            if (typeMappings == null && configOptions.containsKey("type-mappings")) {
                applyTypeMappingsKvp(configOptions.get("type-mappings").toString(), configurator);
            }

            // Retained for backwards-compataibility with configOptions -> language-specific-primitives
            if (languageSpecificPrimitives == null && configOptions.containsKey("language-specific-primitives")) {
                applyLanguageSpecificPrimitivesCsv(configOptions
                        .get("language-specific-primitives").toString(), configurator);
            }

            // Retained for backwards-compataibility with configOptions -> additional-properties
            if (additionalProperties == null && configOptions.containsKey("additional-properties")) {
                applyAdditionalPropertiesKvp(configOptions.get("additional-properties").toString(),
                        configurator);
            }

            // Retained for backwards-compataibility with configOptions -> reserved-words-mappings
            if (reservedWordsMappings == null && configOptions.containsKey("reserved-words-mappings")) {
                applyReservedWordsMappingsKvp(configOptions.get("reserved-words-mappings")
                        .toString(), configurator);
            }
        }

        //Apply Instantiation Types
        if (instantiationTypes != null && !configOptions.containsKey("instantiation-types")) {
            applyInstantiationTypesKvpList(instantiationTypes, configurator);
        }

        //Apply Import Mappings
        if (importMappings != null && !configOptions.containsKey("import-mappings")) {
            applyImportMappingsKvpList(importMappings, configurator);
        }

        //Apply Type Mappings
        if (typeMappings != null && !configOptions.containsKey("type-mappings")) {
            applyTypeMappingsKvpList(typeMappings, configurator);
        }

        //Apply Language Specific Primitives
        if (languageSpecificPrimitives != null && !configOptions.containsKey("language-specific-primitives")) {
            applyLanguageSpecificPrimitivesCsvList(languageSpecificPrimitives, configurator);
        }

        //Apply Additional Properties
        if (additionalProperties != null && !configOptions.containsKey("additional-properties")) {
            applyAdditionalPropertiesKvpList(additionalProperties, configurator);
        }

        //Apply Reserved Words Mappings
        if (reservedWordsMappings != null && !configOptions.containsKey("reserved-words-mappings")) {
            applyReservedWordsMappingsKvpList(reservedWordsMappings, configurator);
        }

        if (environmentVariables != null) {

            for (String key : environmentVariables.keySet()) {
                originalEnvironmentVariables.put(key, System.getProperty(key));
                String value = environmentVariables.get(key);
                if (value == null) {
                    // don't put null values
                    value = "";
                }
                System.setProperty(key, value);
                configurator.addSystemProperty(key, value);
            }
        }

		return configurator;
    }
}