package com.chenhz.http.main;

import com.chenhz.http.enums.FormatType;
import com.chenhz.http.enums.MethodType;
import com.chenhz.http.utils.ParameterHelper;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpRequest {
    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String CONTENT_MD5 = "Content-MD5";
    protected static final String CONTENT_LENGTH = "Content-Length";
    private String url = null;
    private MethodType method = null;
    protected FormatType httpContentType = null;
    protected byte[] httpContent = null;
    protected String encoding = null;
    protected Map<String, String> headers = null;
    protected Integer connectTimeout = null;
    protected Integer readTimeout = null;
    protected SSLSocketFactory sslSocketFactory = null;

    public HttpRequest(String strUrl) {
        this.url = strUrl;
        this.headers = new HashMap();
    }

    public HttpRequest(String strUrl, Map<String, String> tmpHeaders) {
        this.url = strUrl;
        if (null != tmpHeaders) {
            this.headers = tmpHeaders;
        }

    }

    public HttpRequest() {
    }

    public String getUrl() {
        return this.url;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public FormatType getHttpContentType() {
        return this.httpContentType;
    }

    public void setHttpContentType(FormatType httpContentType) {
        this.httpContentType = httpContentType;
        if (null == this.httpContent && null == httpContentType) {
            this.headers.remove("Content-Type");
        } else {
            this.headers.put("Content-Type", this.getContentTypeValue(this.httpContentType, this.encoding));
        }

    }

    public MethodType getMethod() {
        return this.method;
    }

    public void setMethod(MethodType method) {
        this.method = method;
    }

    public byte[] getHttpContent() {
        return this.httpContent;
    }

    public String getHeaderValue(String name) {
        return (String)this.headers.get(name);
    }

    public Integer getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return this.readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public void putHeaderParameter(String name, String value) {
        if (null != name && null != value) {
            this.headers.put(name, value);
        }

    }

    public void setHttpContent(byte[] content, String encoding, FormatType format) {
        if (null == content) {
            this.headers.remove("Content-MD5");
            this.headers.put("Content-Length", "0");
            this.headers.remove("Content-Type");
            this.httpContentType = null;
            this.httpContent = null;
            this.encoding = null;
        } else {
            this.httpContent = content;
            this.encoding = encoding;
            String contentLen = String.valueOf(content.length);
            String strMd5 = ParameterHelper.md5Sum(content);
            if (null != format) {
                this.httpContentType = format;
            } else {
                this.httpContentType = FormatType.RAW;
            }

            this.headers.put("Content-MD5", strMd5);
            this.headers.put("Content-Length", contentLen);
            this.headers.put("Content-Type", this.getContentTypeValue(this.httpContentType, encoding));
        }
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    public HttpURLConnection buildHttpConnection() throws IOException, GeneralSecurityException {
        Map<String, String> mappedHeaders = this.headers;
        String strUrl = this.url;
        if (null == strUrl) {
            throw new IllegalArgumentException("URL is null for HttpRequest.");
        } else if (null == this.method) {
            throw new IllegalArgumentException("Method is not set for HttpRequest.");
        } else {
            URL url = null;
            String[] urlArray = null;
            if (MethodType.POST.equals(this.method) && null == this.getHttpContent()) {
                urlArray = strUrl.split("\\?");
                url = new URL(urlArray[0]);
            } else {
                url = new URL(strUrl);
            }

            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
            HttpURLConnection httpConn = null;

            if (url.getProtocol().equalsIgnoreCase("https") && this.sslSocketFactory != null) {
                HttpsURLConnection httpsConn = (HttpsURLConnection)url.openConnection();
                httpsConn.setSSLSocketFactory(this.sslSocketFactory);
                httpConn = httpsConn;
            }

            if (httpConn == null) {
                //得到网络访问对象java.net.HttpURLConnection
                httpConn = (HttpURLConnection)url.openConnection();
            }
            // 设置请求方式
            ((HttpURLConnection)httpConn).setRequestMethod(this.method.toString());
            // 设置是否向HttpURLConnection输出
            ((HttpURLConnection)httpConn).setDoOutput(true);
            // 设置是否从httpUrlConnection读入
            ((HttpURLConnection)httpConn).setDoInput(true);
            // 设置是否使用缓存
            ((HttpURLConnection)httpConn).setUseCaches(false);
            // 设置超时时间
            if (this.getConnectTimeout() != null) {
                ((HttpURLConnection)httpConn).setConnectTimeout(this.getConnectTimeout());
            }

            if (this.getReadTimeout() != null) {
                ((HttpURLConnection)httpConn).setReadTimeout(this.getReadTimeout());
            }

            Iterator i$ = mappedHeaders.entrySet().iterator();

            while(i$.hasNext()) {
                Entry<String, String> entry = (Entry)i$.next();
                ((HttpURLConnection)httpConn).setRequestProperty((String)entry.getKey(), (String)entry.getValue());
            }

            if (null != this.getHeaderValue("Content-Type")) {
                ((HttpURLConnection)httpConn).setRequestProperty("Content-Type", this.getHeaderValue("Content-Type"));
            } else {
                String contentTypeValue = this.getContentTypeValue(this.httpContentType, this.encoding);
                if (null != contentTypeValue) {
                    ((HttpURLConnection)httpConn).setRequestProperty("Content-Type", contentTypeValue);
                }
            }

            if (MethodType.POST.equals(this.method) && null != urlArray && urlArray.length == 2) {
                ((HttpURLConnection)httpConn).getOutputStream().write(urlArray[1].getBytes());
            }
            return (HttpURLConnection)httpConn;
        }
    }

    private String getContentTypeValue(FormatType contentType, String encoding) {
        if (null != contentType && null != encoding) {
            return FormatType.mapFormatToAccept(contentType) + ";charset=" + encoding.toLowerCase();
        } else {
            return null != contentType ? FormatType.mapFormatToAccept(contentType) : null;
        }
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public void setSslSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.sslSocketFactory = sslSocketFactory;
    }
}
