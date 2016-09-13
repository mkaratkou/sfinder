CREATE DATABASE users_db default character set = 'UTF8' default collate = 'utf8_general_ci';

CREATE USER 'userservice'@'localhost' IDENTIFIED BY 'qwerty1';
GRANT ALL PRIVILEGES ON users_db.* TO 'userservice'@'localhost' WITH GRANT OPTION;
