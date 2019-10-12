/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipcentric.simwood.api.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.sipcentric.simwood.api.exception.HttpException;
import com.sipcentric.simwood.api.exception.NumberingException;
import com.sipcentric.simwood.api.model.AllocationResponse;
import com.sipcentric.simwood.api.model.NumbersResponse;
import com.sipcentric.simwood.api.model.SuccessResponse;
import com.sipcentric.simwood.api.record.EmergencyServicesData;
import com.sipcentric.simwood.api.record.Number;
import com.sipcentric.simwood.api.record.SmsMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author charles
 */
public class WebServiceClient {

    private enum HttpMethod {

        GET, POST, PUT, DELETE;
    }
    public static final String base = "/v3";
    private final String host;
    private final String account;
    private final String username;
    private final String password;
    private final int connectTimeout;
    private final int readTimeout;

    WebServiceClient(Builder builder) {
        this.host = builder.host;
        this.account = builder.account;
        this.username = builder.username;
        this.password = builder.password;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
    }

    public final static class Builder {

        String host = "localhost";
        String account = "000000";
        String username = "username";
        String password = "password";
        int connectTimeout = 1000;
        int readTimeout = 5000;

        public Builder() {
        }

        public Builder host(String val) {
            this.host = val;
            return this;
        }
        
        public Builder account(String val) {
            this.account = val;
            return this;
        }
        
        public Builder auth(String username, String password) {
            this.username = username;
            this.password = password;
            return this;
        }

        public Builder connectTimeout(int val) {
            this.connectTimeout = val;
            return this;
        }

        public Builder readTimeout(int val) {
            this.readTimeout = val;
            return this;
        }

        public WebServiceClient build() {
            return new WebServiceClient(this);
        }
    }

    public NumbersResponse availableNumbers(String pattern) throws IOException, NumberingException {
        HashMap<String, String> params = new HashMap<>();
        if (pattern != null) {
            params.put("pattern", pattern);
        }
        TypeReference<List<Number>> tr = new TypeReference<List<Number>>() {};
        List<Number> numbers = this.responseFor(HttpMethod.GET, base + "/numbers/" + account + "/available/standard/100", params, null, tr);
        return new NumbersResponse(numbers);
    }

    public AllocationResponse assignNumber(String number) throws IOException, NumberingException {
        return this.responseFor(HttpMethod.PUT, base + "/numbers/" + account + "/allocated/" + number, null, null, AllocationResponse.class);
    }

    public void unassignNumber(String number) throws IOException, NumberingException {
        this.responseFor(HttpMethod.DELETE, base + "/numbers/" + account + "/allocated/" + number, null, null, SuccessResponse.class);
    }

    public void updateEmergencyServicesData(String number, EmergencyServicesData data) throws IOException, NumberingException {
        TypeReference<List<EmergencyServicesData>> tr = new TypeReference<List<EmergencyServicesData>>() {};
        this.responseFor(HttpMethod.PUT, base + "/numbers/" + account + "/allocated/" + number + "/999", null, data, tr);
    }
    
    public void sendSms(SmsMessage sms) throws IOException, NumberingException {
        this.responseFor(HttpMethod.POST, base + "/messaging/" + account + "/sms", null, sms, SuccessResponse.class);
    }
    
    private <T> T responseFor(HttpMethod method, String resource, HashMap<String, String> params, Object requestObject, Class<T> cls) throws NumberingException, IOException {
        GenericUrl uri = this.createUri(resource, params);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        HttpContent httpContent;
        if (requestObject != null) {
            httpContent = ByteArrayContent.fromString("application/json", mapper.writer().writeValueAsString(requestObject));
        } else {
            httpContent = new EmptyContent();
        }

        HttpResponse response = this.getResponse(uri, method, httpContent);

        String responseBody = "{}";

        if (response.getStatusCode() != 204) {

            responseBody = getSuccessBody(response, uri);

            if (responseBody == null || responseBody.length() <= 0) {
                throw new HttpException("Received a 200 response for " + uri
                        + " but there was no message body.", 200, uri.toURL());
            }

        }

        try {
            return mapper.reader(cls).readValue(responseBody);
        } catch (IOException e) {
            throw new NumberingException(
                    "Received a 200 response but unable to deserialize JSON: "
                    + responseBody);
        }
    }
    
    private <T> T responseFor(HttpMethod method, String resource, HashMap<String, String> params, Object requestObject, TypeReference<T> tr) throws NumberingException, IOException {
        GenericUrl uri = this.createUri(resource, params);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        HttpContent httpContent;
        if (requestObject != null) {
            httpContent = ByteArrayContent.fromString("application/json", mapper.writer().writeValueAsString(requestObject));
        } else {
            httpContent = new EmptyContent();
        }

        HttpResponse response = this.getResponse(uri, method, httpContent);

        String responseBody = "{}";

        if (response.getStatusCode() != 204) {

            responseBody = getSuccessBody(response, uri);

            if (responseBody == null || responseBody.length() <= 0) {
                throw new HttpException("Received a 200 response for " + uri
                        + " but there was no message body.", 200, uri.toURL());
            }

        }

        try {
            return mapper.reader(tr).readValue(responseBody);
        } catch (IOException e) {
            throw new NumberingException(
                    "Received a 200 response but unable to deserialize JSON: "
                    + responseBody);
        }
    }

    private HttpResponse getResponse(GenericUrl uri, HttpMethod method, HttpContent httpContent) throws NumberingException,
            IOException {
        // HttpTransport is not thread safe.
        HttpTransport transport = new NetHttpTransport();
        HttpRequestFactory requestFactory = transport.createRequestFactory();
        HttpRequest request;
        try {
            request = requestFactory.buildRequest(method.toString(), uri, httpContent);
        } catch (IOException e) {
            throw new NumberingException("Error building request", e);
        }
        request.setConnectTimeout(this.connectTimeout);
        request.setReadTimeout(this.readTimeout);

        HttpHeaders headers = request.getHeaders();
        headers.setBasicAuthentication(username, password);
        headers.setUserAgent("Sipcentric Simwood API Java Client v0.1");

        try {
            return request.execute();
        } catch (HttpResponseException e) {
            int status = e.getStatusCode();
            throw new HttpException("Received HTTP status (" + status + ") for " + uri, status, uri.toURL());
        }
    }

    private static String getSuccessBody(HttpResponse response, GenericUrl uri)
            throws NumberingException {
        String body;
        try {
            body = response.parseAsString();
        } catch (IOException e) {
            throw new NumberingException(
                    "Received a 200 response but unable to retrieve message body: "
                    + e.getMessage());
        }

        if (response.getContentType() == null
                || !response.getContentType().contains("json")) {
            throw new NumberingException("Received a 200 response for " + uri
                    + " but it does not appear to be JSON:\n" + body);
        }
        return body;
    }

    private GenericUrl createUri(String path, HashMap<String, String> params) {
        return new GenericUrl("https://" + this.host + path
                + (params != null && !params.isEmpty() ? "?" + createQueryString(params) : ""));

    }

    private String createQueryString(HashMap<String, String> params) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry<String, String> e : params.entrySet()) {
                if (sb.length() > 0) {
                    sb.append('&');
                }
                sb.append(URLEncoder.encode(e.getKey(), "UTF-8")).append('=').append(URLEncoder.encode(e.getValue(), "UTF-8"));
            }
        } catch (UnsupportedEncodingException ex) {
        }
        return sb.toString();
    }
}
