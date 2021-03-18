package br.com.responsa.api.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.ArrayList

import springfox.documentation.service.ApiInfo
import springfox.documentation.service.VendorExtension


@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun apis(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false)
    }

    private fun apiInfo(): ApiInfo? {
        return ApiInfo(
            "Responsa API",
            "Um serviço para manusear eventos e alteração dos dados",
            "1.0",
            "Termos de serviço",
            null,
            "MIT License",
            "URL da licença",
            ArrayList()
        )
    }
}
