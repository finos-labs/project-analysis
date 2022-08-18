package org.finos.ls;

import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.graphql_java_generator.client.GraphQLConfiguration;

import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

	@Value("${graphql.endpoint.url}")
	String endpointUrl;
	
	@Value("${graphql.token}")
	String token;
	
	@Bean(name="webClient")
	public WebClient webClient() {
		
		ExchangeFilterFunction eff = ExchangeFilterFunction.ofRequestProcessor(cr -> {
			return Mono.just(ClientRequest.from(cr)
				.header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
				.build());
		});
		
		WebClient wc =  GraphQLConfiguration.getWebClient(endpointUrl, null, eff);
		
		return wc;
	}
	
}
