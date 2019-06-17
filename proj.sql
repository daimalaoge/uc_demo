CREATE TABLE `ucm_mang_sys_codeset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `code_key` varchar(255) NOT NULL DEFAULT '' COMMENT '编码',
  `level` varchar(2) NOT NULL DEFAULT '' COMMENT '级别',
  `descp` varchar(500) NOT NULL DEFAULT '' COMMENT '说明',
  `upper_code` varchar(255) NOT NULL DEFAULT '' COMMENT '上级编码',
  `attr_value1` varchar(255) DEFAULT '',
  `attr_value2` varchar(255) DEFAULT '',
  `attr_value3` varchar(255) DEFAULT '',
  `attr_value4` varchar(255) DEFAULT '',
  `attr_value5` varchar(255) DEFAULT '',
  `attr_value6` varchar(255) DEFAULT '',
  `attr_value7` varchar(255) DEFAULT '',
  `attr_value8` varchar(255) DEFAULT '',
  `attr_value9` varchar(255) DEFAULT '',
  `attr_value10` varchar(255) DEFAULT '',
  `seq` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `codeset_gstatus` varchar(200) NOT NULL DEFAULT 'G_STATUS_USE',
  `create_datetime` datetime DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT '0',
  `update_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`,`code_key`) USING BTREE,
  UNIQUE KEY `code` (`code_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='系统设置表';

CREATE TABLE `ucm_mang_sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '' COMMENT '菜单名称',
  `upper_id` int(11) DEFAULT '0' COMMENT '上级菜单',
  `seq` int(11) DEFAULT '0' COMMENT '顺序',
  `url` varchar(500) DEFAULT '' COMMENT '跳转地址',
  `url_data` varchar(255) DEFAULT '',
  `icon` varchar(500) DEFAULT '' COMMENT '图标',
  `levels` tinyint(4) DEFAULT '0' COMMENT '级别 1-3',
  `codeset_menutype` varchar(200) DEFAULT '',
  `codeset_gstatus` varchar(200) DEFAULT '',
  `remarks` varchar(1000) DEFAULT '' COMMENT '备注',
  `create_datetime` datetime DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT '0',
  `update_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

CREATE TABLE `ucm_mang_sys_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT '',
  `title` varchar(255) DEFAULT NULL COMMENT '日志标题',
  `codeset_oplogtype` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `remote_addr` varchar(255) DEFAULT '' COMMENT '请求地址',
  `request_uri` varchar(500) DEFAULT '' COMMENT 'URI',
  `class_func` varchar(255) DEFAULT NULL COMMENT '运行路径',
  `method` varchar(255) DEFAULT NULL COMMENT '方法名',
  `params` varchar(1000) DEFAULT NULL COMMENT '提交参数',
  `exception` varchar(2000) DEFAULT NULL COMMENT '异常',
  `runtime` int(11) DEFAULT NULL COMMENT '运行时间',
  `result` varchar(255) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统操作日志表';

CREATE TABLE `ucm_mang_sys_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '',
  `code` varchar(255) DEFAULT '',
  `codeset_gstatus` varchar(200) DEFAULT 'G_STATUS_USE',
  `remarks` varchar(255) DEFAULT '',
  `create_user_id` int(11) DEFAULT '0',
  `update_user_id` int(11) DEFAULT '0',
  `create_datetime` datetime DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统组织结构表';


CREATE TABLE `ucm_mang_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '',
  `codeset_gstatus` varchar(200) DEFAULT 'G_STATUS_USE',
  `remarks` varchar(255) DEFAULT '',
  `create_user_id` int(11) DEFAULT '0',
  `update_user_id` int(11) DEFAULT '0',
  `create_datetime` datetime DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统角色表';

CREATE TABLE `ucm_mang_sys_role_menu_rs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5027 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

