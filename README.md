### Problem
Spring boot application (behind corporate proxy) using Apache `HTTPClient` could not access the internet even with `http_proxy` and `https_proxy` configured

### Solution
Use `HTTPClient`'s `HttpClientBuilder.create().useSystemProperties()` to apply system properties such as proxy configuration

### Test
Once Spring Boot application is ran (i.e. `./gradlew bootRun` or `java -jar app.jar`), the POC application will invoke two HTTP requests to the defined URL, one without `useSystemProperties` and one using the said solution.
