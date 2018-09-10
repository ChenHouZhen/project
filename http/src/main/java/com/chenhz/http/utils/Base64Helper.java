package com.chenhz.http.utils;

import java.io.UnsupportedEncodingException;


public class Base64Helper {
    private static final String BASE64_CODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private static final int[] BASE64_DECODE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public Base64Helper() {
    }

    private static byte[] zeroPad(int length, byte[] bytes) {
        byte[] padded = new byte[length];
        System.arraycopy(bytes, 0, padded, 0, bytes.length);
        return padded;
    }

    public static synchronized String encode(byte[] buff) {
        if (null == buff) {
            return null;
        } else {
            StringBuilder strBuilder = new StringBuilder("");
            int paddingCount = (3 - buff.length % 3) % 3;
            byte[] stringArray = zeroPad(buff.length + paddingCount, buff);

            int intPos;
            int i;
            for(intPos = 0; intPos < stringArray.length; intPos += 3) {
                i = ((stringArray[intPos] & 255) << 16) + ((stringArray[intPos + 1] & 255) << 8) + (stringArray[intPos + 2] & 255);
                strBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(i >> 18 & 63));
                strBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(i >> 12 & 63));
                strBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(i >> 6 & 63));
                strBuilder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(i & 63));
            }

            intPos = strBuilder.length();

            for(i = paddingCount; i > 0; --i) {
                strBuilder.setCharAt(intPos - i, '=');
            }

            return strBuilder.toString();
        }
    }

    public static synchronized String encode(String string, String encoding) throws UnsupportedEncodingException {
        if (null != string && null != encoding) {
            byte[] stringArray = string.getBytes(encoding);
            return encode(stringArray);
        } else {
            return null;
        }
    }
/*
    public static synchronized String decode(String string, String encoding) throws UnsupportedEncodingException {
        if (null != string && null != encoding) {
            int posIndex = 0;
            int decodeLen = string.endsWith("==") ? string.length() - 2 : (string.endsWith("=") ? string.length() - 1 : string.length());
            byte[] buff = new byte[decodeLen * 3 / 4];
            int count4 = decodeLen - decodeLen % 4;

            int c0;
            int c1;
            int c2;
            for(c0 = 0; c0 < count4; c0 += 4) {
                c1 = BASE64_DECODE[string.charAt(c0)];
                c2 = BASE64_DECODE[string.charAt(c0 + 1)];
                int c2 = BASE64_DECODE[string.charAt(c0 + 2)];
                int c3 = BASE64_DECODE[string.charAt(c0 + 3)];
                buff[posIndex++] = (byte)((c1 << 2 | c2 >> 4) & 255);
                buff[posIndex++] = (byte)(((c2 & 15) << 4 | c2 >> 2) & 255);
                buff[posIndex++] = (byte)(((c2 & 3) << 6 | c3) & 255);
            }

            if (2 <= decodeLen % 4) {
                c0 = BASE64_DECODE[string.charAt(count4)];
                c1 = BASE64_DECODE[string.charAt(count4 + 1)];
                buff[posIndex++] = (byte)((c0 << 2 | c1 >> 4) & 255);
                if (3 == decodeLen % 4) {
                    c2 = BASE64_DECODE[string.charAt(count4 + 2)];
                    buff[posIndex++] = (byte)(((c1 & 15) << 4 | c2 >> 2) & 255);
                }
            }

            return new String(buff, encoding);
        } else {
            return null;
        }
    }*/
}
