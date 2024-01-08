#创建 sys_file 表 id 设置太大

Owner: YunaiV

Repo: ruoyi-vue-pro

Labels: bug 

## qlnutotoro (23 Feb 2021)

CREATE TABLE `sys_file` (
  `id` varchar(255) NOT NULL COMMENT '文件路径',
  `content` blob NOT NULL COMMENT '文件内容',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件表\n';

执行上面语句，报错如下:
Specified key was too long; max key length is 767 byte

查看数据长度 ，  255 修改成36
`id` varchar(36) NOT NULL COMMENT '文件路径',



## YunaiV (02 Mar 2021)

已经修复，感谢提出。

胖友后面可以尝试 pr 哈哈哈。多好！

