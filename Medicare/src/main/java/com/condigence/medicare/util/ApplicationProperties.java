package com.condigence.medicare.util;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties("app")
public class ApplicationProperties {

	@Valid
	@NotNull
	private Cache cache;
	@Valid
	@NotNull
	private Cors cors;

	@Valid
	@NotNull
	private String host;

	@Valid
	@NotNull
	private String port;

	@Valid
	@NotNull
	private String protocol;

	public static class Cache {

		private Integer ttl;
		private Long maxEntries;

		public Integer getTtl() {
			return ttl;
		}

		public void setTtl(Integer ttl) {
			this.ttl = ttl;
		}

		public Long getMaxEntries() {
			return maxEntries;
		}

		public void setMaxEntries(Long maxEntries) {
			this.maxEntries = maxEntries;
		}

		@Override
		public String toString() {
			return "Cache{" + "ttl=" + ttl + ", maxEntries=" + maxEntries + '}';
		}
	}

	public static class Cors {
		private List<String> allowedOrigins;
		private String[] allowedMethods;
		private List<String> allowedHeaders;
		private Boolean allowCredentials;
		private Integer maxAge;

		public List<String> getAllowedOrigins() {
			return allowedOrigins;
		}

		public void setAllowedOrigins(List<String> allowedOrigins) {
			this.allowedOrigins = allowedOrigins;
		}

		public String[] getAllowedMethods() {
			return allowedMethods;
		}

		public void setAllowedMethods(String[] allowedMethods) {
			this.allowedMethods = allowedMethods;
		}

		public List<String> getAllowedHeaders() {
			return allowedHeaders;
		}

		public void setAllowedHeaders(List<String> allowedHeaders) {
			this.allowedHeaders = allowedHeaders;
		}

		public Boolean getAllowCredentials() {
			return allowCredentials;
		}

		public void setAllowCredentials(Boolean allowCredentials) {
			this.allowCredentials = allowCredentials;
		}

		public Integer getMaxAge() {
			return maxAge;
		}

		public void setMaxAge(Integer maxAge) {
			this.maxAge = maxAge;
		}

		@Override
		public String toString() {
			return "Cors{" + "allowedOrigins=" + allowedOrigins + ", allowedMethods=" + Arrays.toString(allowedMethods)
					+ ", allowedHeaders=" + allowedHeaders + ", allowCredentials=" + allowCredentials + ", maxAge="
					+ maxAge + '}';
		}
	}

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Cors getCors() {
		return cors;
	}

	public void setCors(Cors cors) {
		this.cors = cors;
	}

	@Override
	public String toString() {
		return "ApplicationProperties{" + "cache=" + cache + ", cors=" + cors + '}';
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
}
