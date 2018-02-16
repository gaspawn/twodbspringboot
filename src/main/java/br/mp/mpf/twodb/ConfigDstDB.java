package br.mp.mpf.twodb;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "dstEntityManagerFactory", transactionManagerRef = "dstTransactionManager", basePackages = "br.mp.mpf.twoDB.dstDB.repo")
public class ConfigDstDB {

	@Bean(name = "dstDataSource")
	@ConfigurationProperties(prefix = "destino.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dstEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean dstEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dstDataSource") DataSource dataSource) {		
		return builder.dataSource(dataSource).packages("br.mp.mp.twoDB.dstDB.domain").persistenceUnit("dstDB").build();
	}

	@Bean(name = "dstTransactionManager")
	public PlatformTransactionManager dstTransactionManager(
			@Qualifier("dstEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
		return new JpaTransactionManager(barEntityManagerFactory);
	}

}
