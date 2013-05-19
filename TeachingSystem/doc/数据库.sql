CREATE TABLE TB_Course (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '课程名称',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '类型',
  `semester` int(11) NOT NULL DEFAULT '0' COMMENT '学期',
  `classId` bigint(20) NOT NULL DEFAULT '0' COMMENT '班级id',
  `teacherId` bigint(20) NOT NULL DEFAULT '0' COMMENT '老师id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态,0未结束',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程表'


CREATE TABLE TB_Course_Student_TotalScore (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `score` double NOT NULL DEFAULT '0' COMMENT '分数',
  `semester` int(11) NOT NULL DEFAULT '0' COMMENT '学期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生课程总得分表'


CREATE TABLE TB_Course_Student_Score (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `percentType` tinyint(4) NOT NULL DEFAULT '0' COMMENT '评分细则类型id',
  `percent` double NOT NULL DEFAULT '0' COMMENT '评分细则比例',
  `score` double NOT NULL DEFAULT '0' COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程评分类型得分表'

CREATE TABLE TB_Course_Student_Property_SemesterScore (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `semester` int(11) NOT NULL DEFAULT '0' COMMENT '学期',
  `propertyId` bigint(20) NOT NULL DEFAULT '0' COMMENT '属性id',
  `score` double NOT NULL DEFAULT '0' COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生学期属性分数表'

CREATE TABLE TB_Course_Student_Property_Score (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `propertyId` bigint(20) NOT NULL DEFAULT '0' COMMENT '属性id',
  `score` double NOT NULL DEFAULT '0' COMMENT '分数',
  `semester` int(11) NOT NULL DEFAULT '0' COMMENT '学期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程评分类型得分表'


CREATE TABLE TB_Course_Property (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` bigint(20) NOT NULL DEFAULT '0' COMMENT '属性名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生属性表'


CREATE TABLE TB_Course_ScorePercent_Property (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `percentType` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分细则类型',
  `propertyId` bigint(20) NOT NULL DEFAULT '0' COMMENT '属性id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生属性课程关联表'


CREATE TABLE TB_Course_ScorePercent (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `percentType` tinyint(4) NOT NULL DEFAULT '0' COMMENT '评分细则类型id',
  `percent` double NOT NULL DEFAULT '0' COMMENT '评分细则比例',
  `teacherId` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分老师id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程百分比表'

CREATE TABLE TB_Course_PercentType (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '评分细则类型名称',
  `desc` varchar(127) NOT NULL DEFAULT '' COMMENT '评分细则类型描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程评分类型表'


CREATE TABLE TB_Course_PercentType_Group (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '小组数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程小组表'


CREATE TABLE TB_Course_PercentType_Group_Student (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `groupId` bigint(20) NOT NULL DEFAULT '0' COMMENT '小组id',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '组长还是普通成员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程小组成员表'


CREATE TABLE TB_Course_PercentType_Group_Student_Score (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `groupId` bigint(20) NOT NULL DEFAULT '0' COMMENT '小组id',
  `fromStudentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '从学生id',
  `toStudentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '去学生id',
  `score` double NOT NULL DEFAULT '0' COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程小组成员互相打分表'


CREATE TABLE TB_Course_PercentType_Stage (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `score` double NOT NULL DEFAULT '0' COMMENT '分数',
  `stageIndex` int(11) NOT NULL DEFAULT '0' COMMENT '哪个阶段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='阶段达标测试分数表'


CREATE TABLE TB_Class (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '班级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='班级'

CREATE TABLE `TB_Profile` (
  `UserId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `UserName` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `Password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '用户登录等级',
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户信息表'    