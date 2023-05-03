package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import communication.chatgpt.dto.chat.request.ChatRequestDto;
import communication.chatgpt.dto.completions.request.CompletionsRequestDto;
import communication.chatgpt.dto.summary.request.SummarizeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class OpenAiController {

    private final OpenAiResponseEntity openAiResponseEntity;
    private final OpenAiRequestEntity openAiRequestEntity;

    @PostMapping("/chat/completions")
    public ResponseEntity<String> chat(@RequestBody ChatRequestDto request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.chatParsed(request);
        return openAiResponseEntity.chatParsed(openAiRequest);
    }

    @PostMapping("/gc")
    public ResponseEntity<String> grammarCheck(@RequestBody CompletionsRequestDto request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.grammarCheckParsed(request);
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }

    @PostMapping("/mood")
    public ResponseEntity<String> tweetClassifier(@RequestBody CompletionsRequestDto request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.tweetClassifierParsed(request);
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }

    @PostMapping("/trans")
    public ResponseEntity<String> translate(@RequestBody CompletionsRequestDto request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.translateParsed(request);
        return openAiResponseEntity.completionsParsed(openAiRequest);
    }

    @PostMapping("/summarize")
    public HttpEntity<String> summarize(@RequestBody SummarizeRequestDto request) throws JsonProcessingException {
        HttpEntity<String> openAiRequest = openAiRequestEntity.summarizeParsed(request);
        return openAiResponseEntity.summarizeParsed(openAiRequest);
    }
}