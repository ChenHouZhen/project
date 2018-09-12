package com.chenhz.http.main;

import com.chenhz.http.enums.FormatType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpResponse extends HttpRequest {
    private int status;

    public HttpResponse(String strUrl) {
        super(strUrl);
    }

    public HttpResponse() {
    }

    public void setHttpContent(byte[] content, String encoding, FormatType format) {
        this.httpContent = content;
        this.encoding = encoding;
        this.httpContentType = format;
    }

    public String getHeaderValue(String name) {
        String value = (String)this.headers.get(name);
        if (null == value) {
            value = (String)this.headers.get(name.toLowerCase());
        }

        return value;
    }

    private static byte[] readContent(InputStream content) throws IOException {
        if (content == null) {
            return null;
        } else {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];

            while(true) {
                int read = content.read(buff);
                if (read == -1) {
                    return outputStream.toByteArray();
                }

                outputStream.write(buff, 0, read);
            }
        }
    }

    private static void pasrseHttpConn(HttpResponse response, HttpURLConnection httpConn, InputStream content) throws IOException {
        byte[] buff = readContent(content);
        response.setStatus(httpConn.getResponseCode());
        Map<String, List<String>> headers = httpConn.getHeaderFields();
        Iterator i$ = headers.entrySet().iterator();

        while(true) {
            Entry entry;
            String key;
            do {
                if (!i$.hasNext()) {
                    String type = response.getHeaderValue("Content-Type");
                    if (null != buff && null != type) {
                        response.setEncoding("UTF-8");
                        String[] split = type.split(";");
                        response.setHttpContentType(FormatType.mapAcceptToFormat(split[0].trim()));
                        if (split.length > 1 && split[1].contains("=")) {
                            String[] codings = split[1].split("=");
                            response.setEncoding(codings[1].trim().toUpperCase());
                        }
                    }

                    response.setStatus(httpConn.getResponseCode());
                    response.setHttpContent(buff, response.getEncoding(), response.getHttpContentType());
                    return;
                }

                entry = (Entry)i$.next();
                key = (String)entry.getKey();
            } while(null == key);

            List<String> values = (List)entry.getValue();
            StringBuilder builder = new StringBuilder((String)values.get(0));

            for(int i = 1; i < values.size(); ++i) {
                builder.append(",");
                builder.append((String)values.get(i));
            }

            response.putHeaderParameter(key, builder.toString());
        }
    }

    public static HttpResponse getResponse(HttpRequest request) throws IOException, GeneralSecurityException {
        OutputStream out = null;
        InputStream content = null;
        HttpResponse response = null;
        HttpURLConnection httpConn = request.buildHttpConnection();

        HttpResponse var6;
        try {
            // 连接
            httpConn.connect();
            if (null != request.getHttpContent() && request.getHttpContent().length > 0) {
                out = httpConn.getOutputStream();
                out.write(request.getHttpContent());
            }

            content = httpConn.getInputStream();
            response = new HttpResponse(httpConn.getURL().toString());
            pasrseHttpConn(response, httpConn, content);
            HttpResponse var5 = response;
            return var5;
        } catch (IOException var10) {
            content = httpConn.getErrorStream();
            response = new HttpResponse(httpConn.getURL().toString());
            pasrseHttpConn(response, httpConn, content);
            var6 = response;
        } finally {
            if (content != null) {
                content.close();
            }

            httpConn.disconnect();
        }

        return var6;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return 200 <= this.status && 300 > this.status;
    }
}
