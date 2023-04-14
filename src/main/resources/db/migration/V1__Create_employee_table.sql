CREATE TABLE employee (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '社員ID',
  name varchar(255) NOT NULL DEFAULT '' COMMENT '社員名',
  age varchar(255) NOT NULL DEFAULT '0' COMMENT '年齢',
  address varchar(255) NOT NULL DEFAULT '' COMMENT '住所',
  dept_id varchar(255) NOT NULL DEFAULT '0' COMMENT '部署ID',
  created_at datetime NOT NULL DEFAULT current_timestamp() COMMENT '登録日',
  updated_at datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新日'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社員';