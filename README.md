# project_d7v2b_e5j1b_j1t2b
# Readme

To initialize Spring project, it requires an access to local MySql databse.
## Step1
In this project, we use database cpsc_304 which should be created manually.
## Step2
Give Spring application an access to Mysql
create user 'springuser'@'%' identified by 'ThePassword';
grant all on cpsc_304.* to 'springuser'@'%';
