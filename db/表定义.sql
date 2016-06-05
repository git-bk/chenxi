CREATE TABLE `dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `value` varchar(128) DEFAULT NULL COMMENT '数据',
  `dic_key` varchar(64) DEFAULT NULL COMMENT '数据类型',
  `dic_order` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改者',
  `is_system` varchar(1) DEFAULT NULL COMMENT '是否系统变量，是否可修改、删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='数据字典';

CREATE TABLE `dictionary_setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `key_type` varchar(64) DEFAULT NULL COMMENT '数据字典类型',
  `key_type_name` varchar(128) DEFAULT NULL COMMENT '数据字典类型名称',
  `is_system` char(1) DEFAULT NULL COMMENT '是否可添加',
  `is_sort` char(1) DEFAULT NULL COMMENT '是否排序',
  `control_type` varchar(1) DEFAULT NULL COMMENT '控件类型',
  `has_child` char(1) DEFAULT NULL COMMENT '是否有儿子',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改者',
  `is_deleted` varchar(1) DEFAULT 'n' COMMENT '是否删除',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点id',
  `value_min_length` int(11) DEFAULT NULL COMMENT '字典值的最小长度限制',
  `value_max_length` int(11) DEFAULT NULL COMMENT '字典值的最大长度限制',
  `callback_url` varchar(255) DEFAULT NULL COMMENT '数据增删改操作回调方法',
  PRIMARY KEY (`id`),
  KEY `idx_dictionary_setting_key_type` (`key_type`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='数据字典设计表';

CREATE TABLE `entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `name` varchar(45) DEFAULT NULL COMMENT '表单名称',
  `entity_key` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `permission` varchar(200) DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  `app_id` int(11) DEFAULT NULL COMMENT '应用id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

CREATE TABLE `field` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `name` varchar(45) DEFAULT NULL COMMENT '字段名称',
  `field_type` int(11) DEFAULT NULL COMMENT '字段类型',
  `is_required` char(1) DEFAULT NULL COMMENT '是否必填',
  `field_order` int(11) DEFAULT NULL COMMENT '字段排序',
  `min_value` bigint(20) DEFAULT NULL COMMENT '最小值或最小字符',
  `max_value` bigint(20) DEFAULT NULL COMMENT '最大值或最大字符',
  `entity_id` int(11) NOT NULL COMMENT '关联实体id',
  `default_value` varchar(200) DEFAULT NULL,
  `column_name` varchar(20) DEFAULT NULL,
  `dic_setting_id` int(11) DEFAULT NULL COMMENT '数据字典id（附件、枚举类型会用到，标识该field是一个）',
  `field_key` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `permission` varchar(200) DEFAULT NULL,
  `is_hidden` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_field_form_idx` (`entity_id`),
  CONSTRAINT `fk_field_form` FOREIGN KEY (`entity_id`) REFERENCES `entity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `field_data_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `name` varchar(45) DEFAULT NULL COMMENT '表单名称',
  `description` varchar(200) DEFAULT NULL,
  `start_index` int(3) DEFAULT NULL COMMENT '起始列编号',
  `end_index` int(3) DEFAULT NULL COMMENT '终止列编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

CREATE TABLE `field_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `name` varchar(45) DEFAULT NULL COMMENT '表单名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `field_data_type_id` int(11) DEFAULT NULL COMMENT '数据类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(45) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(255) DEFAULT NULL COMMENT 'URL',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `associate_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `is_deleted` varchar(1) DEFAULT 'n' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=977 DEFAULT CHARSET=utf8 COMMENT='附件表';

CREATE TABLE `i_associ` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `node` int(11) DEFAULT NULL,
  `node2` int(11) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `type2` varchar(100) DEFAULT NULL,
  `associ_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `i_depend` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `parent` int(11) DEFAULT NULL,
  `child` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `associate_id` int(11) DEFAULT NULL COMMENT '关联ID',
  `original_url` varchar(255) DEFAULT NULL COMMENT '原图URL',
  `thumbnail_url` varchar(255) DEFAULT NULL COMMENT '缩略图URL',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `my_order` int(11) DEFAULT NULL COMMENT '排序',
  `mobile_url` varchar(255) DEFAULT NULL COMMENT '移动端url',
  PRIMARY KEY (`id`),
  KEY `idx_image_project_idx` (`associate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4048 DEFAULT CHARSET=utf8 COMMENT='图片表';

CREATE TABLE `instance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` char(1) NOT NULL COMMENT '是否删除',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(20) DEFAULT NULL COMMENT '修改人',
  `entity_id` int(11) DEFAULT NULL COMMENT '实体id',
  `entity_key` varchar(45) DEFAULT NULL COMMENT '实体key',
  `column1` varchar(10) DEFAULT NULL COMMENT 'column1',
  `column2` varchar(10) DEFAULT NULL COMMENT 'column2',
  `column3` varchar(10) DEFAULT NULL COMMENT 'column3',
  `column4` varchar(10) DEFAULT NULL COMMENT 'column4',
  `column5` varchar(10) DEFAULT NULL COMMENT 'column5',
  `column6` varchar(10) DEFAULT NULL COMMENT 'column6',
  `column7` varchar(10) DEFAULT NULL COMMENT 'column7',
  `column8` varchar(10) DEFAULT NULL COMMENT 'column8',
  `column9` varchar(10) DEFAULT NULL COMMENT 'column9',
  `column10` varchar(10) DEFAULT NULL COMMENT 'column10',
  `column11` varchar(50) DEFAULT NULL COMMENT 'column11',
  `column12` varchar(50) DEFAULT NULL COMMENT 'column12',
  `column13` varchar(50) DEFAULT NULL COMMENT 'column13',
  `column14` varchar(50) DEFAULT NULL COMMENT 'column14',
  `column15` varchar(50) DEFAULT NULL COMMENT 'column15',
  `column16` varchar(50) DEFAULT NULL COMMENT 'column16',
  `column17` varchar(50) DEFAULT NULL COMMENT 'column17',
  `column18` varchar(50) DEFAULT NULL COMMENT 'column18',
  `column19` varchar(50) DEFAULT NULL COMMENT 'column19',
  `column20` varchar(50) DEFAULT NULL COMMENT 'column20',
  `column21` varchar(200) DEFAULT NULL COMMENT 'column21',
  `column22` varchar(200) DEFAULT NULL COMMENT 'column22',
  `column23` varchar(200) DEFAULT NULL COMMENT 'column23',
  `column24` varchar(200) DEFAULT NULL COMMENT 'column24',
  `column25` varchar(200) DEFAULT NULL COMMENT 'column25',
  `column26` varchar(200) DEFAULT NULL COMMENT 'column26',
  `column27` varchar(200) DEFAULT NULL COMMENT 'column27',
  `column28` varchar(200) DEFAULT NULL COMMENT 'column28',
  `column29` varchar(200) DEFAULT NULL COMMENT 'column29',
  `column30` varchar(200) DEFAULT NULL COMMENT 'column30',
  `column31` varchar(500) DEFAULT NULL COMMENT 'column31',
  `column32` varchar(500) DEFAULT NULL COMMENT 'column32',
  `column33` varchar(500) DEFAULT NULL COMMENT 'column33',
  `column34` varchar(500) DEFAULT NULL COMMENT 'column34',
  `column35` varchar(500) DEFAULT NULL COMMENT 'column35',
  `column36` varchar(500) DEFAULT NULL COMMENT 'column36',
  `column37` varchar(500) DEFAULT NULL COMMENT 'column37',
  `column38` varchar(500) DEFAULT NULL COMMENT 'column38',
  `column39` varchar(500) DEFAULT NULL COMMENT 'column39',
  `column40` varchar(500) DEFAULT NULL COMMENT 'column40',
  `column41` datetime DEFAULT NULL COMMENT 'column41',
  `column42` datetime DEFAULT NULL COMMENT 'column42',
  `column43` datetime DEFAULT NULL COMMENT 'column43',
  `column44` datetime DEFAULT NULL COMMENT 'column44',
  `column45` datetime DEFAULT NULL COMMENT 'column45',
  `column46` char(1) DEFAULT NULL COMMENT 'column46',
  `column47` char(1) DEFAULT NULL COMMENT 'column47',
  `column48` char(1) DEFAULT NULL COMMENT 'column48',
  `column49` char(1) DEFAULT NULL COMMENT 'column49',
  `column50` char(1) DEFAULT NULL COMMENT 'column50',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实例表';

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改者',
  `is_deleted` varchar(1) DEFAULT 'n' COMMENT '是否删除',
  `receiver` varchar(32) DEFAULT NULL COMMENT '接收人',
  `business_type` varchar(45) DEFAULT NULL COMMENT '通知类型',
  `message_type` varchar(45) DEFAULT NULL COMMENT '通知方式：邮件或短信',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `user_type` varchar(1) DEFAULT NULL COMMENT '用户类型',
  `sender` varchar(32) DEFAULT NULL COMMENT '发送人',
  `receiver_address` varchar(128) DEFAULT NULL COMMENT '接收地址',
  `associate_id` int(11) DEFAULT NULL COMMENT '关联ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1026 DEFAULT CHARSET=utf8 COMMENT='消息记录';


CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '权限名称',
  `descr` varchar(200) DEFAULT NULL COMMENT '权限描述',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';




CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) DEFAULT NULL COMMENT '姓名',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `descr` varchar(400) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `type` char(1) DEFAULT NULL COMMENT '用户类型：1-个人用户，2-企业用户',
  `account` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `weixin` varchar(70) DEFAULT NULL COMMENT '微信',
  `weibo` varchar(70) DEFAULT NULL COMMENT '微博',
  `intro` varchar(320) DEFAULT NULL COMMENT '介绍',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `signature` varchar(70) DEFAULT NULL COMMENT '签名',
  `is_deleted` varchar(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1019 DEFAULT CHARSET=utf8 COMMENT='用户';

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色Id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建日期',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改日期',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建者',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1023 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

CREATE TABLE `validate_code` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `phone` char(40) NOT NULL COMMENT '手机号',
  `code` char(10) NOT NULL COMMENT '验证码',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `is_deleted` char(1) DEFAULT 'n' COMMENT '是否删除',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1023 DEFAULT CHARSET=utf8 COMMENT='手机验证码';
