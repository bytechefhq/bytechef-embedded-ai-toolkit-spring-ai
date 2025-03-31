package org.sample.ai.toolkit.sample.rest;

import com.bytechef.ai.toolkit.Environment;
import com.bytechef.ai.toolkit.tool.ToolCallbackProviderFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
class ChatController {

    private final ChatClient chatClient;
    private final ToolCallbackProviderFactory toolCallbackProviderFactory;

    ChatController(ChatClient.Builder chatClientBuilder, ToolCallbackProviderFactory toolCallbackProviderFactory) {
        this.chatClient = chatClientBuilder.build();
        this.toolCallbackProviderFactory = toolCallbackProviderFactory;
    }

    @GetMapping("/ai/chat")
    Map<String, Object> chat(@RequestParam String question, @RequestParam String externalUserId) {
        Object response = chatClient.prompt()
            .toolCallbacks(toolCallbackProviderFactory.create(externalUserId, Environment.DEVELOPMENT))
            .user(question)
            .call()
            .content();

        return Map.of("question", question, "answer", response);
    }
}
