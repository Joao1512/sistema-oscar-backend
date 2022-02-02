package com.company.sistema01backEnd.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.company.sistema01backEnd.model.administracao.Credencial;


@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private DetailService detailsService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		Credencial credencial = detailsService.findByLogin(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		info.put("login: ", credencial.getEmail());
		info.put("id", credencial.getId());
		
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}


}