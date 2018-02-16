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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "dstEntityManagerFactory", transactionManagerRef = "dstTransactionManager", basePackages = "br.mp.mpf.twodb.dstDB.repo")
public class ConfigDstDB {

	@Bean(name = "dstDataSource")
	@ConfigurationProperties(prefix = "destino.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "dstEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean dstEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dstDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("br.mp.mp.twoDB.dstDB.domain")
				.properties(hibernateDefaultProperties()).persistenceUnit("dstDB").build();
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
