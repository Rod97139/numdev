INSERT INTO TEACHERS (first_name, last_name)
SELECT * FROM (SELECT 'Margot', 'DELAHAYE') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM teachers WHERE first_name = 'Margot' AND last_name = 'DELAHAYE'
) LIMIT 1;

INSERT INTO TEACHERS (first_name, last_name)
SELECT * FROM (SELECT 'Hélène', 'THIERCELIN') AS tmp
WHERE NOT EXISTS (
    SELECT 1 FROM teachers WHERE first_name = 'Hélène' AND last_name = 'THIERCELIN'
) LIMIT 1;

INSERT INTO USERS (first_name, last_name, admin, email, password)
VALUES ('Admin', 'Admin', true, 'yoga@studio.com', '$2a$10$.Hsa/ZjUVaHqi0tp9xieMeewrnZxrZ5pQRzddUXE/WjDu2ZThe6Iq')
ON DUPLICATE KEY UPDATE
                     first_name = VALUES(first_name),
                     last_name = VALUES(last_name),
                     password = VALUES(password);