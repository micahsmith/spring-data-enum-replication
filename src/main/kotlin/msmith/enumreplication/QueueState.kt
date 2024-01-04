package msmith.enumreplication

import java.sql.JDBCType
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.jdbc.core.mapping.JdbcValue

enum class QueueState {
  PENDING,
  SUBMITTED,
  ERROR,
  CANCELLED;

  @WritingConverter
  class WConverter : Converter<QueueState, JdbcValue> {
    override fun convert(source: QueueState): JdbcValue {
      return JdbcValue.of(source.name, JDBCType.OTHER)
    }
  }
}
