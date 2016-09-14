use users_db;
drop table if exists T_USER;

create table if not exists T_USER (
  id                     BIGINT(10) not null auto_increment,
  email                  VARCHAR(255) not null unique,
  first_name             VARCHAR(50) not null,
  last_name              VARCHAR(50) not null,
  password               VARCHAR(255) not null,
  landline_phone_number  VARCHAR(50),
  mobile_phone_number    VARCHAR(50),
  constraint pk_profession primary key (id)
) engine = InnoDB DEFAULT CHARSET=utf8;
