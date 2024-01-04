package msmith.enumreplication

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.BadSqlGrammarException
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [DataSourceConfig::class])
class EnumReplicationApplicationTests
@Autowired
constructor(private val emailRepository: EmailRepository) {

  @Test
  fun canFindCount() {
    val emails =
      listOf(
        Email(null, QueueState.PENDING),
        Email(null, QueueState.PENDING),
        Email(null, QueueState.SUBMITTED)
      )
    val expectedCount = emails.count()

    emailRepository.saveAll(emails)

    val actualCount = emailRepository.findAll().count()
    Assertions.assertEquals(expectedCount, actualCount)
  }

  @Test
  // Test to confirm that the enum does get read into Email instances without issue
  fun canReadEnumFromQuery() {
    val emails =
      listOf(
        Email(null, QueueState.PENDING),
        Email(null, QueueState.SUBMITTED),
        Email(null, QueueState.CANCELLED),
      )

    val expectedValues = emails.map { it.queueState }.toSet()

    emailRepository.saveAll(emails)
    val actualValues = emailRepository.findAll().map { it.queueState }.toSet()

    Assertions.assertEquals(expectedValues, actualValues)
  }

  @Test
  fun cannotQueryWithEnum() {
    val emails =
      listOf(
        Email(null, QueueState.PENDING),
        Email(null, QueueState.SUBMITTED),
        Email(null, QueueState.CANCELLED),
      )

    emailRepository.saveAll(emails)

    // The call to EmailRepository#findAllByQueueState would work fine in Spring Boot 2.7. But now
    // it will fail; the inner exception will be: org.postgresql.util.PSQLException: ERROR: operator
    // does not exist: queue_state = character varying
    Assertions.assertThrows(BadSqlGrammarException::class.java) {
      emailRepository.findAllByQueueState(QueueState.PENDING)
    }
  }
}
