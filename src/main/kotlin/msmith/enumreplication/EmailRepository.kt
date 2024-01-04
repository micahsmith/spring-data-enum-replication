package msmith.enumreplication

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmailRepository : CrudRepository<Email, Int> {
  fun findAllByQueueState(queueState: QueueState): List<Email>
}
