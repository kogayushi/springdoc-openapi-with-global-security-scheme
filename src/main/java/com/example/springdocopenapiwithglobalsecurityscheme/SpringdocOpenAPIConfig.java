package com.example.springdocopenapiwithglobalsecurityscheme;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocOpenAPIConfig {

    // `/api/**`に対してspringdoc openapiの対象とする設定。セキュリティ定義とは特に関係ない。
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                             .group("api")
                             .pathsToMatch("/api/**")
                             .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Sample API")
                                .description("Sample")
                                .version("1.0.0")
                     )
                // `addSecuritySchemes`と`addSecurityItem`はbearer tokenをswagger-ui上から指定できるようにするための設定である。
                // 参考URL → https://swagger.io/docs/specification/authentication/
                // 重要な部分を以下に抜粋する。
                // After you have defined the security schemes in the securitySchemes section,
                // you can apply them to the whole API or individual operations by adding the security section on the root level or operation level, respectively.
                // When used on the root level, security applies the specified security schemes globally to all API operations,
                // unless overridden on the operation level.
                // 要約すると、以下となる。
                // 1. 要求する可能性のあるセキュリティ定義をまず宣言する
                // 2. OpenAPI仕様書のトップレベルで(1)を利用すると宣言するとすべてのAPIに対してそのセキュリティ定義が適用される
                // 3. ただし、各APIで別のセキュリティ定義の利用を設定すると(2)の設定は上書きできる
                .components(
                        new Components()
                                // security schema objectの設定 https://github.com/OAI/OpenAPI-Specification/blob/main/versions/3.0.3.md#securitySchemeObject
                                // 利用する可能性のあるセキュリティ定義を宣言する。この設定だけではAPIに認証が必要なことの設定にはならない。
                                .addSecuritySchemes(
                                        "basicScheme",
                                        new SecurityScheme()
                                                    .type(SecurityScheme.Type.HTTP)
                                                .scheme("basic")
                                                   )
                           )
                // Security Requirement Objectの設定 https://github.com/OAI/OpenAPI-Specification/blob/main/versions/3.0.3.md#security-requirement-object
                // すべてのAPIにデフォルトで適用するセキュリティ定義を指定する
                // 各APIで別のセキュリティ定義を指定することで上書きできる。あるいはセキュリティ定義なし（認証なし）とすることもできる。
                .addSecurityItem(
                        new SecurityRequirement().addList("basicScheme"))
                ;
    }
}
