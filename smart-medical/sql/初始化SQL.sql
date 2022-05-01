DROP TABLE IF EXISTS `house_user`;
CREATE TABLE `house_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `account` varchar(64) DEFAULT NULL COMMENT '账户',
  `name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `passwd` varchar(64) DEFAULT NULL COMMENT '密码',
  `create_dt` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_dt` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新人',
  `state` tinyint DEFAULT '1' COMMENT '状态：1=正常，2=禁用， 3=注销',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
