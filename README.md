# 项目描述
目的：为毕业生选择老师进行毕业设计提供平台。  

# 项目环境
* idea19.3.3
* springboot 2.2.5
* mysql 8.0
* Vue x

***

## 2020.03.10
项目初始化完成

## 2020.03.12
第一次实体类书写完成

## 2020.03.17
修改实体类
Tutor添加password属性，Student id属性去掉自增长;
编写基本repository接口

## 2020.03.19
实体类第四次修改
![entity01](image/entity0104.png)
修改实体类，每个实体类添加插入时间戳和修改时间戳。  
修改实体类名，将AT类修改为AreaTutor等。
去掉AreaTutor类的WEIGHT（权重）属性
添加StudentService类
和TutorService类及其部分方法
    