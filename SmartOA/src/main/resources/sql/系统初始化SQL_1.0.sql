DROP TABLE IF EXISTS `oa_form`;
CREATE TABLE `oa_form` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `name` varchar(64) DEFAULT NULL COMMENT '自定义表单名称',
  `desc` varchar(128) DEFAULT NULL COMMENT '自定义表单描述',
  `key_` varchar(64) NOT NULL COMMENT '自定义表单key',
  `content` varchar(1024) DEFAULT NULL COMMENT 'HTML内容',
  `create_id` varchar(64) DEFAULT NULL COMMENT '创建者Id',
  `create_dt` datetime DEFAULT NULL COMMENT '创建者时间',
  `version` int DEFAULT NULL COMMENT '版本号',
  `update_id` varchar(64) DEFAULT NULL COMMENT '更新人Id',
  `update_dt` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `only_key` (`key_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

