package com.singaporepower.poc;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Executes an HTTP GET request to test if host is reachable based on system properties (i.e. proxy)
 *
 * Created by Hans Christian Ang
 */
@Component
public class ProxyHttpClientTest {

    @PostConstruct
    public void init() {
        String url = "https://www.google.com";
        testWithoutProxy(url);
        testWithProxy(url);
    }

    public void testWithoutProxy(String url) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            // Create request
            HttpUriRequest request = new HttpGet(new URIBuilder(url).build());

            // Execute HTTP
            HttpResponse httpResponse = httpClient.execute(request);

            System.out.println("[No proxy applied] " + httpResponse.getEntity().getContent());
        }
        catch (Exception e) {
            System.out.println("[No proxy applied] " + e.getMessage());
//            e.printStackTrace();
        }
    }

    public void testWithProxy(String url) {
        try {
            // Added `useSystemProperties()`
            HttpClient httpClient = HttpClientBuilder.create().useSystemProperties().build();

            // Create request
            HttpUriRequest request = new HttpGet(new URIBuilder(url).build());

            // Execute HTTP
            HttpResponse httpResponse = httpClient.execute(request);

            System.out.println("[Proxy from system props applied] " + httpResponse.getEntity().getContent());
        }
        catch (Exception e) {
            System.out.println("[Proxy from system props applied] " + e.getMessage());
//            e.printStackTrace();
        }
    }
}
