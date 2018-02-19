package br.mp.mpf.twodb;

import java.util.HashMap;
import java.util.Map;

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

import br.mp.mpf.twodb.dstDB.domain.PessoaDestino;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "dstEntityManagerFactory", transactionManagerRef = "dstTransactionManager", basePackages = "br.mp.mpf.twodb.dstDB.repo")
public class ConfigDstDB {

	@Bean(name = "dstDataSource")
	@ConfigurationProperties(prefix = "destino.datasource")
	public DataSource dataSource() {
		DataSource x = DataSourceBuilder.create().build();
		return x;
	}

	@Bean(name = "dstEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean dstEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dstDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource)
				//.packages("br.mp.mpf.twodb.dstDB.domain")				
				//.properties(hibernateDefaultProperties())
				.packages(PessoaDestino.class)
				.persistenceUnit("dstDB").build();
	}

	@Bean(name = "dstTransactionManager")
	public PlatformTransactionManager dstTransactionManager(
			@Qualifier("dstEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
		return new JpaTransactionManager(barEntityManagerFactory);
	}

	public Map<String, ?> hibernateDefaultProperties() {
		HashMap<String, Object> p = new HashMap<String, Object>();
		p.put("hibernate.show_sql", true);
		p.put("hibernate.format_sql", true);
		p.put("hibernate.hbm2ddl.auto", "update");
		return p;

	}

}
