/*=============================
 * tabble auth_user 用户表
 * 一个用户 只能用一个部门，一个部门有多个用户；
 * 一个用户可有多个角色，一个角色下有多个用户； * 
 * =============================
 */

DROP TABLE IF EXISTS auth_user;
create table auth_user
(
   id                 bigint(20) NOT NULL AUTO_INCREMENT comment '主键',
   user_name          varchar(30) comment '用户名',
   real_name          varchar(30) comment '用户真实姓名',
   password           varchar(64) comment '加密后的密码',
   salt               varchar(30) comment '密码加密的盐',
   mobile             varchar(12) comment '手机',
   email              varchar(30) comment '邮箱',
   qq                 varchar(30) comment 'QQ',
   status             char(1) default 1 comment '是否有效[1有效,0无效]',
   dept_id            bigint(20)  comment '部门ID',
   create_date        timestamp default current_timestamp comment '创建时间',
   update_date        datetime default NULL comment '最后一次更新时间',
   primary key (id),
   UNIQUE key unique_user_name (user_name), -- # 用户名唯一性
   INDEX index_user_name (user_name) ,
   INDEX index_password (password) ,
   INDEX index_dept_id (dept_id) ,
   INDEX index_create_date (create_date) 
)ENGINE = InnoDB DEFAULT CHARSET=utf8 comment '用户表';



/*=============================
 * tabble auth_rule 角色表
 * 一个用户可有多个角色，一个角色下有多个用户；
 * =============================
 */

DROP TABLE IF EXISTS auth_rule;
create table auth_rule
(
   id                 bigint(20) NOT NULL AUTO_INCREMENT comment '主键',
   rule_name          varchar(30) comment '中文角色名',
   en_name            varchar(30) comment '英文角色名',
   status             char(1) default 1 comment '是否有效[1有效,0无效]',
   create_date        timestamp default current_timestamp comment '创建时间',
   update_date        datetime default NULL comment '最后一次更新时间',
   primary key (id),
   UNIQUE key unique_en_name (en_name), -- # 角色名唯一性
   INDEX index_rule_name (rule_name) ,
   INDEX index_create_date (create_date) 
)ENGINE = InnoDB DEFAULT CHARSET=utf8 comment '角色表';



/*=============================
 * tabble auth_user_rule 用户角色关系表
 * 一个用户可有多个角色，一个角色下有多个用户；
 * =============================
 */

DROP TABLE IF EXISTS auth_user_rule;
create table auth_user_rule
(
   id                 bigint(20) NOT NULL AUTO_INCREMENT comment '主键',
   user_id            bingint(20) not null comment '用户ID',   
   rule_id            bingint(20) comment '角色ID',
   create_date        timestamp default current_timestamp comment '创建时间',
   update_date        datetime default NULL comment '最后一次更新时间',
   primary key (id),
   INDEX index_user_id (user_id) ,
   INDEX index_rule_id (rule_id) ,
   INDEX index_create_date (create_date) 
)ENGINE = InnoDB DEFAULT CHARSET=utf8 comment '用户角色关系表';


/*=============================
 * tabble auth_function 系统功能表
 * 一个角色下多个系统功能操作权限，一个操作可以有多个角色；
 * =============================
 */

DROP TABLE IF EXISTS auth_function;
create table auth_function
(
   id                 bigint(20) NOT NULL AUTO_INCREMENT comment '主键',
   function_module    varchar(50) comment '功能模块',
   function_type      varchar(20) comment '功能类型[save-保存/新增;view;edit-编辑/修改;delete-删除;submit-提交;return-返回;export-导出;config-配置]',
   function_url       varchar(100) comment '功能url',
   function_desc      varchar(255) comment '功能描述',
   status             char(1) default 1 comment '是否有效[1有效,0无效]',
   create_date        timestamp default current_timestamp comment '创建时间',
   update_date        datetime default NULL comment '最后一次更新时间',
   primary key (id),
   UNIQUE key unique_function_type_url (function_module,function_type,function_url),
   INDEX index_create_date (create_date) 
)ENGINE = InnoDB DEFAULT CHARSET=utf8 comment '用户角色关系表';

/*=============================
 * tabble auth_user_rule 用户角色关系表
 * 一个用户可有多个角色，一个角色下有多个用户；
 * =============================
 */

DROP TABLE IF EXISTS auth_rule_permission;
create table auth_rule_permission
(
   id                 bigint(20) NOT NULL AUTO_INCREMENT comment '主键',
   rule_id            bingint(20) comment '角色ID',
   function_id        bingint(20) comment '功能ID',
   create_date        timestamp default current_timestamp comment '创建时间',
   update_date        datetime default NULL comment '最后一次更新时间',
   primary key (id),
   INDEX index_permission_rule_id (rule_id) ,
   INDEX index_function_id (function_id) ,
   INDEX index_create_date (create_date) 
)ENGINE = InnoDB DEFAULT CHARSET=utf8 comment '用户角色关系表';





/*=============================
 * tabble dept 用户表
 * 一个用户 只能用一个部门，一个部门有多个用户；
 * 一个用户可有多个角色，一个角色下有多个用户； * 
 * =============================
 */

DROP TABLE IF EXISTS auth_dept;
create table auth_dept
(
   id                 bigint(20) NOT NULL AUTO_INCREMENT comment '主键',
   dept_name          varchar(30) comment '部门名称',
   parent_dept_id     bigint(20) comment '上级部门ID',
   status             char(1) default 1 comment '状态 [1有效,0无效]',
   flag               char(1) default 0 comment '是否是一级部门[1是-无parent_dept_id,0不是]',
   create_date        timestamp default current_timestamp comment '创建时间',
   update_date        datetime default NULL comment '最后一次更新时间',
   primary key (id),
   UNIQUE key unique_dept_name (dept_name), -- # 部门唯一性
   INDEX index_dept_name (dept_name) ,
   INDEX index_create_date (create_date) 
)ENGINE = InnoDB DEFAULT CHARSET=utf8 comment '部门表';


