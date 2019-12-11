create table COMMENT
(
    ID           BIGINT auto_increment,
    PARENT_ID    BIGINT,
    TYPE         INTEGER,
    COMMENTATOR  INTEGER,
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    LIKE_COUNT   BIGINT default 0,
    CONTENT      VARCHAR(1024),
    constraint COMMENT_PK
        primary key (ID)
);
