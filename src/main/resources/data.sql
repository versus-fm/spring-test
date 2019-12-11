INSERT INTO users(username, "password")
VALUES
   ('test', '$2a$10$FQtV96dVzVpSoh3/FEgz/uJzlYP0xmMZKUu4qwxnaSo0K0WnmJ5Ky') ON CONFLICT DO NOTHING;