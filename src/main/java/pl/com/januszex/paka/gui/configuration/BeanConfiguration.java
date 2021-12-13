package pl.com.januszex.paka.gui.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import pl.com.januszex.paka.gui.configuration.rest.RestTemplateAuthInterceptor;
import pl.com.januszex.paka.gui.configuration.rest.RestTemplateExceptionHandling;
import pl.com.januszex.paka.gui.user.api.service.CurrentUserServicePort;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class BeanConfiguration {

    private final RestTemplateBuilder restTemplateBuilder;
    private final CurrentUserServicePort currentUserService;

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .errorHandler(new RestTemplateExceptionHandling())
                .interceptors(new RestTemplateAuthInterceptor(currentUserService))
                .build();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


}
