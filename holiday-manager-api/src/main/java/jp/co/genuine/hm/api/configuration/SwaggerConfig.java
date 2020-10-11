package jp.co.genuine.hm.api.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("holiday-manager-api")
				.select()
				.paths(paths())
				.build()
				.apiInfo(apiInfo());
	}

	private Predicate<String> paths() {
		return Predicates.or(Predicates.containsPattern("/"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"HolidayManagerAPI", 
				"有給休暇管理アプリ用API", 
				"v1", 
				"", 
				new Contact("", "", ""), 
				"", 
				"", 
				new ArrayList<VendorExtension>()
		);
	}
}
