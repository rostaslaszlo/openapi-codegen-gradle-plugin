# Openapi code generator gradle plugin

This plugin base on [swagger-codegen-maven-plugin](https://github.com/swagger-api/swagger-codegen/tree/master/modules/swagger-codegen-maven-plugin). _**Business logic copied from here!**_

### Example
#### Plugin:
```
id "com.irl.openapi.generator" version "3.0.0-rc1-v1.0"
```

#### Config:
```
openapiSources {
	openapiGenerateTask {
		schema = file('openapi-schema/schema.yaml')
		code {
		    output = file('build/generatedSwaggerSource')
		    language = 'jaxrs-cxf-client'
		    skip = false
		    modelPackage = 'com.irl.openapi.model'
		    generateModelTests = false
		}
	}
}
```
### General Configuration parameters

- `inputSpec` - OpenAPI Spec file path
- `language` - target generation language
- `output` - target output path (default is `${project.build.directory}/generated-sources/swagger`)
- `templateDirectory` - directory with mustache templates
- `addCompileSourceRoot` - add the output directory to the project as a source root (`true` by default)
- `modelPackage` - the package to use for generated model objects/classes
- `apiPackage` - the package to use for generated api objects/classes
- `invokerPackage` - the package to use for the generated invoker objects
- `modelNamePrefix` and `modelNameSuffix` - Sets the pre- or suffix for model classes and enums
- `withXml` - enable XML annotations inside the generated models and API (only works with Java `language` and libraries that provide support for JSON and XML)
- `configOptions` - a map of language-specific parameters (see below)
- `configHelp` - dumps the configuration help for the specified library (generates no sources)
- `ignoreFileOverride` - specifies the full path to a `.swagger-codegen-ignore` used for pattern based overrides of generated outputs
- `generateApis` - generate the apis (`true` by default)
- `generateApiTests` - generate the api tests (`true` by default. Only available if `generateApis` is `true`)
- `generateApiDocumentation` - generate the api documentation (`true` by default. Only available if `generateApis` is `true`)
- `generateModels` - generate the models (`true` by default)
- `modelsToGenerate` - A comma separated list of models to generate.  All models is the default.
- `generateModelTests` - generate the model tests (`true` by default. Only available if `generateModels` is `true`)
- `generateModelDocumentation` - generate the model documentation (`true` by default. Only available if `generateModels` is `true`)
- `generateSupportingFiles` - generate the supporting files (`true` by default)
- `supportingFilesToGenerate` - A comma separated list of supporting files to generate.  All files is the default.
- `skip` - skip code generation (`false` by default. Can also be set globally through the `codegen.skip` property)

### Todos

License
----
Apache License version 2
