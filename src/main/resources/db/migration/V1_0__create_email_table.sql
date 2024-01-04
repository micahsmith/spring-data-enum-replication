CREATE TYPE queue_state AS ENUM (
  'PENDING',
  'SUBMITTED',
  'ERROR',
  'CANCELLED'
);

CREATE TABLE IF NOT EXISTS email (
    id bigserial PRIMARY KEY,
    queue_state queue_state NOT NULL
);