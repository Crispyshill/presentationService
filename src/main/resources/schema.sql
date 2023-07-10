-- schema.sql

-- Create the necessary table for user details
CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(500) NOT NULL,
  enabled BOOLEAN NOT NULL,
  PRIMARY KEY (username)
);

-- Create the necessary table for user roles
CREATE TABLE IF NOT EXISTS authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);
