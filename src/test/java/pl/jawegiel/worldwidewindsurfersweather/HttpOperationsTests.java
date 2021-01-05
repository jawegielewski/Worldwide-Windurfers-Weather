package pl.jawegiel.worldwidewindsurfersweather;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jawegiel.worldwidewindsurfersweather.utility.ForecastApiKey;
import pl.jawegiel.worldwidewindsurfersweather.utility.HttpOperations;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HttpOperationsTests {
    
    public static final String TARGET_HOST_NOT_SPECIFIED = "Target host is not specified";
    public static final String URL_OF_CORRECT_CITY = "https://api.weatherbit.io/v2.0/forecast/daily?city=Jastarnia&country=pl&key="+ ForecastApiKey.getKey();


    @Test
    void testIfMakeHttpRequestReturnsErrorWhenWrongUrl() {
        HttpPost httpPost = new HttpPost("abc");
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            httpClient.execute(httpPost);
        } catch (Exception ex) {
            assertEquals(TARGET_HOST_NOT_SPECIFIED,  ex.getCause().getMessage());
        }
    }

    @Test
    void testIfMakeHttpRequestReturnsOkWhenCorrectUrl() throws IOException {
        HttpGet httpGet = new HttpGet(URL_OF_CORRECT_CITY);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse = httpClient.execute(httpGet);
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    @Test
    void testIfGetJsonFromInputStreamReturnsData() throws IOException {
        InputStream is = HttpOperations.makeHttpRequest(URL_OF_CORRECT_CITY);
        String jsonData = HttpOperations.convertJsonFromInputStreamToString(is);
        assertTrue(jsonData.contains("data"));
    }
}
