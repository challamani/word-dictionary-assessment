DROP TABLE IF EXISTS file;

CREATE TABLE file (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(500) NOT NULL,
  type VARCHAR(30) NOT NULL,
  size BIGINT NOT NULL
);

DROP TABLE IF EXISTS file_content;

 create table file_content (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content CLOB(30K) NOT NULL,
    tags VARCHAR(1000) default NULL,
    file_id BIGINT NOT NULL,
    foreign key (file_id) references file(id)
);


drop table if exists words;

create table word (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    word varchar(100) not null,
    quick_index varchar(2) not null,
    description varchar(500)
);

drop table if exists word_file_content;

create table word_file_content (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    word_id BIGINT NOT NULL,
    file_content_id BIGINT NOT NULL
);


drop table if exists word_relative_words;
create table  word_relative_words (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 word_id BIGINT not null,
 reference_word_id BIGINT not null,
 is_synonym int not null
);

CREATE INDEX word_quick_index ON word (quick_index);

CREATE UNIQUE INDEX word_file_content_idx ON word_file_content (word_id,file_content_id);

CREATE UNIQUE INDEX word_relative_words_idx ON word_relative_words (word_id,reference_word_id);


