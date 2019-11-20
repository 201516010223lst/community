create table COMMENT
(
    ID           BIGINT auto_increment,
    PARENT_ID    BIGINT  not null,
    TYPE         INTEGER not null,
    COMMENTATOR  INTEGER not null,
    GMT_CREATE   BIGINT  not null,
    GMT_MODIFIED BIGINT  not null,
    LIKE_COUNT   BIGINT default 0,
    CONTENT      VARCHAR(1024),
    constraint COMMENT_PK
        primary key (ID)
);
