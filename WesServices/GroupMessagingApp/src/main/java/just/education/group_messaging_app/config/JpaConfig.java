package just.education.group_messaging_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    @Value(value = "${entitymanager.packages}")
    private String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaProperties jpaProperties) {

        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setPackagesToScan(this.PROP_ENTITYMANAGER_PACKAGES_TO_SCAN);


        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        lemfb.setJpaVendorAdapter(hibernateJpaVendorAdapter);

        lemfb.setDataSource(dataSource);
        lemfb.setJpaPropertyMap(jpaProperties.getProperties());

        return lemfb;
    }
}