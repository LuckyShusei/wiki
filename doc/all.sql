#用户表
drop table if exists `user`;
create table `user`
(
    `id`  bigint not null comment 'ID',
    `login_name` varchar(50) not null comment 'login_name',
    `name` varchar(50) not null comment 'nickname',
    `password` char(32) not null comment 'password',
    primary key (`id`),
    unique key `login_name_unique` (`login_name`)
)engine=innodb default charset=utf8mb4 comment='user';
insert into `user`(id,`login_name`,`name`,`password`)values(1,'test','test','test');
#内容表
drop table if exists `content`;
create table `content`
(
    `id`      bigint     not null comment 'Doc id',
    `content` mediumtext not null comment 'Content',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='Doc Content';

# 文档表
drop table if exists `doc`;
create table `doc`(
    `id` bigint not null comment 'id',
    `ebook_id` bigint not null default 0 comment 'ebookId',
    `parent` bigint not null default 0 comment 'Parent ID',
    `name` varchar(50) not null comment 'Name',
    `sort` int comment 'Sort',
    `view_count` int default 0 comment 'ViewCount',
    `vote_count` int default 0 comment 'VoteCount',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='Documents';

insert into `doc`(id,ebook_id,parent,name,sort,view_count,vote_count) values (1,1,0,'Document1',1,0,0);
insert into `doc`(id,ebook_id,parent,name,sort,view_count,vote_count) values (2,1,1,'Document1.1',1,0,0);
insert into `doc`(id,ebook_id,parent,name,sort,view_count,vote_count) values (3,1,0,'Document2',2,0,0);
insert into `doc`(id,ebook_id,parent,name,sort,view_count,vote_count) values (4,1,3,'Document2.1',1,0,0);
insert into `doc`(id,ebook_id,parent,name,sort,view_count,vote_count) values (5,1,3,'Document2.2',2,0,0);
insert into `doc`(id,ebook_id,parent,name,sort,view_count,vote_count) values (6,1,5,'Document2.2.1',1,0,0);




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

insert into `category`(id,parent,name,sort)values(100,000,'Front-end',100);
insert into `category`(id,parent,name,sort)values(101,100,'Vue',101);
insert into `category`(id,parent,name,sort)values(102,100,'HTML&CSS',102);
insert into `category`(id,parent,name,sort)values(200,000,'JAVA',200);
insert into `category`(id,parent,name,sort)values(201,200,'Basic Application',201);
insert into `category`(id,parent,name,sort)values(202,200,'Framework Application',202);
insert into `category`(id,parent,name,sort)values(300,000,'Others',300);
insert into `category`(id,parent,name,sort)values(301,300,'PYTHON',301);
insert into `category`(id,parent,name,sort)values(302,300,'Spring Boot',302);
insert into `category`(id,parent,name,sort)values(400,000,'DataBase',400);
insert into `category`(id,parent,name,sort)values(401,400,'MySql',401);
insert into `category`(id,parent,name,sort)values(500,000,'日本語',500);




drop table if exists `ebook`;
create table `ebook`(
    `id` bigint not null comment 'id',
    `name` varchar(50) comment 'name',
    `category1_id` bigint comment 'category1Id',
    `category2_id` bigint comment 'category2Id',
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