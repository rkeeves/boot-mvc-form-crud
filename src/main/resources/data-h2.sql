INSERT INTO roles (id,name) VALUES
	 (1,'ROLE_ADMIN'),
	 (2,'ROLE_USER');

INSERT INTO accounts (username,password) VALUES
	 ('admin','$2a$10$bHLKc7OcaLOcTIuHodPJzusALqFDkFU6ncI5Z48Mf0VdKeknexzHC');

INSERT INTO accounts_roles (account_id,role_id) VALUES
	 (1,1),
	 (1,2);
