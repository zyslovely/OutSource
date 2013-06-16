CREATE TABLE TB_Semester_Student (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `semesterId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学期',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态,0未结束',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学期课程';


CREATE TABLE TB_Course (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '课程名称',
  `semester` bigint(20) NOT NULL DEFAULT '0' COMMENT '学期',
  `classId` bigint(20) NOT NULL DEFAULT '0' COMMENT '班级id',
  `teacherId` bigint(20) NOT NULL DEFAULT '0' COMMENT '老师id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态,0未结束',
  `description` varchar(512) NOT NULL DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程表';

CREATE TABLE TB_Course_Student (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `classId` bigint(20) NOT NULL DEFAULT '0' COMMENT '班级id',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0学生,1老师.2企业老师',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态,0未结束',
  `semesterId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学期id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程表人员';

CREATE TABLE TB_Course_Student_TotalScore (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `score` double NOT NULL DEFAULT '-1' COMMENT '分数',
  `semester` bigint(20) NOT NULL DEFAULT '0' COMMENT '学期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生课程总得分表';


CREATE TABLE TB_Course_Student_Score (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `percentType` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分细则类型id',
  `percent` double NOT NULL DEFAULT '0' COMMENT '评分细则比例',
  `score` double NOT NULL DEFAULT '-1' COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程评分类型得分表';

CREATE TABLE TB_Course_Student_Property_SemesterScore (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `semesterId`  bigint(20) NOT NULL DEFAULT '0' COMMENT '学期',
  `propertyId` bigint(20) NOT NULL DEFAULT '0' COMMENT '属性id',
  `score` double NOT NULL DEFAULT '-1' COMMENT '分数',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生学期属性分数表';

CREATE TABLE TB_Course_Student_Property_Score (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `propertyId` bigint(20) NOT NULL DEFAULT '0' COMMENT '属性id',
  `score` double NOT NULL DEFAULT '-1' COMMENT '分数',
  `semesterId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程评分类型得分表';


CREATE TABLE TB_Course_Property (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '属性名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生属性表';


CREATE TABLE TB_Course_ScorePercent_Property (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `percentType` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分细则类型',
  `percent` double NOT NULL DEFAULT '0' COMMENT '评分细则比例',
  `propertyId` bigint(20) NOT NULL DEFAULT '0' COMMENT '属性id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生属性课程关联表';


CREATE TABLE TB_Course_ScorePercent (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `percentType` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分细则类型id',
  `percent` double NOT NULL DEFAULT '0' COMMENT '评分细则比例',
  `objectCount` int(11) NOT NULL DEFAULT '0' COMMENT '相关的数量',
  `teacherId` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分老师id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程百分比表';



CREATE TABLE TB_Course_PercentType_Demo (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '课程类型名称',
  `demoJson` varchar(255) NOT NULL DEFAULT '' COMMENT '课程类型json',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程评分demo表';


CREATE TABLE TB_Course_PercentType_Group (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '小组数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程小组表';


CREATE TABLE TB_Course_PercentType_Group_Student (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `groupId` bigint(20) NOT NULL DEFAULT '0' COMMENT '小组id',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '组长还是普通成员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程小组成员表';


CREATE TABLE TB_Course_PercentType_Group_Student_Score (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `groupId` bigint(20) NOT NULL DEFAULT '0' COMMENT '小组id',
  `fromStudentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '从学生id',
  `toStudentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '去学生id',
  `score` double NOT NULL DEFAULT '-1' COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='课程小组成员互相打分表';


CREATE TABLE TB_Course_PercentType_Stage (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `studentId` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生id',
  `score` double NOT NULL DEFAULT '-1' COMMENT '分数',
  `stageIndex` int(11) NOT NULL DEFAULT '0' COMMENT '哪个阶段',
  `percentType` bigint(20) NOT NULL DEFAULT '0' COMMENT '评分细则类型id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='阶段达标测试分数表';


CREATE TABLE TB_Specialty (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `specialty` varchar(127) NOT NULL DEFAULT '' COMMENT '专业',
  `shortSpecialty` varchar(127) NOT NULL DEFAULT '' COMMENT '缩写专业',
  `semesterCount` int(11) NOT NULL DEFAULT '0' COMMENT '学期数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='专业' ;

CREATE TABLE TB_Class (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `specialtyId` bigint(20) NOT NULL DEFAULT '0' COMMENT '专业id',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '班级名称',
  `startYear` int(11) NOT NULL DEFAULT '0' COMMENT '入学年份',
  `semesterCount` int(11) NOT NULL DEFAULT '0' COMMENT '学期数量',
  `studentCount` int(11) NOT NULL DEFAULT '0' COMMENT '学生数量',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='班级';


CREATE TABLE TB_Semester (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(127) NOT NULL DEFAULT '' COMMENT '学期描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学期';

CREATE TABLE `TB_Profile` (
  `UserId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `UserName` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `Password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '姓名',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `classId` bigint(20) NOT NULL DEFAULT '0' COMMENT '班级id',
  `number` bigint(20) NOT NULL DEFAULT '0' COMMENT '编号',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '用户登录等级',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态,0未结束,1已结束',
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户信息表'; 

CREATE TABLE `TB_Profile_Property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `propertyId` bigint(20) NOT NULL DEFAULT '0' COMMENT '属性id',
  `score` double NOT NULL DEFAULT '-1' COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户学业属性'; 


CREATE TABLE `TB_FeedBack` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `FromUserId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `ToUserId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `content` varchar(1023) NOT NULL DEFAULT '' COMMENT '内容',
  `feedbackId` bigint(20) NOT NULL DEFAULT '0' COMMENT '反馈id',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态,0未读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='反馈表' ; 

CREATE TABLE `TB_Interactive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `showUserId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `content` varchar(1023) NOT NULL DEFAULT '' COMMENT '内容',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `forwardId` bigint(20) NOT NULL DEFAULT '0' COMMENT '转发id',
  `photoUrl` varchar(127) NOT NULL DEFAULT '' COMMENT '内容',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态,0公开,1隐藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='互动表';    

CREATE TABLE `TB_Interactive_Back` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `interactiveId` bigint(20) NOT NULL DEFAULT '0' COMMENT '互动id',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `content` varchar(1023) NOT NULL DEFAULT '' COMMENT '内容',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='互动回复表';    



CREATE TABLE `TB_Journal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
  `content` varchar(1023) NOT NULL DEFAULT '' COMMENT '内容',
  `courseId` bigint(20) NOT NULL DEFAULT '0' COMMENT '课程id',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态,0学习日志,1实习日志 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='日志表';    



CREATE TABLE `TB_School_Info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '标题',
  `content` varchar(1023) NOT NULL DEFAULT '' COMMENT '内容',
  `CreateTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态,0校园信息,1学院信息',
  `infoType` tinyint(4) NOT NULL DEFAULT '0' COMMENT '信息类型,0常规,1可以参加的类型',
  `imgUrl` varchar(255) NOT NULL DEFAULT '' COMMENT '图片url',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='校园信息';    



insert into TB_Profile (userName,password,name,level) values ('admin','admin','admin',3);
insert into TB_Profile (userName,password,name,level) values ('teacher','teacher','teacher',1);
insert into TB_Course_Property (name) values ('耐心毅力');
insert into TB_Course_Property (name) values ('创新能力');
insert into TB_Course_Property (name) values ('执行能力');
insert into TB_Course_Property (name) values ('团队协作');
insert into TB_Course_Property (name) values ('理解能力');


