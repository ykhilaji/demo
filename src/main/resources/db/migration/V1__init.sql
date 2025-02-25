-- Active: 1740063368894@@127.0.0.1@5432@coliship_demo
-- Create task table
CREATE TABLE student(
  id            SERIAL NOT NULL               PRIMARY KEY,
  firstname     VARCHAR (100),
  lastname      VARCHAR (20),
  age           integer
);

CREATE TABLE tasks (
  id              BIGSERIAL                     PRIMARY KEY,
  user_id         TEXT                          NOT NULL,
  token           VARCHAR                       NOT NULL,
  user_data       JSONB,
  job_id          BIGINT                        NOT NULL,
  status          VARCHAR                       NOT NULL,
  data            JSONB                         NOT NULL,
  type            VARCHAR                       NOT NULL,
  response        JSONB,
  date            TIMESTAMP WITH TIME ZONE      NOT NULL DEFAULT NOW(),
  filename        TEXT,
  line_number     INTEGER,
  print_results JSONB
);



-- Create indexes
CREATE INDEX idx_task_user_id ON tasks(user_id);
CREATE INDEX idx_task_status ON tasks(status);
CREATE INDEX idx_task_type ON tasks(type);
