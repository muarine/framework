项目名称：SpringMVC4.1.7 Rest + Mybatis3.0.0

项目主要规范：

1. 按照模块给package命名

2. rest controller只提供返json的api ,业务逻辑Coding在Service层

3. mapper层采用xml拼接sql，(Mybatis官方不建议用注解配置)

4. 编码原则：最简,引入最少的依赖jar干更多的事

5. mybatis中Bean和数据库columns的映射关系保持一致，所以java bean采用和数据库中columns一样的命名方式

java环境建议采用jdk8，主要优势：

详情介绍：http://www.infoq.com/cn/articles/Java-PERMGEN-Removed

1. 虚拟机大改动。永久带消失，迎接元空间，也就不会出现OutofMemeryException

2. 元空间处于系统的与堆不相连的本地内存区域


场景：
初始化 -XX:MetaspaceSize=21M

JVM初始化21M内存空间，这也是GC高水平线，一旦触及这一水平线，JVM将会GC，并且监测GC的内存空间，水平线将会被重置


元空间虚拟机
    
  采用组块分配的形式 
    
    类加载器类型决定区块大小
    
      类加载器加载元数据


问题：元空间虚拟机分配的空闲空间和类需要的区块大小不一致，最终会出现碎片化。 而且虚拟机不支持压缩操作


版本号：1.0
