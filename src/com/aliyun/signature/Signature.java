package com.aliyun.signature;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * It used to to build an immutable signature for api caller through the
 * parameters of specified url; Besides it provide a Builder class to load the
 * relative parameters and compose the final request url easily and friendly.
 *
 * @since 05/06/2017
 * @author Harbor Luo
 */
public final class Signature {
    /**
     * The HTTP method.
     */
    private final String method;

    /**
     * The relative parameters which generally parsed from a URL.
     */
    private final Map<String, String> parameters;

    /**
     * The application secret key.
     */
    private final String secret;

    /**
     * The cached signature, this value is immutable but would be backed up by every thread.
     */
    private String signature;

    /**
     * The requested api url.
     */
    private String url;

    private final static Logger LOG = Logger.getLogger(Signature.class.getName());

    /**
     * Constructs a instance from a Builder class.
     *
     * @param builder the specified builder class
     */
    private Signature(Builder builder) {
        this.method = builder.method;
        this.secret = builder.secret;
        this.url = builder.url;
        this.parameters = The.Maps.immutableCopyOf(builder.parameters);

        The.HTTP.validate(this.method);
        The.checkNotNull(this.secret);
    }

    /**
     * Returns the string style signature.
     * @return the string style signature.
     */
    public String get() {
        try {
            if (signature == null) {
                signature = PublicSignature.generate(this.method, this.parameters, this.secret);
            }
            return signature;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "create signature failed - " + this, e);
            throw new IllegalStateException(e); // fail-fast
        }
    }

    @Override
    public String toString() {
        return "Signature{" +
                "method='" + method + '\'' +
                ", parameters=" + parameters +
                ", secret='" + secret + '\'' +
                ", signature='" + get() + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    /**
     * Returns a new <tt>Builder</tt> instance.
     *
     * @return a new <tt>Builder</tt> instance.
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Returns a new composed api url used to request the resource directly.
     * @return a new composed api url
     */
    public String compose() {
        return url + "&Signature=" + get();
    }

    /**
     * A <tt>Builder</tt> class is used to load the relative parameters
     * build the {@link Signature} instance finally.
     */
    public static class Builder {
        private String method;
        private Map<String, String> parameters = new HashMap<String, String>();
        private String secret;
        private String url;

        /**
         * Returns an immutable {@link Signature} instance.
         * @return an immutable {@link Signature} instance.
         */
        public Signature build() {
            return new Signature(this);
        }

        /**
         * Loads the HTTP method.
         * @param method the HTTP method.
         * @return the Builder self instance.
         */
        public Builder method(String method) {
            this.method = method;
            return this;
        }

        /**
         * Loads the application secret key.
         * @param secret the application secret key
         * @return the Builder self instance.
         */
        public Builder secret(String secret) {
            this.secret = secret;
            return this;
        }

        Builder parameter(String key, String value) {
            this.parameters.put(key, value);
            return this;
        }

        /**
         * Loads the api URL.
         * @param url the api URL.
         * @return the Builder self instance.
         */
        public Builder url(String url) {
            try {
                this.url = url;
                this.parameters = PublicSignature.splitQueryString(url);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            return this;
        }
    }

}
