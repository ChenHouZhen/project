package com.chenhz.http.utils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;

public class JsonReader implements Reader {
    private static final Object ARRAY_END_TOKEN = new Object();
    private static final Object OBJECT_END_TOKEN = new Object();
    private static final Object COMMA_TOKEN = new Object();
    private static final Object COLON_TOKEN = new Object();
    private static final int FIRST_POSITION = 0;
    private static final int CURRENT_POSITION = 1;
    private static final int NEXT_POSITION = 2;
    private CharacterIterator ct;
    private char c;
    private Object token;
    private StringBuffer stringBuffer = new StringBuffer();
    private Map<String, String> map = new HashMap();
    private static Map<Character, Character> escapes = new HashMap();

    public JsonReader() {
    }

    public Map<String, String> read(String response, String endpoint) {
         return this.read(new StringCharacterIterator(response), endpoint, 0);
    }

    public Map<String, String> readForHideArrayItem(String response, String endpoint) {
        return this.readForHideItem(new StringCharacterIterator(response), endpoint, 0);
    }

    public Map<String, String> read(CharacterIterator ci, String endpoint, int start) {
        this.ct = ci;
        switch(start) {
            case 0:
                this.c = this.ct.first();
                break;
            case 1:
                this.c = this.ct.current();
                break;
            case 2:
                this.c = this.ct.next();
        }

        this.readJson(endpoint);
        return this.map;
    }

    public Map<String, String> readForHideItem(CharacterIterator ci, String endpoint, int start) {
        this.ct = ci;
        switch(start) {
            case 0:
                this.c = this.ct.first();
                break;
            case 1:
                this.c = this.ct.current();
                break;
            case 2:
                this.c = this.ct.next();
        }

        this.readJsonForHideItem(endpoint);
        return this.map;
    }

    private Object readJson(String baseKey) {
        this.skipWhiteSpace();
        char ch = this.c;
        this.nextChar();
        switch(ch) {
            case '"':
                this.token = this.processString();
                break;
            case ',':
                this.token = COMMA_TOKEN;
                break;
            case ':':
                this.token = COLON_TOKEN;
                break;
            case '[':
                if (this.c == '"') {
                    this.processList(baseKey);
                } else {
                    this.processArray(baseKey);
                }
                break;
            case ']':
                this.token = ARRAY_END_TOKEN;
                break;
            case 'f':
                this.nextChar();
                this.nextChar();
                this.nextChar();
                this.nextChar();
                this.token = Boolean.FALSE;
                break;
            case 'n':
                this.nextChar();
                this.nextChar();
                this.nextChar();
                this.token = null;
                break;
            case 't':
                this.nextChar();
                this.nextChar();
                this.nextChar();
                this.token = Boolean.TRUE;
                break;
            case '{':
                this.processObject(baseKey);
                break;
            case '}':
                this.token = OBJECT_END_TOKEN;
                break;
            default:
                this.c = this.ct.previous();
                if (Character.isDigit(this.c) || this.c == '-') {
                    this.token = this.processNumber();
                }
        }

        return this.token;
    }

    private Object readJsonForHideItem(String baseKey) {
        this.skipWhiteSpace();
        char ch = this.c;
        this.nextChar();
        switch(ch) {
            case '"':
                this.token = this.processString();
                break;
            case ',':
                this.token = COMMA_TOKEN;
                break;
            case ':':
                this.token = COLON_TOKEN;
                break;
            case '[':
                if (this.c == '"') {
                    this.processList(baseKey);
                } else {
                    this.processArrayForHideItem(baseKey);
                }
                break;
            case ']':
                this.token = ARRAY_END_TOKEN;
                break;
            case 'f':
                this.nextChar();
                this.nextChar();
                this.nextChar();
                this.nextChar();
                this.token = Boolean.FALSE;
                break;
            case 'n':
                this.nextChar();
                this.nextChar();
                this.nextChar();
                this.token = null;
                break;
            case 't':
                this.nextChar();
                this.nextChar();
                this.nextChar();
                this.token = Boolean.TRUE;
                break;
            case '{':
                this.processObjectForHideItemName(baseKey);
                break;
            case '}':
                this.token = OBJECT_END_TOKEN;
                break;
            default:
                this.c = this.ct.previous();
                if (Character.isDigit(this.c) || this.c == '-') {
                    this.token = this.processNumber();
                }
        }

        return this.token;
    }

