package com.rajeshkawali.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OAuthController {

	@Value("${encryptionDecryption.url}")
	private String encryptionDecryptionUrl;

	@Qualifier("oauth2RestTemplate")
	@Autowired
	private OAuth2RestTemplate oauth2RestTemplate;

	//http://localhost:8080/api/test/6c552c4ae896
	@GetMapping("/test/{key}")
	public void testMethod(@PathVariable String key) {
		System.out.println("OAuthController.testMethod():key==>"+key);
		String url = encryptionDecryptionUrl + key;
		System.out.println("OAuthController.testMethod():url==>"+url);
		System.out.println("Token==>"+oauth2RestTemplate.getAccessToken());
		String response = oauth2RestTemplate.getForObject(url, String.class);
		System.out.println("OAuthController.testMethod():response==>"+response);
	}
}
