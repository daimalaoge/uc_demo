# uc_demo
uc_demo UC快速开发框架</p>
登录界面</p>
![image](http://att.newsmth.net/nForum/att/Java/426119/1868/)</p>
系统代码（数据字典）管理</p>
![image](http://att.newsmth.net/nForum/att/Java/426119/19717/)</p>
系统账号管理</p>
![image](http://att.newsmth.net/nForum/att/Java/426119/85023)</p>
权限组管理</p>
![image](http://att.newsmth.net/nForum/att/Java/426119/137300)</p>
系统日志查看</p>
![image](http://att.newsmth.net/nForum/att/Java/426119/229064)</p>
Demo示范</p>
![image](http://att.newsmth.net/nForum/att/Java/426119/340379)</p>
权限设置</p>
![image](http://att.newsmth.net/nForum/att/Java/426119/393248)</p>
uc_demo项目是Union Componen项目中的一个用例。本用例是一个后台的全功能实现，包括以下功能：   </p>
1、基础权限管理实现</p>
2、基础菜单管理实现</p>
3、菜单、权限、账号联动实现</p>
4、基础代码可编辑</p>
5、自动记录日志</p>
6、简化增删改功能</p>
7、简单Demo参考</p>
综合来说，用本项目代码进行项目开发，基本上不用动一行代码，从登陆到权限逻辑都涵盖了。直接写业务代码就可以</p>
</p>
项目优点：</p>
1、简单高效</p>
2、代码简洁易懂，通过对简单Demo的参考，一般人在1个小时内就可以完成一个增删改的全业务代码实现</p>
3、组件增强：本项目是联合组件项目的基础平台，在这个平台上开发的项目，可以成为联合组件的一部分</p>
4、通过联合组件，可以不断增强业务功能</p>
5、前后端分离
</p>
本项目使用技术：</p>
基本核心：Spring Boot 2.0以上版本</p>
UI框架：KitAdmin（基于LayUI）</p>
前端展示：Freemarker</p>
安全验证：shiro</p>
自动日志记录：AspectJ</p>
业务防篡改功能：uc_utils</p>
自动匹配基础代码：反射注入</p>
lombok插件</p>
数据库：jpa</p>
数据库查询：dsl</p>
内存缓存：ehcache</p>
</p>
项目使用 </p>
把项目下载下来，里面有个sql文件，mysql数据库里先做成一个库uc_demo，把sql导入，然后项目中的application-local.yml文件修改下链接属性，启动项目，访问http://localhost:8088/login使用就可以 
默认用户名/密码：admin/123456,验证码随便输入就好了，那是假的功能... 


