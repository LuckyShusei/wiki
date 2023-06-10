drop table if exists `ebook`;
create table `ebook`(
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `category1_id` bigint comment 'category1',
    `category2_id` bigint comment 'category2',
    `description` varchar(200) comment 'description',
    `cover` varchar(200) comment 'cover',
    `doc_count` int comment '文档数',
    `view_count` int comment '阅读量',
    `vote_count` int comment '点赞数',
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