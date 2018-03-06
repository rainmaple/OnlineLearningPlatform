package com.aliyun.signature;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Global constant variables and some utility functions.
 *
 * @since 05/06/2017
 * @author Harbor Luo
 */
public final class The {
    private The() { }

    private final static String UTF_8 = "utf-8";
    private final static String DELIMITER = "&";

    public static String utf_8() {
        return  UTF_8;
    }

    public static  String delimiter() {
        return DELIMITER;
    }

    public static class API {
        private final static String ACCESS = "LTAINbH9JXc8UW4y";
        private final static String SECRET = "tdtAyDvVdusjPkI63IbQKDJFTOt01Y";
        private final static String END_POINT = "https://live.aliyuncs.com/";
        private final static String API_VERSION = "2016-11-01";
        private final static String SIG_VERSION = "1.0";
        private final static String SIG_METHOD = "HMAC-SHA1";
        private final static String RET_XML_FORMAT = "XML";
        private final static String RET_JSON_FORMAT = "JSON";

        public static String secret() {
            return SECRET;
        }

        public static String accessID() {
            return ACCESS;
        }

        public static String endPoint() {
            return  END_POINT;
        }

        public static String apiVersion() {
            return API_VERSION;
        }

        public static String sigVersion() {
            return SIG_VERSION;
        }

        public static String sigMethod() {
            return SIG_METHOD;
        }

        public static String xmlFormat() {
            return RET_XML_FORMAT;
        }

        public static String jsonFormat() {
            return RET_JSON_FORMAT;
        }

        public static String newIOSTimeStamp() {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            df.setTimeZone(new SimpleTimeZone(0, "GMT"));
            return df.format(new Date());
        }

        /**
         * Build and return an api url by a specified action name.
         * @param action the specified action name
         * @return an api url.
         */
        public static String build(String action) {
            return The.API.endPoint() + "?" +
                    "TimeStamp=" + The.API.newIOSTimeStamp() + "&" +
                    "Format=" + The.API.jsonFormat() + "&" +
                    "AccessKeyId=" + The.API.accessID() + "&" +
                    "Action=" + action + "&" +
                    "SignatureMethod=" + The.API.sigMethod() + "&" +
                    "SignatureNonce=" + UUID.randomUUID().toString() + "&" +
                    "Version=" + The.API.apiVersion() + "&" +
                    "SignatureVersion=" + The.API.sigVersion();
        }
    }

    public enum HTTP {
        GET("GET"),
        POST("POST"),
        ;

        private final String method;
        private HTTP(String method) {
            this.method = method;
        }

        public static HTTP validate(String method) {
            for (HTTP m : HTTP.values()) {
                if (m.method().equals(method)) {
                    return m;
                }
            }
            throw new IllegalArgumentException("invalid http method - " + method);
        }

        public String method() {
            return method;
        }
    }

    /**
     * Taken from guava's @{@code Preconditions}
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public final static class Maps {
        public static <K, V> Map<K, V> immutableCopyOf(Map<? extends K, ? extends V> from) {
            checkNotNull(from);
            Map<K, V> map = new HashMap<K, V>(from);
            return Collections.unmodifiableMap(map);
        }
    }

    private final static Logger LOG = Logger.getLogger(The.class.getName());

    /**
     * Translates a string into {@code application/x-www-form-urlencoded}
     * format using a specific encoding scheme. This method uses the
     * supplied encoding scheme to obtain the bytes for unsafe
     * characters. Moreover, this method would be full back to original
     * url if the {@link UnsupportedEncodingException} raised from the
     * underlying encode function.
     *
     * @param url the url need to be translated
     * @param encoding the encoding scheme
     * @return the translated url.
     */
    public static String urlEncodeWithFullback(String url, String encoding) {
        checkNotNull(url);
        checkNotNull(encoding);

        try {
            url = URLEncoder.encode(url, encoding);
        } catch (UnsupportedEncodingException fullback) {
            LOG.log(Level.WARNING,
                    "url encode failed and full back to " + url + ", using encoding " + encoding,
                    fullback);
        }

        return url;
    }

}
