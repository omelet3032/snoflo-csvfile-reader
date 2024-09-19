CREATE DATABASE snoflo_file_reader;
-- CREATE USER 'snoflo'@'localhost' IDENTIFIED BY 'Snoflo123!!';
CREATE USER 'snoflo'@'%' IDENTIFIED BY 'Snoflo123!!';
GRANT ALL PRIVILEGES ON snoflo.* TO 'snoflo'@'%';
FLUSH PRIVILEGES;