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
