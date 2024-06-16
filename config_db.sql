--initORCL.ora

db_name=orcl
control_files=(C:\oraclexe\app\oracle\oradata\orcl\control01.ctl, C:\oraclexe\app\oracle\oradata\orcl\control02.ctl)
memory_target=500M
processes=150
audit_file_dest=%ORACLE_HOME%\admin\orcl\adump
audit_trail=db
db_block_size=8192
db_domain=''
db_recovery_file_dest=%ORACLE_HOME%\flash_recovery_area
db_recovery_file_dest_size=2G
diagnostic_dest=%ORACLE_HOME%
open_cursors=300
undo_tablespace=undotbs1



orapwd file=%ORACLE_HOME%\database\PWDorcl.ora password=123456 entries=10



mkdir C:\oraclexe\app\oracle\oradata\orcl
mkdir C:\oraclexe\app\oracle\flash_recovery_area
mkdir C:\oraclexe\app\oracle\admin\orcl
mkdir C:\oraclexe\app\oracle\admin\orcl\adump
mkdir C:\oraclexe\app\oracle\admin\orcl\dpdump
mkdir C:\oraclexe\app\oracle\admin\orcl\pfile





sqlplus /nolog



startup nomount pfile='C:\oraclexe\app\oracle\product\11.2.0\server\database\initORCL.ora';

CREATE DATABASE orcl
   USER SYS IDENTIFIED BY 123456
   USER SYSTEM IDENTIFIED BY 123456
   LOGFILE GROUP 1 ('C:\oraclexe\app\oracle\oradata\orcl\redo01.log') SIZE 50M,
           GROUP 2 ('C:\oraclexe\app\oracle\oradata\orcl\redo02.log') SIZE 50M,
           GROUP 3 ('C:\oraclexe\app\oracle\oradata\orcl\redo03.log') SIZE 50M
   MAXLOGFILES 32
   MAXLOGMEMBERS 5
   MAXLOGHISTORY 1
   MAXDATAFILES 100
   CHARACTER SET AL32UTF8
   NATIONAL CHARACTER SET AL16UTF16
   EXTENT MANAGEMENT LOCAL
   DATAFILE 'C:\oraclexe\app\oracle\oradata\orcl\system01.dbf' SIZE 700M REUSE
   SYSAUX DATAFILE 'C:\oraclexe\app\oracle\oradata\orcl\sysaux01.dbf' SIZE 550M REUSE
   DEFAULT TABLESPACE users
      DATAFILE 'C:\oraclexe\app\oracle\oradata\orcl\users01.dbf'
      SIZE 200M REUSE AUTOEXTEND ON MAXSIZE UNLIMITED
   DEFAULT TEMPORARY TABLESPACE temp
      TEMPFILE 'C:\oraclexe\app\oracle\oradata\orcl\temp01.dbf'
      SIZE 20M REUSE
   UNDO TABLESPACE undotbs1
      DATAFILE 'C:\oraclexe\app\oracle\oradata\orcl\undotbs01.dbf'
      SIZE 200M REUSE AUTOEXTEND ON MAXSIZE UNLIMITED;




@C:\oraclexe\app\oracle\product\11.2.0\server\rdbms\admin\catalog.sql
@C:\oraclexe\app\oracle\product\11.2.0\server\rdbms\admin\catproc.sql

@C:\oraclexe\app\oracle\product\11.2.0\server\sqlplus\admin\pupbld.sql




CREATE SPFILE FROM PFILE='C:\oraclexe\app\oracle\product\11.2.0\server\database\initORCL.ora';

ALTER DATABASE OPEN RESETLOGS;


exit;










oradim -new -sid ORCL -intpwd 123456 -startmode auto -pfile C:\oraclexe\app\oracle\product\11.2.0\server\database\initORCL.ora

net start OracleServiceORCL
lsnrctl start


sqlplus

STARTUP





