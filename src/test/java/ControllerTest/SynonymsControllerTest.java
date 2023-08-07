package ControllerTest;

import com.citidemo.citiproject.controller.SynonymsController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SynonymsControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void testGetSynonyms_Success() {
        String word = "hello";
        String apiUrl = "https://wordsapiv1.p.mashape.com/words/words/hello/synonyms?when=2023-08-07T14:05:22.164Z&encrypted=8cfdb189e7229b9bea9607beeb58bebcaeb22f0931f993b8";
        String responseBody = "[\"synonym1\", \"synonym2\"]";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(apiUrl, String.class)).thenReturn(expectedResponse);

        SynonymsController synonymsController = new SynonymsController(restTemplate);
        ResponseEntity<String> response = synonymsController.getSynonyms(word);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseBody, response.getBody());
        verify(restTemplate, times(1)).getForEntity(apiUrl, String.class);
    }

    @Test
    public void testGetSynonyms_InternalServerError() {
        String word = "hello";
        String apiUrl = "https://wordsapiv1.p.mashape.com/words/words/hello/synonyms?when=2023-08-07T14:05:22.164Z&encrypted=8cfdb189e7229b9bea9607beeb58bebcaeb22f0931f993b8";
        String errorMessage = "Error al obtener los sinónimos";

        when(restTemplate.getForEntity(apiUrl, String.class))
                .thenThrow(new RuntimeException(errorMessage));

        SynonymsController synonymsController = new SynonymsController(restTemplate);
        ResponseEntity<String> response = synonymsController.getSynonyms(word);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(errorMessage, response.getBody());
        verify(restTemplate, times(1)).getForEntity(apiUrl, String.class);
    }


}
