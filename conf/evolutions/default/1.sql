# --- !Ups

create table "users" ("id" int auto_increment primary key, "name" varchar);

# --- !Downs

drop table "users";
