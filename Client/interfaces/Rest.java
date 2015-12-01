package interfaces;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.net.URL;
import java.util.Objects;
import java.util.stream.Stream;

public class Rest {

    private final String host;
    private String lastUrl;

    private Rest(String host) {
        Objects.requireNonNull(host, "Host is null.");
        this.host = host;
    }

    public static Rest newInstance(String host) {
        return new Rest(host);
    }

    public String getHost() {
        return host;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public GetRequest get(String... path) {
        return Unirest.get(constructUrl(path));
    }

    public HttpRequestWithBody post(String... path) {
        return Unirest.post(constructUrl(path));
    }

    public HttpRequestWithBody put(String... path) {
        return Unirest.put(constructUrl(path));
    }

    public HttpRequestWithBody patch(String... path) {
        return Unirest.patch(constructUrl(path));
    }

    public HttpRequestWithBody delete(String... path) {
        return Unirest.delete(constructUrl(path));
    }

    public GetRequest head(String... path) {
        return Unirest.head(constructUrl(path));
    }

    public HttpRequestWithBody options(String... path) {
        return Unirest.options(constructUrl(path));
    }

    private String constructUrl(String[] path) {
        Objects.requireNonNull(path, "Path is null.");
        lastUrl = String.format("%s/%s", getHost(), String.join("/", path));
        return lastUrl;
    }

}