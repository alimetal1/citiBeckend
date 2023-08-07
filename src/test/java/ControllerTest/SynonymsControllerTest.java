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
        String word = "example";
        String apiUrl = "https://wordsapiv1.p.mashape.com/words/" + word + "/synonyms";
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
    public void testGetSynonyms_Error() {
        String word = "invalidword";
        String apiUrl = "https://wordsapiv1.p.mashape.com/words/" + word + "/synonyms";

        when(restTemplate.getForEntity(apiUrl, String.class)).thenThrow(new RuntimeException("API Error"));

        SynonymsController synonymsController = new SynonymsController(restTemplate);
        ResponseEntity<String> response = synonymsController.getSynonyms(word);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al obtener los sinónimos", response.getBody());
        verify(restTemplate, times(1)).getForEntity(apiUrl, String.class);
    }
}
