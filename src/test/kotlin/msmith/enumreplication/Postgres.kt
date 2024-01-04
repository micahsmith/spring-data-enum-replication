package msmith.enumreplication

import org.flywaydb.core.Flyway
import org.flywaydb.core.api.Location
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

object Postgres {
  private const val DATABASE_NAME = "test"
  private const val DATABASE_USERNAME = "db_user"
  private const val DATABASE_PASSWORD = "ThZaVUWHzmmh"

  private val dockerImageName = DockerImageName.parse("postgres:13-bullseye")

  @JvmStatic
  private val instance: PostgreSQLContainer<*> by lazy {
    val container =
      PostgreSQLContainer(dockerImageName)
        .withEnv("POSTGRES_DB", DATABASE_NAME)
        .withEnv("POSTGRES_PASSWORD", DATABASE_PASSWORD)
        .withEnv("POSTGRES_USER", DATABASE_USERNAME)
        .withDatabaseName(DATABASE_NAME)
        .withUsername(DATABASE_USERNAME)
        .withPassword(DATABASE_PASSWORD)

    container.start()
    container.executeMigrations()
    container
  }

  @JvmStatic
  val jdbcUrl: String
    get() = instance.jdbcUrl

  @JvmStatic
  val username: String
    get() = DATABASE_USERNAME

  @JvmStatic
  val password: String
    get() = DATABASE_PASSWORD

  @JvmStatic
  private fun PostgreSQLContainer<*>.executeMigrations() {
    val flyway =
      Flyway.configure()
        .dataSource(this.jdbcUrl, DATABASE_USERNAME, DATABASE_PASSWORD)
        .locations(Location("/db/migration"))
        .load()

    flyway.migrate()
  }
}
