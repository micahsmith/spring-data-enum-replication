package msmith.enumreplication

import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration

@Configuration
class DataConfig : AbstractJdbcConfiguration() {
  override fun userConverters(): List<*> {
    return listOf(QueueState.WConverter())
  }
}