    private void processObject(String baseKey) {
        String key = baseKey + "." + this.readJson(baseKey);

        while(true) {
            do {
                if (this.token == OBJECT_END_TOKEN) {
                    return;
                }

                this.readJson(key);
            } while(this.token == OBJECT_END_TOKEN);

            Object object = this.readJson(key);
            if (object instanceof String || object instanceof Number || object instanceof Boolean) {
                this.map.put(key, String.valueOf(object));
            }

            if (this.readJson(key) == COMMA_TOKEN) {
                key = String.valueOf(this.readJson(key));
                key = baseKey + "." + key;
            }
        }
    }

    private void processObjectForHideItemName(String baseKey) {
        String key = baseKey + "." + this.readJsonForHideItem(baseKey);

        while(true) {
            do {
                if (this.token == OBJECT_END_TOKEN) {
                    return;
                }

                this.readJsonForHideItem(key);
            } while(this.token == OBJECT_END_TOKEN);

            Object object = this.readJsonForHideItem(key);
            if (object instanceof String || object instanceof Number || object instanceof Boolean) {
                this.map.put(key, String.valueOf(object));
            }

            if (this.readJson(key) == COMMA_TOKEN) {
                key = String.valueOf(this.readJson(key));
                key = baseKey + "." + key;
            }
        }
    }

    private void processList(String baseKey) {
        Object value = this.readJson(baseKey);
        int index = 0;

        while(this.token != ARRAY_END_TOKEN) {
            String key = trimFromLast(baseKey, ".") + "[" + index++ + "]";
            this.map.put(key, String.valueOf(value));
            if (this.readJson(baseKey) == COMMA_TOKEN) {
                value = this.readJson(baseKey);
            }
        }

        this.map.put(trimFromLast(baseKey, ".") + ".Length", String.valueOf(index));
    }

    private void processArray(String baseKey) {
        int index = 0;
        String preKey = baseKey.substring(0, baseKey.lastIndexOf("."));
        String key = preKey + "[" + index + "]";
        Object value = this.readJson(key);

        while(this.token != ARRAY_END_TOKEN) {
            this.map.put(preKey + ".Length", String.valueOf(index + 1));
            if (value instanceof String) {
                this.map.put(key, String.valueOf(value));
            }

            if (this.readJson(baseKey) == COMMA_TOKEN) {
                StringBuilder var10000 = (new StringBuilder()).append(preKey).append("[");
                ++index;
                key = var10000.append(index).append("]").toString();
                value = this.readJson(key);
            }
        }

    }

    private void processArrayForHideItem(String baseKey) {
        int index = 0;
        String preKey = baseKey;
        String key = baseKey + "[" + index + "]";
        Object value = this.readJson(key);

        while(this.token != ARRAY_END_TOKEN) {
            this.map.put(preKey + ".Length", String.valueOf(index + 1));
            if (value instanceof String) {
                this.map.put(key, String.valueOf(value));
            }

            if (this.readJson(baseKey) == COMMA_TOKEN) {
                StringBuilder var10000 = (new StringBuilder()).append(preKey).append("[");
                ++index;
                key = var10000.append(index).append("]").toString();
                value = this.readJson(key);
            }
        }

    }

    private Object processNumber() {
        this.stringBuffer.setLength(0);
        if ('-' == this.c) {
            this.addChar();
        }

        this.addDigits();
        if ('.' == this.c) {
            this.addChar();
            this.addDigits();
        }

        if ('e' == this.c || 'E' == this.c) {
            this.addChar();
            if ('+' == this.c || '-' == this.c) {
                this.addChar();
            }

            this.addDigits();
        }

        return this.stringBuffer.toString();
    }

    private void addDigits() {
        while(Character.isDigit(this.c)) {
            this.addChar();
        }

    }

    private void skipWhiteSpace() {
        while(Character.isWhitespace(this.c)) {
            this.nextChar();
        }

    }

    private char nextChar() {
        this.c = this.ct.next();
        return this.c;
    }

    private Object processString() {
        this.stringBuffer.setLength(0);

        while(this.c != '"') {
            if (this.c == '\\') {
                this.nextChar();
                Object value = escapes.get(this.c);
                if (value != null) {
                    this.addChar((Character)value);
                }
            } else {
                this.addChar();
            }
        }

        this.nextChar();
        return this.stringBuffer.toString();
    }

    private void addChar(char ch) {
        this.stringBuffer.append(ch);
        this.nextChar();
    }

    private void addChar() {
        this.addChar(this.c);
    }

    public static String trimFromLast(String str, String stripString) {
        int pos = str.lastIndexOf(stripString);
        return pos > -1 ? str.substring(0, pos) : str;
    }

    static {
        escapes.put('\\', '\\');
        escapes.put('/', '/');
        escapes.put('"', '"');
        escapes.put('t', '\t');
        escapes.put('n', '\n');
        escapes.put('r', '\r');
        escapes.put('b', '\b');
        escapes.put('f', '\f');
    }
}
