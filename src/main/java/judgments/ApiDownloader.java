package judgments;

import javax.net.ssl.X509ExtendedTrustManager;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

import judgments.ApiModel.Judgment;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.PemUtils;


public class ApiDownloader {
    private final HttpClient client;
    private final static String SAOS_URL = "https://www.saos.org.pl/api/search/judgments";

    public ApiDownloader() {
        X509ExtendedTrustManager trustManager = PemUtils.loadTrustMaterial("saos-org-pl.pem");
        SSLFactory sslFactory = SSLFactory.builder()
                .withDefaultTrustMaterial()
                .withTrustMaterial(trustManager)
                .build();

        this.client = HttpClient.newBuilder()
                .sslParameters(sslFactory.getSslParameters())
                .sslContext(sslFactory.getSslContext())
                .build();
    }

    private URI createUri(LocalDate from, LocalDate to) {
        return UriBuilder.fromPath(SAOS_URL)
                .queryParam("judgmentDateFrom", from)
                .queryParam("judgmentDateTo", to)
                .build();
    }

    private HttpRequest createRequest(URI uri) {
        return HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .build();
    }

    public List<Judgment> requestJudgments(LocalDate from, LocalDate to) throws IOException, InterruptedException {
        URI uri = this.createUri(from, to);
        HttpRequest request = this.createRequest(uri);
        HttpResponse<String> httpResponse = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        return JSONParser.parse(httpResponse.body());
    }
}
