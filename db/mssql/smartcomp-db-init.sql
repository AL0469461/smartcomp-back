USE [master]
GO

IF DB_ID('smartcomp_db') IS NOT NULL
    set noexec on
-- prevent creation when already exists

CREATE DATABASE [smartcomp_db];
GO

USE [smartcomp_db]
GO