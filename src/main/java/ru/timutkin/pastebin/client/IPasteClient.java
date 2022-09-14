package ru.timutkin.pastebin.client;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.timutkin.pastebin.exception.IncorrectTimeException;
import ru.timutkin.pastebin.exception.PasteNotActiveException;
import ru.timutkin.pastebin.exception.PasteNotFoundException;
import ru.timutkin.pastebin.store.entity.PasteEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class IPasteClient implements PasteClient {

    RestTemplate restTemplate;

    private static final String RESOURCE_URL = "http://localhost:2022/api/v1/pastebin/pastes";

    @Override
    public ResponseEntity<PasteEntity> getPasteByHash(String hash) throws PasteNotFoundException, PasteNotActiveException {
        return restTemplate.getForEntity(RESOURCE_URL+"{hash}",PasteEntity.class, hash);
    }

    @Override
    public ResponseEntity<List<PasteEntity>> getLastPaste() throws PasteNotFoundException {
        return restTemplate.exchange(
                RESOURCE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PasteEntity>>() {}
        );
    }

    @Override
    public ResponseEntity<String> savePaste(PasteEntity paste) throws IncorrectTimeException {
        return restTemplate.postForEntity(RESOURCE_URL,paste, String.class );
    }

    @Override
    public ResponseEntity<List<PasteEntity>> getPage(int number) {
        return restTemplate.exchange(
                RESOURCE_URL + "/pages/{number}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PasteEntity>>() {},
                number
        );
    }
}
