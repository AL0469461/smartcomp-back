IF NOT EXISTS (SELECT * FROM sys.databases WHERE name= 'smartcomp_db')
BEGIN
 CREATE DATABASE smartcomp_db;
END;
GO
USE smartcomp_db;
GO
