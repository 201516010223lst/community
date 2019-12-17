create table comment
(
    id bigint auto_increment primary key,
    parent_id bigint ,
    type int ,
    commentator int ,
    gmt_create bigint ,
    gmt_modified bigint ,
    like_count bigint default 0
);