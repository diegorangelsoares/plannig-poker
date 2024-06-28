package com.diego.planningpoker.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("testes")
public class ApplicationProperty {

	private String appName;
	private Seguranca seguranca = new Seguranca();
	private Log log = new Log();
	private Aws aws = new Aws();
	private SecretsManager secretsManager = new SecretsManager();
	private ApiInfo apiInfo = new ApiInfo();

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public void setSeguranca(Seguranca seguranca) {
		this.seguranca = seguranca;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public Aws getAws() {
		return aws;
	}

	public void setAws(Aws aws) {
		this.aws = aws;
	}

	public ApiInfo getApiInfo() {
		return apiInfo;
	}

	public void setApiInfo(ApiInfo apiInfo) {
		this.apiInfo = apiInfo;
	}

	public SecretsManager getSecretsManager() {
		return secretsManager;
	}

	public void setSecretsManager(SecretsManager secretsManager) {
		this.secretsManager = secretsManager;
	}

	public static class Seguranca {

		private JwtTokenConfig jwtTokenConfig;

		public JwtTokenConfig getJwtTokenConfig() {
			return jwtTokenConfig;
		}

		public void setJwtTokenConfig(JwtTokenConfig jwtTokenConfig) {
			this.jwtTokenConfig = jwtTokenConfig;
		}

		public static class JwtTokenConfig {
			
			private String segredo;

			public String getSegredo() {
				return segredo;
			}

			public void setSegredo(String segredo) {
				this.segredo = segredo;
			}
		}
	}

	public static class Log {

		private Integer pool;
		private boolean elastic;
		private boolean error;
		private boolean info;

		public Integer getPool() {
			return pool;
		}

		public void setPool(Integer pool) {
			this.pool = pool;
		}

		public boolean isElastic() {
			return elastic;
		}

		public void setElastic(boolean elastic) {
			this.elastic = elastic;
		}

		public boolean isError() {
			return error;
		}

		public void setError(boolean error) {
			this.error = error;
		}

		public boolean isInfo() {
			return info;
		}

		public void setInfo(boolean info) {
			this.info = info;
		}
	}

	public static class Aws {

		private Sqs sqs = new Sqs();

		public Sqs getSqs() {
			return sqs;
		}

		public void setSqs(Sqs sqs) {
			this.sqs = sqs;
		}
	}

	public static class Sqs {

		private String accessKey;
		private String secretKey;
		private String fifoQueue;
		private String region;

		public String getAccessKey() {
			return accessKey;
		}

		public void setAccessKey(String accessKey) {
			this.accessKey = accessKey;
		}

		public String getSecretKey() {
			return secretKey;
		}

		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}

		public String getFifoQueue() {
			return fifoQueue;
		}

		public void setFifoQueue(String fifoQueue) {
			this.fifoQueue = fifoQueue;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}
	}

	public static class SecretsManager {

		private String accessKey;
		private String secretKey;
		private String region;
		private String secretId;

		public String getAccessKey() {
			return accessKey;
		}

		public void setAccessKey(String accessKey) {
			this.accessKey = accessKey;
		}

		public String getSecretKey() {
			return secretKey;
		}

		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getSecretId() {
			return secretId;
		}

		public void setSecretId(String secretId) {
			this.secretId = secretId;
		}
	}

	public static class ApiInfo {
		private String titulo;
		private String descricao;
		private String versao;
		private String pacote;

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getVersao() {
			return versao;
		}

		public void setVersao(String versao) {
			this.versao = versao;
		}

		public String getPacote() {
			return pacote;
		}

		public void setPacote(String pacote) {
			this.pacote = pacote;
		}
	}
}
