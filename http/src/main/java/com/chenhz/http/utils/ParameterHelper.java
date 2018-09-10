package com.chenhz.http.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class ParameterHelper {
    private static final String TIME_ZONE = "GMT";
    private static final String FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String FORMAT_RFC2616 = "EEE, dd MMM yyyy HH:mm:ss zzz";

    public ParameterHelper() {
    }

    public static String getUniqueNonce() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String getISO8601Time(Date date) {
        Date nowDate = date;
        if (null == date) {
            nowDate = new Date();
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(nowDate);
    }

    public static String getRFC2616Date(Date date) {
        Date nowDate = date;
        if (null == date) {
            nowDate = new Date();
        }

        SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(nowDate);
    }

    public static Date parse(String strDate) throws ParseException {
        if (null != strDate && !"".equals(strDate)) {
            try {
                return parseISO8601(strDate);
            } catch (ParseException var2) {
                return parseRFC2616(strDate);
            }
        } else {
            return null;
        }
    }

    public static Date parseISO8601(String strDate) throws ParseException {
        if (null != strDate && !"".equals(strDate)) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            df.setTimeZone(new SimpleTimeZone(0, "GMT"));
            return df.parse(strDate);
        } else {
            return null;
        }
    }

    public static Date parseRFC2616(String strDate) throws ParseException {
        if (null != strDate && !"".equals(strDate) && strDate.length() == "EEE, dd MMM yyyy HH:mm:ss zzz".length()) {
            SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
            df.setTimeZone(new SimpleTimeZone(0, "GMT"));
            return df.parse(strDate);
        } else {
            return null;
        }
    }

    public static String md5Sum(byte[] buff) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(buff);
            return Base64Helper.encode(messageDigest);
        } catch (Exception var3) {
            return null;
        }
    }

    public static byte[] getFormData(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator i$ = params.entrySet().iterator();

        while(i$.hasNext()) {
            Entry<String, String> entry = (Entry)i$.next();
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode((String)entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode((String)entry.getValue(), "UTF-8"));
        }

        return result.toString().getBytes("UTF-8");
    }
}