CREATE USER manager1 IDENTIFIED BY 123456;
ALTER USER manager1 QUOTA 50M ON USERS;
CREATE USER manager2 IDENTIFIED BY 123456;
ALTER USER manager2 QUOTA 50M ON USERS;
CREATE USER manager3 IDENTIFIED BY 123456;
ALTER USER manager3 QUOTA 50M ON USERS;

CREATE ROLE manager_role;
GRANT CREATE SESSION TO manager_role;
GRANT CREATE TABLE TO manager_role;
GRANT CREATE SEQUENCE TO manager_role;
GRANT INSERT ANY TABLE TO manager_role;
GRANT UPDATE ANY TABLE TO manager_role;
GRANT SELECT ANY TABLE TO manager_role;
GRANT DELETE ANY TABLE TO manager_role;

GRANT manager_role TO manager1;
GRANT manager_role TO manager2;
GRANT manager_role TO manager3;




GRANT CREATE PUBLIC DATABASE LINK TO manager_role;
GRANT CREATE SYNONYM TO manager_role;


CREATE PUBLIC DATABASE LINK manager1TOmanager2 CONNECT TO manager2 IDENTIFIED BY "123456" USING 'orcl';
CREATE PUBLIC DATABASE LINK manager1TOmanager3 CONNECT TO manager3 IDENTIFIED BY "123456" USING 'orcl';
CREATE SYNONYM Dep FOR manager1.Departement@manager2TOmanager1;
CREATE SYNONYM Emp FOR manager1.Employe@manager2TOmanager1;
CREATE SYNONYM Mach FOR manager1.Machine@manager2TOmanager1;
CREATE SYNONYM Pst FOR manager1.Post@manager2TOmanager1;
CREATE SYNONYM Sal FOR manager1.Salaire@manager2TOmanager1;

CREATE SYNONYM Hot FOR manager2.Hotel@manager1TOmanager2;
CREATE SYNONYM Dir FOR manager2.Directeur@manager1TOmanager2;
CREATE SYNONYM Ser FOR manager2.Service@manager1TOmanager2;

CREATE SYNONYM Cha FOR manager3.Chambre@manager1TOmanager3;
CREATE SYNONYM Per FOR manager3.Personne@manager1TOmanager3;



CREATE PUBLIC DATABASE LINK manager2TOmanager1 CONNECT TO manager1 IDENTIFIED BY "123456" USING 'orcl';
CREATE PUBLIC DATABASE LINK manager2TOmanager3 CONNECT TO manager3 IDENTIFIED BY "123456" USING 'orcl';
CREATE SYNONYM Dep FOR manager1.Departement@manager2TOmanager1;
CREATE SYNONYM Emp FOR manager1.Employe@manager2TOmanager1;
CREATE SYNONYM Mach FOR manager1.Machine@manager2TOmanager1;
CREATE SYNONYM Pst FOR manager1.Post@manager2TOmanager1;
CREATE SYNONYM Sal FOR manager1.Salaire@manager2TOmanager1;

CREATE SYNONYM Cha FOR manager3.Chambre@manager2TOmanager3;
CREATE SYNONYM Per FOR manager3.Personne@manager2TOmanager3;



CREATE PUBLIC DATABASE LINK manager3TOmanager1 CONNECT TO manager1 IDENTIFIED BY "123456" USING 'orcl';
CREATE PUBLIC DATABASE LINK manager3TOmanager2 CONNECT TO manager2 IDENTIFIED BY "123456" USING 'orcl';
CREATE SYNONYM Dep FOR manager1.Departement@manager2TOmanager1;
CREATE SYNONYM Emp FOR manager1.Employe@manager2TOmanager1;
CREATE SYNONYM Mach FOR manager1.Machine@manager2TOmanager1;
CREATE SYNONYM Pst FOR manager1.Post@manager2TOmanager1;
CREATE SYNONYM Sal FOR manager1.Salaire@manager2TOmanager1;

CREATE SYNONYM Hot FOR manager2.Hotel@manager3TOmanager2;
CREATE SYNONYM Dir FOR manager2.Directeur@manager3TOmanager2;
CREATE SYNONYM Ser FOR manager2.Service@manager3TOmanager2;