package msmith.enumreplication

import org.springframework.data.annotation.Id

data class Email(
  @Id val id: Long?,
  val queueState: QueueState,
)
