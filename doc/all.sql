# 分类
drop table if exists `category`;
create table `category`
(
    `id`     bigint      not null comment 'ID',
    `parent` bigint      not null default 0 comment 'Parent ID',
    `name`   varchar(50) not null comment 'Name',
    `sort`   int comment 'Sort',
    primary key (`id`)
)engine=innodb default  charset = utf8mb4 comment ='category';

insert into `category`(id,parent,name,sort)values(100,000,'前端开发',100);
insert into `category`(id,parent,name,sort)values(101,100,'Vue',101);
insert into `category`(id,parent,name,sort)values(102,100,'HTML&CSS',102);
insert into `category`(id,parent,name,sort)values(200,000,'JAVA',200);
insert into `category`(id,parent,name,sort)values(201,200,'基础应用',201);
insert into `category`(id,parent,name,sort)values(202,200,'框架应用',202);
insert into `category`(id,parent,name,sort)values(300,000,'PYTHON',300);
insert into `category`(id,parent,name,sort)values(301,300,'基础方法应用',301);
insert into `category`(id,parent,name,sort)values(302,300,'基础应用',302);
insert into `category`(id,parent,name,sort)values(400,000,'数据库',400);
insert into `category`(id,parent,name,sort)values(401,400,'MySql',401);
insert into `category`(id,parent,name,sort)values(500,000,'其他',500);




drop table if exists `ebook`;
create table `ebook`(
    `id` bigint not null comment 'id',
    `name` varchar(50) comment 'name',
    `category1Id` bigint comment 'category1Id',
    `category2Id` bigint comment 'category2Id',
    `description` varchar(200) comment 'Description',
    `cover` varchar(200) comment 'Cover',
    `doc_count` int comment 'Documents',
    `view_count` int comment 'Views',
    `vote_count` int comment 'Likes',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='ebook';
insert into `ebook`(id,name,description)values(1,'Spring boot入门','java开发');
insert into `ebook`(id, name,description)values(2,'Vue入门','零基础入门Vue开发');
insert into `ebook`(id,name,description)values (3,'Python入门','零基础入门Python');
insert into `ebook`(id,name,description)values(4,'MySQL入门','零基础入门Mysql开发');
insert into `ebook`(id,name,description)values(5,'Oracle入门教程','零基础入门Oracle开发');

drop table if exists `test`;
create table `test`(
                       `id` bigint not null comment 'ID',
                       `name` varchar(50)comment 'NAME',
                       `password` varchar(50)comment 'PASSWORD',
                       primary key (`ID`)
)engine = innodb default charset = utf8mb4 comment = 'test';
insert into`test`(id,name,password)values(1,'test','password');

drop table if exists `demo`;
create table `demo`(
                       `id` bigint not null comment 'ID',
                       `name` varchar(50)comment 'NAME',
                       primary key (`ID`)
)engine = innodb default charset = utf8mb4 comment = 'test';
insert into`demo`(id,name)values(1,'test');