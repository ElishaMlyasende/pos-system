package Point.Of.Sale.POS;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Info info = new Info()
                .title("POINT OF SALE API")
                .version("1.0")
                .description("SYSTEM FOR MANAGING SALES");

        return new OpenAPI().info(info);
    }
}
