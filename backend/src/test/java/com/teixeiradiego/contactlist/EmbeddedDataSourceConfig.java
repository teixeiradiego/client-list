package com.teixeiradiego.contactlist;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Collections;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariDataSource;

import de.flapdoodle.embed.process.runtime.Network;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;
import ru.yandex.qatools.embed.postgresql.distribution.Version;

@Profile("integration-test")
@Configuration
@EnableAutoConfiguration
public class EmbeddedDataSourceConfig {

	@Autowired
	private EmbeddedDataSourceProperties embeddedDataSourceProperties;

	private EmbeddedPostgres postgres;
	
    @Bean
    @ConfigurationProperties("spring.datasource")
    @Primary
    public HikariDataSource dataSource() throws IOException {
    	
        URI uri = URI.create(embeddedDataSourceProperties.getUrl().substring(5));
        int port = Network.getFreeServerPort();
        embeddedDataSourceProperties.setUrl("jdbc:postgresql://localhost:" 
        		+ port + "/" + uri.getPath().substring(1));
        
        postgres = new EmbeddedPostgres(Version.V9_6_11);
        
        postgres.start(EmbeddedPostgres.cachedRuntimeConfig(
        			Paths.get(embeddedDataSourceProperties.getEmbeddedDirectory())
        		),
        		uri.getHost(), port, uri.getPath().substring(1),
        		embeddedDataSourceProperties.getUsername(),
        		embeddedDataSourceProperties.getPassword(),
        		Collections.emptyList());
        	
    	return embeddedDataSourceProperties
    			.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
        	        
    }

	@PreDestroy
	public void stopDataSource() {
		postgres.stop();
	}

}