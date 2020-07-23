-- Role: expense_tracker
-- DROP ROLE expense_tracker;
CREATE ROLE expense_tracker WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  NOREPLICATION
  ENCRYPTED PASSWORD 'md5de3c070e14aa25e5dd302108dee486ae';
COMMENT ON ROLE expense_tracker IS 'Test Project - Expense Tracker';

-- Database: expense_tracker
-- DROP DATABASE expense_tracker;
CREATE DATABASE expense_tracker
    WITH 
    OWNER = expense_tracker
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     23.07.2020 22:48:56                          */
/*==============================================================*/
drop index if exists idx_expense_amount;
drop index if exists idx_expense_dttm;
drop table if exists expense cascade;
drop index if exists spring_session_pk;
drop table if exists spring_session cascade;
drop index if exists spring_session_attributes_pk;
drop table if exists spring_session_attributes;
drop index if exists idx_username;
drop index if exists relation_user_user_role_fk;
drop table if exists "user" cascade;
drop index if exists relation_expense_user_expense_f;
drop index if exists relation_user_user_expense_fk;
drop table if exists user_expenses cascade;
drop index if exists idx_role_name;
drop table if exists user_role cascade;
/*==============================================================*/
/* Table: expense                                               */
/*==============================================================*/
create table expense (
   expense_id           serial               not null,
   expense_dttm         date                 null,
   expense_description  varchar(255)         null,
   expense_amount       numeric(15,2)        null,
   expense_comment      varchar(1024)        null,
   constraint pk_expense primary key (expense_id)
);

comment on table expense is
'Expense';

comment on column expense.expense_id is
'Expense ID (expense_id)';

comment on column expense.expense_dttm is
'Expense Date&Time (expense_dttm)';

comment on column expense.expense_description is
'Expense Description (expense_description)';

comment on column expense.expense_amount is
'Expense amount (expense_amount)';

comment on column expense.expense_comment is
'Expense comment (expense_comment)';

/*==============================================================*/
/* Index: idx_expense_dttm                                      */
/*==============================================================*/
create  index idx_expense_dttm on expense (
expense_dttm
);

/*==============================================================*/
/* Index: idx_expense_amount                                    */
/*==============================================================*/
create  index idx_expense_amount on expense (
expense_amount
);

/*==============================================================*/
/* Table: spring_session                                        */
/*==============================================================*/
create table spring_session (
   primary_id           char(36)             not null,
   session_id           char(36)             not null,
   creation_time        int8                 not null,
   last_access_time     int8                 not null,
   max_inactive_interval int4                 not null,
   expiry_time          int8                 not null,
   principal_name       varchar(100)         null,
   constraint pk_spring_session primary key (primary_id)
);

/*==============================================================*/
/* Index: spring_session_pk                                     */
/*==============================================================*/
create unique index spring_session_pk on spring_session (
primary_id
);

/*==============================================================*/
/* Table: spring_session_attributes                             */
/*==============================================================*/
create table spring_session_attributes (
   session_primary_id   char(36)             not null,
   attribute_name       varchar(200)         not null,
   attribute_bytes      char                 not null,
   constraint pk_spring_session_attributes primary key (session_primary_id, attribute_name)
);

/*==============================================================*/
/* Index: spring_session_attributes_pk                          */
/*==============================================================*/
create unique index spring_session_attributes_pk on spring_session_attributes (
session_primary_id,
attribute_name
);

/*==============================================================*/
/* Table: "user"                                                */
/*==============================================================*/
create table "user" (
   user_id              serial               not null,
   role_id              int4                 not null,
   username             varchar(20)          not null,
   first_name           varchar(150)         null,
   second_name          varchar(150)         null,
   encrypted_password   varchar(255)         null,
   registration_dttm    timestamp            null,
   constraint pk_user primary key (user_id)
);

comment on table "user" is
'Users';

comment on column "user".user_id is
'User ID (user_id)';

comment on column "user".role_id is
'Role ID (role_id)';

comment on column "user".username is
'username';

comment on column "user".first_name is
'First name (first_name)';

comment on column "user".second_name is
'Second name (second_name)';

comment on column "user".encrypted_password is
'Password MD5 (encrypted_password)';

comment on column "user".registration_dttm is
'Registration Date&Time (registration_dttm)';

/*==============================================================*/
/* Index: relation_user_user_role_fk                            */
/*==============================================================*/
create  index relation_user_user_role_fk on "user" (
role_id
);

/*==============================================================*/
/* Index: idx_username                                          */
/*==============================================================*/
create unique index idx_username on "user" (
username
);

/*==============================================================*/
/* Table: user_expenses                                         */
/*==============================================================*/
create table user_expenses (
   user_expense_id      serial               not null,
   user_id              int4                 not null,
   expense_id           int4                 not null,
   constraint pk_user_expenses primary key (user_expense_id)
);

comment on table user_expenses is
'Users expenses';

comment on column user_expenses.user_expense_id is
'User expense ID (user_expense_id)';

comment on column user_expenses.user_id is
'User ID (user_id)';

comment on column user_expenses.expense_id is
'Expense ID (expense_id)';

/*==============================================================*/
/* Index: relation_user_user_expense_fk                         */
/*==============================================================*/
create  index relation_user_user_expense_fk on user_expenses (
user_id
);

/*==============================================================*/
/* Index: relation_expense_user_expense_f                       */
/*==============================================================*/
create  index relation_expense_user_expense_f on user_expenses (
expense_id
);

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role (
   role_id              int4                 not null,
   role_name            varchar(20)          not null,
   role_description     varchar(255)         null,
   constraint pk_user_role primary key (role_id)
);

comment on table user_role is
'Roles of users';

comment on column user_role.role_id is
'Role ID (role_id)';

comment on column user_role.role_name is
'Role name (role_name)';

comment on column user_role.role_description is
'Role description (role_description)';

/*==============================================================*/
/* Index: idx_role_name                                         */
/*==============================================================*/
create unique index idx_role_name on user_role (
role_name
);

alter table "user"
   add constraint fk_user_relation__user_rol foreign key (role_id)
      references user_role (role_id)
      on delete restrict on update restrict;

alter table user_expenses
   add constraint fk_user_exp_relation__expense foreign key (expense_id)
      references expense (expense_id)
      on delete restrict on update restrict;

alter table user_expenses
   add constraint fk_user_exp_relation__user foreign key (user_id)
      references "user" (user_id)
      on delete restrict on update restrict;

