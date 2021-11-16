package com.rajeshkawali.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class OAuth2ClientConfig {

	@Value("${oAuth2.clientId}")
	private String oAuth2ClientId;

	@Value("${oAuth2.clientSecret}")
	private String oAuth2ClientSecret;

	@Value("${oAuth2.accesTokenUri}")
	private String accessTokenUri;

	@Value("${oAuth2.grantType}")
	private String grantType;

	// @Value("${oAuth2.scope}")
	// private String scope;

	@Bean
	public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri(accessTokenUri);
		resourceDetails.setClientId(oAuth2ClientId);
		resourceDetails.setClientSecret(oAuth2ClientSecret);
		resourceDetails.setGrantType(grantType);
		// resourceDetails.setScope(Collections.singletonList(scope)); // Optional
		return resourceDetails;
	}

	@Bean
	public OAuth2ClientContext oauth2ClientContext() {
		DefaultOAuth2ClientContext defaultOAuth2ClientContext = new DefaultOAuth2ClientContext();
		return defaultOAuth2ClientContext;
	}

	@Bean(name = "oauth2RestTemplate")
	public OAuth2RestTemplate oauth2RestTemplate() {
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(oAuth2ProtectedResourceDetails(), oauth2ClientContext());
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		restTemplate.setRequestFactory(factory);
		System.out.println(restTemplate.getAccessToken());
		return restTemplate;
	}

}