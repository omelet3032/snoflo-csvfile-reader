-- csv 파일 데이터를 db로 저장
CREATE TABLE file_data (
    id INTEGER AUTO_INCREMENT,
    concept VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    keyword1 VARCHAR(255),
    keyword2 VARCHAR(255),
    PRIMARY KEY(id)
) ENGINE=InnoDB;