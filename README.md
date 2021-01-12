### Example of using Openapi-generator for Swagger/OAS

### Requirements:

* JDK 8
* Internet connection

### Run tests:

```gradlew clean test```

### View report:

```build/reports/allure-report/index.html```

### About openapi-generator configuration:

* [Config options for Kotlin](https://openapi-generator.tech/docs/generators/kotlin)
* File example [swagger.yaml](https://petstore.swagger.io/v2/swagger.yaml)

1 Spec location ```src/main/resources/specs/swagger.yaml```  
2 Run ```gradlew clean openApiGenerate```  
3 Move ```src/main/kotlin/io/swagger/petstore``` from ```build/generated``` to root directory  
4 Reformat generated code (interfaces, models) and update tests  