package msmith.enumreplication

import javax.sql.DataSource
import org.postgresql.Driver
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class DataSourceConfig {

  @Bean
  fun dataSource(): DataSource {
    return DataSourceBuilder.create()
      .driverClassName(Driver::class.java.canonicalName)
      .url(Postgres.jdbcUrl)
      .username(Postgres.username)
      .password(Postgres.password)
      .build()
  }
}
