# new-obj

## Database
click [here](https://www.db-fiddle.com/f/sLPGszXdgNTbjKNEhEsBtK/5) see example.

## user

```sql
create table newobj.user
(
    u_id    int           not null primary key,
    u_name  varchar(255)  null,
    u_pwd   varchar(255)  null,
    isAdmin boolean default false
);
```
| Field   | Type         | Null | Key | Default | Extra |
| ------- | ------------ | ---- | --- | ------- | ----- |
| u_id    | int(11)      | NO   | PRI |         |       |
| u_name  | varchar(255) | YES  |     |         |       |
| u_pwd   | varchar(255) | YES  |     |         |       |
| isAdmin | tinyint(1)   | YES  |     | 0       |       |

### accountbook

```sql
create table accountbook
(
    j_id  int auto_increment comment '记录编号',
    u_id  int          not null comment '用户id',
    szqk  varchar(255) not null comment '收支情况',
    money varchar(255) not null comment '金额',
    lx    varchar(255) not null comment '类型',
    bz    varchar(255) null comment '备注',
    date  datetime     null comment '记录日期',
    primary key (j_id, u_id)
);
```

| Field | Type         | Null | Key | Default | Extra          |
| ----- | ------------ | ---- | --- | ------- | -------------- |
| j_id  | int(11)      | NO   | PRI |         | auto_increment |
| u_id  | int(11)      | NO   | PRI |         |                |
| szqk  | varchar(255) | NO   |     |         |                |
| money | varchar(255) | NO   |     |         |                |
| lx    | varchar(255) | NO   |     |         |                |
| bz    | varchar(255) | YES  |     |         |                |
| date  | datetime     | YES  |     |         |                |