INSERT INTO roles(id, name) VALUES (1,'ROLE_ADMIN') ON CONFLICT (id) DO NOTHING;
INSERT INTO roles(id, name) VALUES (2,'ROLE_USER') ON CONFLICT (id) DO NOTHING;

INSERT INTO accounts(username, password)
    VALUES ('admin','$2a$10$bHLKc7OcaLOcTIuHodPJzusALqFDkFU6ncI5Z48Mf0VdKeknexzHC')
    ON CONFLICT (username) DO NOTHING;

INSERT INTO accounts_roles (account_id,role_id)
    VALUES (1,1)
    ON CONFLICT (account_id,role_id) DO NOTHING;

INSERT INTO accounts_roles (account_id,role_id)
    VALUES (1,2)
    ON CONFLICT (account_id,role_id) DO NOTHING;