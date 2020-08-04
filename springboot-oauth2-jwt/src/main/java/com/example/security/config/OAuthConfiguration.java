package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
	private AuthenticationManager authenticationManager;
    
    @Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userService;

	@Value("${jwt.clientId}")
	private String clientId;

	@Value("${jwt.client-secret}")
	private String clientSecret;

	@Value("${jwt.accessTokenValidititySeconds}")
	private int accessTokenValiditySeconds;

	@Value("${jwt.authorizedGrantTypes:password,authorization_code,refresh_token}")
	private String[] authorizedGrantTypes;

	@Value("${jwt.refreshTokenValiditySeconds}")
	private int refreshTokenValiditySeconds;

	public OAuthConfiguration(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
			UserDetailsService userService) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;

	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret))
				.accessTokenValiditySeconds(accessTokenValiditySeconds)
				.refreshTokenValiditySeconds(refreshTokenValiditySeconds).authorizedGrantTypes(authorizedGrantTypes)
				.scopes("read", "write").resourceIds("api");
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.accessTokenConverter(accessTokenConverter()).userDetailsService(userService)
				.authenticationManager(authenticationManager);
	}

	@Bean
	JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		return converter;
	}
}
