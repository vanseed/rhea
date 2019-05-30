# rhea

rhea是基于springboot的单服务（区别于微服务）框架，可用于app的服务端和运营端。

>* 一、依赖saturn项目，https://github.com/vanseed/saturn.git ，请先克隆该项目。
>* 二、基本框架，sprngboot, SSH+M（crud采用jpa，默认hibernate实现，复杂关联查询采用mybatis）。
>* 三、针对移动端作session共享，使用redis持久化session。
>* 四、默认mysql数据库。
