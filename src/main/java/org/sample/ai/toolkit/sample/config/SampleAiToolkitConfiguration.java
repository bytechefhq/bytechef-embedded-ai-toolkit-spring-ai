package org.sample.ai.toolkit.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bytechef.ai.toolkit.tool.ToolCallbackProviderFactory;

@Configuration
public class SampleAiToolkitConfiguration {

    @Bean
    ToolCallbackProviderFactory integrationToolCallbackProviderFactory(
        @Value("${bytechef.ai.toolkit.api-key}") String apiKey,
        @Value("${bytechef.ai.toolkit.base-url:'https://app.bytechef.io'}") String baseUrl) {

        return new ToolCallbackProviderFactory(apiKey, baseUrl);
    }
}
