package communication.chatgpt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.chatgpt.data.Completions;
import communication.chatgpt.data.Edits;
import communication.chatgpt.dto.completions.request.CompletionsParsedRequestDto;
import communication.chatgpt.dto.completions.request.CompletionsRequestDto;
import communication.chatgpt.dto.edits.request.EditsParsedRequestDto;
import communication.chatgpt.dto.edits.request.EditsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OpenAiRequestEntity {

    private final ObjectMapper objectMapper;
    private final HttpHeaders headers;

    public HttpEntity<String> editsParsed(EditsRequestDto editRequest) throws JsonProcessingException {
        String editsOpenAiBody = objectMapper.
                writeValueAsString(
                        new EditsParsedRequestDto(
                                Edits.MODEL.data(),
                                editRequest.getInput(),
                                Edits.INSTRUCTION.data()
                        ));
        return new HttpEntity<>(editsOpenAiBody, headers);
    }

    public HttpEntity<String> translateParsed(CompletionsRequestDto completionsRequest) throws JsonProcessingException {
        String translateOpenAiBody = objectMapper.
                writeValueAsString(
                        new CompletionsParsedRequestDto(
                                Completions.MODEL.data(),
                                Completions.TRANSLATE.data() + completionsRequest.getPrompt(),
                                completionsRequest.getPrompt().length() * 2
                        )
                );
        return new HttpEntity<>(translateOpenAiBody, headers);

    }

    public HttpEntity<String> tweetClassifierParsed(CompletionsRequestDto completionsRequest) throws JsonProcessingException {
        String tweetClassifierOpenAiBody = objectMapper.
                writeValueAsString(
                        new CompletionsParsedRequestDto(
                                Completions.MODEL.data(),
                                Completions.TWEET_CLASSIFIER.data() + completionsRequest.getPrompt(),
                                completionsRequest.getPrompt().length() * 2
                        )
                );
        return new HttpEntity<>(tweetClassifierOpenAiBody, headers);
    }
}