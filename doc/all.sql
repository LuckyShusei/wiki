drop table if exists `test`;
create table `test`(
                       `id` bigint not null comment 'ID',
                       `name` varchar(50)comment 'NAME',
                       `password` varchar(50)comment 'PASSWORD',
                       primary key (`ID`)
)engine = innodb default charset = utf8mb4 comment = 'test';

insert into`test`(id,name,password)values(1,'test','password');