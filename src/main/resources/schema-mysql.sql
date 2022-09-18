-- [ROLES]
CREATE TABLE IF NOT EXISTS roles (
  id   INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  CONSTRAINT pk_roles PRIMARY KEY (id),
  CONSTRAINT uk_roles_name UNIQUE (name)
) engine=InnoDB;

-- [ACCOUNTS]
CREATE TABLE IF NOT EXISTS accounts (
  id       INTEGER NOT NULL AUTO_INCREMENT,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  CONSTRAINT pk_accounts PRIMARY KEY (id),
  CONSTRAINT uk_accounts_username UNIQUE (username)
) engine=InnoDB;

-- [ACCOUNTS_ROLES]
CREATE TABLE IF NOT EXISTS accounts_roles (
  account_id INTEGER NOT NULL,
  role_id    INTEGER NOT NULL,
  CONSTRAINT uk_accounts_roles UNIQUE (account_id, role_id),
  CONSTRAINT fk_accounts_roles_account FOREIGN KEY (account_id) REFERENCES accounts (id),
  CONSTRAINT fk_accounts_roles_role FOREIGN KEY (role_id) REFERENCES roles (id)
) engine=InnoDB;

-- [TODOS]
CREATE TABLE IF NOT EXISTS todos (
    id        INTEGER NOT NULL AUTO_INCREMENT,
    name      VARCHAR(100) NOT NULL,
    completed BOOLEAN NOT NULL,
    priority  VARCHAR(30) NOT NULL,
    author_id INTEGER NOT NULL,
    CONSTRAINT pk_todos PRIMARY KEY (id),
    CONSTRAINT fk_todos_account FOREIGN KEY (author_id) REFERENCES accounts (id)
) engine=InnoDB;