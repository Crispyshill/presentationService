package configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecondDataSourceConfig {

    @Bean(name="productProperties")
    @ConfigurationProperties("second.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name="productDataSource")
    @ConfigurationProperties(prefix = "second.datasource")
    public DataSource datasource(@Qualifier("productProperties") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }
}