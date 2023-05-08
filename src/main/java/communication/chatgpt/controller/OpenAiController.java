package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class OpenAiController {

    private final OpenAiResponseEntity openAiResponseEntity;
    private final OpenAiRequestEntity openAiRequestEntity;

    private String chatRequest = "";

    @PostMapping("/chat/completions")
    public ResponseEntity<String> chat(@RequestBody String request) throws JsonProcessingException {
        chatRequest = chatRequest + request;
        HttpEntity<String> openAiRequest = openAiRequestEntity.chatParsed(chatRequest);
        ResponseEntity<String> stringResponseEntity = openAiResponseEntity.chatParsed(openAiRequest);
        chatRequest = chatRequest + stringResponseEntity.getBody();
        return stringResponseEntity;
    }

    @GetMapping("/chat/reset")
    public ResponseEntity<String> chatQuestionReset() {
        chatRequest = "";
        return ResponseEntity.ok("success");
    }

    @PostMapping("/gc")
    public ResponseEntity<String> grammarCheck(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.grammarCheckParsed(request);
        System.out.println(openAiRequest.getBody());
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }

    @PostMapping("/mood")
    public ResponseEntity<String> tweetClassifier(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.tweetClassifierParsed(request);
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }

    @PostMapping("/trans")
    public ResponseEntity<String> translate(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.translateParsed(request);
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }

    @PostMapping("/summarize")
    public HttpEntity<String> summarize(@RequestBody String request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.summarizeParsed(request);
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }
}
