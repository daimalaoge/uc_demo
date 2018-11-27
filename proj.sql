CREATE TABLE `ucm_demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `provence` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `point` int(11) DEFAULT '0' COMMENT '积分',
  `deposit` decimal(11,4) DEFAULT '0.0000' COMMENT '存款',
  `codeset_sex` varchar(20) DEFAULT NULL,
  `codeset_gstatus` varchar(20) DEFAULT '0',
  `create_datetime` datetime DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT '0',
  `update_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

CREATE TABLE `ucm_sys_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8 COMMENT='系统操作日志表';

CREATE TABLE `ucm_sys_codeset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `code_key` varchar(30) NOT NULL DEFAULT '' COMMENT '编码',
  `level` varchar(2) NOT NULL DEFAULT '' COMMENT '级别',
  `descp` varchar(500) NOT NULL DEFAULT '' COMMENT '说明',
  `p_code` varchar(30) NOT NULL DEFAULT '' COMMENT '上级编码',
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
  `seq` tinyint(4) NOT NULL DEFAULT '0' COMMENT '顺序',
  `codeset_gstatus` varchar(20) NOT NULL DEFAULT 'G_STATUS_USE',
  `create_datetime` datetime DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT '0',
  `update_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `code` (`code_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统设置表';

CREATE TABLE `ucm_sys_role_menu_rs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='系统权限表';

CREATE TABLE `ucm_sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '' COMMENT '菜单名称',
  `p_id` int(11) DEFAULT '0' COMMENT '上级菜单',
  `seq` tinyint(4) DEFAULT '0' COMMENT '顺序',
  `url` varchar(500) DEFAULT '' COMMENT '跳转地址',
  `url_data` varchar(255) DEFAULT NULL,
  `icon` varchar(500) DEFAULT '' COMMENT '图标',
  `level` tinyint(4) DEFAULT '0' COMMENT '级别 1-3',
  `codeset_menutype` varchar(20) DEFAULT '',
  `codeset_gstatus` varchar(20) DEFAULT '',
  `remarks` varchar(1000) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统菜单表';

CREATE TABLE `ucm_user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT '',
  `login_email` varchar(255) DEFAULT '',
  `login_phone` varchar(255) DEFAULT '',
  `login_otherid` varchar(255) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `codeset_gstatus` varchar(20) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `update_datetime` datetime DEFAULT NULL,
  `create_user_id` int(11) DEFAULT '0',
  `update_user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统账户表';

CREATE TABLE `ucm_user_role_rs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户-角色关系表';

CREATE TABLE `ucm_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '',
  `codeset_gstatus` varchar(20) DEFAULT 'G_STATUS_USE',
  `remarks` varchar(255) DEFAULT '',
  `create_user_id` int(11) DEFAULT '0',
  `update_user_id` int(11) DEFAULT '0',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1, '性别', 'SEX', '1', '', '', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-08 11:07:18', '2018-11-08 11:07:18', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (2, '男', 'MAN', '2', '', 'SEX', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-08 11:07:18', '2018-11-08 11:07:18', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (3, '女', 'FEMALE', '2', '', 'SEX', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-08 11:07:18', '2018-11-08 11:07:18', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (4, '未知', 'UNKOWN', '2', '', 'SEX', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-08 11:07:18', '2018-11-08 11:07:18', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (5, '系统设置', 'G_SYS_SET', '1', '', '', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (6, '通用状态', 'GSTATUS', '2', '', 'G_SYS_SET', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (7, '可用', 'G_STATUS_USE', '3', '', 'GSTATUS', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (8, '不可用', 'G_STATUS_NOUSE', '3', '', 'GSTATUS', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (9, '删除', 'G_STATUS_DEL', '3', '', 'GSTATUS', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (10, '未知', 'G_STATUS_UNKOWN', '3', '', 'GSTATUS', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (11, '系统操作日志', 'OP_LOG', '2', '', 'G_SYS_SET', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (12, '正常操作', 'OPLOG_INFO', '3', '', 'OP_LOG', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (13, '程序异常', 'OPLOG_ERROR', '3', '', 'OP_LOG', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (14, '菜单类型', 'MENU_TYPE', '2', '', 'G_SYS_SET', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (15, '菜单页面', 'MENU_TYPE_PAGE', '3', '', 'MENU_TYPE', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (16, '按键', 'MENU_TYPE_BUTTOM', '3', '', 'MENU_TYPE', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', '2018-11-20 17:18:05', '2018-11-20 17:18:05', 0, 0);
INSERT INTO `uc_main_demo`.`ucm_sys_codeset`(`id`, `name`, `code_key`, `level`, `descp`, `p_code`, `attr_value1`, `attr_value2`, `attr_value3`, `attr_value4`, `attr_value5`, `attr_value6`, `attr_value7`, `attr_value8`, `attr_value9`, `attr_value10`, `seq`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (17, '动作页面', 'MENU_TYPE_APAGE', '3', '', 'MENU_TYPE', '', '', '', '', '', '', '', '', '', '', 0, 'G_STATUS_USE', NULL, NULL, 0, 0);

INSERT INTO `uc_main_demo`.`ucm_user_account`(`id`, `login_name`, `login_email`, `login_phone`, `login_otherid`, `password`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (0, 'SYSTEM', '', '', '', '', NULL, NULL, NULL, 0, 0);
INSERT INTO `uc_main_demo`.`ucm_user_account`(`id`, `login_name`, `login_email`, `login_phone`, `login_otherid`, `password`, `codeset_gstatus`, `create_datetime`, `update_datetime`, `create_user_id`, `update_user_id`) VALUES (1, 'admin', '', '', '', 'Ev7P89i9X831Dv1B1iPB7v3E9oc89!7!', 'G_STATUS_USE', '2018-11-21 16:09:04', '2018-11-21 16:09:08', 0, 0);