CREATE TABLE `ucm_mang_sys_user_role_rs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='系统用户-角色关系表';

CREATE TABLE `ucm_mang_user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT '0',
  `login_name` varchar(255) DEFAULT '',
  `login_email` varchar(255) DEFAULT '',
  `login_phone` varchar(255) DEFAULT '',
  `login_otherid` varchar(255) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `admin_yn` char(1) DEFAULT 'N' COMMENT 'Y-N',
  `codeset_gstatus` varchar(200) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT '0',
  `update_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='系统账户表';

CREATE TABLE `biz_demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) DEFAULT '0',
  `org_id` int(11) DEFAULT '0',
  `mingcheng` varchar(1000) DEFAULT NULL COMMENT '名称',
  `weiyidaima` varchar(1000) DEFAULT NULL COMMENT '唯一代码',
  `jine` decimal(11,4) DEFAULT '0.0000' COMMENT '金额',
  `fashengriqi` varchar(30) DEFAULT NULL COMMENT '发生日期',
  `zongrenshu` int(11) DEFAULT '0' COMMENT ' 总人数',
  `codeset_fenlei` varchar(255) DEFAULT NULL COMMENT '分类',
  `codeset_gstatus` varchar(200) DEFAULT '',
  `create_datetime` datetime DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT '0',
  `update_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='测试增删改样例';

INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (5, '系统设置', 'G_SYS_SET', '1', '', '', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (6, '通用状态', 'GSTATUS', '2', '', 'G_SYS_SET', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (7, '可用', 'G_STATUS_USE', '3', '', 'GSTATUS', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (8, '不可用', 'G_STATUS_NOUSE', '3', '', 'GSTATUS', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (9, '删除', 'G_STATUS_DEL', '3', '', 'GSTATUS', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (10, '未知', 'G_STATUS_UNKOWN', '3', '', 'GSTATUS', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (11, '系统操作日志', 'OP_LOG', '2', '', 'G_SYS_SET', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (12, '正常操作', 'OPLOG_INFO', '3', '', 'OP_LOG', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (13, '程序异常', 'OPLOG_ERROR', '3', '', 'OP_LOG', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (14, '菜单页面类型', 'MENU_TYPE', '2', '', 'G_SYS_SET', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2019-05-06 13:59:34', '2019-05-06 13:59:34', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (15, '菜单', 'MENU_TYPE_MENU', '3', '', 'MENU_TYPE', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2019-05-06 10:29:27', '2019-05-06 10:29:27', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (16, '操作', 'MENU_TYPE_ACTION', '3', '', 'MENU_TYPE', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2019-05-06 10:35:15', '2019-05-06 10:35:15', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (17, '页面', 'MENU_TYPE_PAGE', '3', '', 'MENU_TYPE', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2019-05-06 10:31:49', '2019-05-06 10:31:49', 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (230, '业务使用', 'BIZ_CODE', '1', '', '', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2019-06-17 13:57:40', '2019-06-17 13:57:40', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (231, '测试分类', 'BIZ_FENLEI', '2', '', 'BIZ_CODE', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2019-06-17 13:58:23', '2019-06-17 13:58:23', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (232, '人工处理', 'BIZ_FENLEI_HAND', '3', '', 'BIZ_FENLEI', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2019-06-17 13:58:50', '2019-06-17 13:58:50', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (233, '自动处理', 'BIZ_FENLEI_AUTO', '3', '', 'BIZ_FENLEI', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2019-06-17 13:59:02', '2019-06-17 13:59:02', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `upper_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (234, '不处理', 'BIZ_FENLEI_NO', '3', '', 'BIZ_FENLEI', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2019-06-17 13:59:13', '2019-06-17 13:59:13', 2, 2);

INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1000, '系统管理', 0, 100000000, '#', '#', '', 1, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', '2019-05-12 11:00:43', '2019-05-12 11:00:43', 0, 3);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1100, '权限管理', 1000, 0, '', '', '', 2, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1101, '人员管理', 1100, 0, '#/manager/sysd/account/list', '/manager/sysd/account/list', '', 3, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1102, '权限组管理', 1100, 0, '#/manager/sysd/role/list', '/manager/sysd/role/list', '', 3, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1103, '菜单管理', 1100, 0, '#/manager/sysd/menu/list', '/manager/sysd/menu/list', '', 3, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1104, '人员管理添加', 1101, 0, '#/manager/sysd/account/boaction/ADD', '/manager/sysd/account/boaction/ADD', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1105, '人员管理修改', 1101, 0, '#/manager/sysd/account/boaction/EDIT', '/manager/sysd/account/boaction/EDIT', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1106, '权限组管理添加', 1102, 0, '#/manager/sysd/role/boaction/ADD', '/manager/sysd/role/boaction/ADD', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1107, '权限组管理修改', 1102, 0, '#/manager/sysd/role/boaction/EDIT', '/manager/sysd/role/boaction/EDIT', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1108, '菜单管理添加', 1103, 0, '#/manager/sysd/menu/boaction/ADD', '/manager/sysd/menu/boaction/ADD', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1109, '菜单管理修改', 1103, 0, '#/manager/sysd/menu/boaction/EDIT', '/manager/sysd/menu/boaction/EDIT', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1110, '下级菜单管理', 1103, 0, '#/manager/sysd/menu/list2', '/manager/sysd/menu/list2', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1111, '权限组菜单设置', 1102, 0, '#/manager/sysd/role/menuset', '/manager/sysd/role/menuset', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', '2019-03-07 15:49:03', '2019-03-07 15:49:03', 0, 1);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1200, '系统监控', 1000, 0, '', '', '', 2, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1201, '日志查看', 1200, 0, '#/manager/sysd/oplog/list', '/manager/sysd/oplog/list', '', 3, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1300, '系统设置', 1000, 0, '#', ' #', '', 2, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', '2019-05-09 14:55:01', '2019-05-09 14:55:01', 0, 3);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1301, '系统代码管理', 1300, 0, '#/manager/sysd/setd/list', '/manager/sysd/setd/list', '', 3, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1302, '系统代码管理添加', 1301, 0, '#/manager/sysd/setd/boaction/ADD', '/manager/sysd/setd/boaction/ADD', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1303, '系统代码管理修改', 1301, 0, '#/manager/sysd/setd/boaction/EDIT', '/manager/sysd/setd/boaction/EDIT', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', NULL, NULL, 0, 0);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22306, '状态设置', 1101, 0, '#/manager/sysd/account/bo/status', '/manager/sysd/account/bo/status', '', 3, 'MENU_TYPE_ACTION', 'G_STATUS_USE', '', '2019-06-14 13:21:30', '2019-06-14 13:57:36', 3, 3);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22307, '删除', 1101, 0, '#/manager/sysd/account/bo/del', '/manager/sysd/account/bo/del', '', 3, 'MENU_TYPE_ACTION', 'G_STATUS_USE', '', '2019-06-14 13:21:55', '2019-06-14 13:21:55', 3, 3);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22308, '权限设置', 1101, 0, '#/manager/sysd/account/roleset', '/manager/sysd/account/roleset', '', 3, 'MENU_TYPE_ACTION', 'G_STATUS_USE', '', '2019-06-14 13:22:23', '2019-06-14 13:22:23', 3, 3);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22309, '状态设置', 1103, 0, '#/manager/sysd/menu/bo/status', '/manager/sysd/menu/bo/status', '', 3, 'MENU_TYPE_ACTION', 'G_STATUS_USE', '', '2019-06-14 13:33:45', '2019-06-14 13:33:45', 3, 3);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22310, '删除', 1103, 0, '#/manager/sysd/menu/bo/del', '/manager/sysd/menu/bo/del', '', 3, 'MENU_TYPE_ACTION', 'G_STATUS_USE', '', '2019-06-14 13:34:01', '2019-06-14 13:34:01', 3, 3);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22311, '状态设置', 1102, 0, '#/manager/sysd/role/bo/status', '/manager/sysd/role/bo/status', '', 3, 'MENU_TYPE_ACTION', 'G_STATUS_USE', '', '2019-06-14 14:30:47', '2019-06-14 14:30:47', 3, 3);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22312, '删除', 1102, 0, '#/manager/sysd/role/bo/del', '/manager/sysd/role/bo/del', '', 3, 'MENU_TYPE_ACTION', 'G_STATUS_USE', '', '2019-06-14 14:31:06', '2019-06-14 14:31:06', 3, 3);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22313, '设置状态', 1301, 0, '#/manager/sysd/setd/bo/status', '/manager/sysd/setd/bo/status', '', 3, 'MENU_TYPE_ACTION', 'G_STATUS_USE', '', '2019-06-14 15:10:34', '2019-06-14 15:10:34', 3, 3);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22331, '示例一级菜单', 0, 0, '#/', '/', '', 1, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', '2019-06-17 13:16:49', '2019-06-17 13:16:49', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22332, '示例二级菜单', 22331, 0, '#/', '/', '', 2, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', '2019-06-17 13:17:06', '2019-06-17 13:17:06', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22333, 'Demo示例', 22332, 0, '#/biz/ceshi/demo/list', '/biz/ceshi/demo/list', '', 3, 'MENU_TYPE_MENU', 'G_STATUS_USE', '', '2019-06-17 13:18:21', '2019-06-17 13:18:21', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22334, 'Demo新增', 22333, 0, '#/biz/ceshi/demo/boaction/ADD', '/biz/ceshi/demo/boaction/ADD', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', '2019-06-17 13:46:08', '2019-06-17 13:46:08', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22335, 'Demo编辑', 22333, 0, '#/biz/ceshi/demo/boaction/EDIT', '/biz/ceshi/demo/boaction/EDIT', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', '2019-06-17 13:46:22', '2019-06-17 13:46:22', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22336, '设置状态', 22333, 0, '#/biz/ceshi/demo/bo/status', '/biz/ceshi/demo/bo/status', '', 3, 'MENU_TYPE_ACTION', 'G_STATUS_USE', '', '2019-06-17 13:46:47', '2019-06-17 13:46:47', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22337, '删除', 22333, 0, '#/biz/ceshi/demo/bo/del', '/biz/ceshi/demo/bo/del', '', 3, 'MENU_TYPE_ACTION', 'G_STATUS_USE', '', '2019-06-17 13:47:08', '2019-06-17 13:47:08', 2, 2);
INSERT INTO `uc_demo`.`ucm_mang_sys_menu`(`id`, `name`, `upper_id`, `seq`, `url`, `url_data`, `icon`, `levels`, `codeset_menutype`, `codeset_gstatus`, `remarks`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (22338, '详情查看', 22333, 0, '#/biz/ceshi/demo/infor', '/biz/ceshi/demo/infor', '', 3, 'MENU_TYPE_PAGE', 'G_STATUS_USE', '', '2019-06-17 14:28:10', '2019-06-17 14:28:10', 2, 2);


INSERT INTO `uc_demo`.`ucm_mang_sys_org`(`id`, `name`, `code`, `codeset_gstatus`, `remarks`, `create_user_id`, `update_user_id`, `create_datetime`, `update_datetime`) VALUES (1, '系统平台', 'DEFORG', 'G_STATUS_USE', '', 1, 1, NULL, NULL);


INSERT INTO `uc_demo`.`ucm_mang_user_account`(`id`, `org_id`, `login_name`, `login_email`, `login_phone`, `login_otherid`, `password`, `admin_yn`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1, 1, 'manager', '', '', '', '', 'N', 'G_STATUS_USE_NODISPLAY', NULL, NULL, 1, 1);
INSERT INTO `uc_demo`.`ucm_mang_user_account`(`id`, `org_id`, `login_name`, `login_email`, `login_phone`, `login_otherid`, `password`, `admin_yn`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (2, 1, 'admin', 'admin@uc.com', '13333333333', '', 'Ev7P89i9X831Dv1B1iPB7v3E9oc89!7!', 'Y', 'G_STATUS_USE', '2019-05-04 22:07:10', '2019-05-09 13:36:07', 1, 1);
