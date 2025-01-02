package nl.jaysh.brugnl.core.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    fun dataSource(): DataSource {
        val dataSource = HikariDataSource()
        dataSource.jdbcUrl = "jdbc:h2:mem:testdb"
        dataSource.driverClassName = "org.h2.Driver"
        dataSource.username = "sa"
        dataSource.password = "password"
        return dataSource
    }
}