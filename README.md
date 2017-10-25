# mail-util
邮件发送工具类<br/>
本工具类提供两种方式发送邮件<br/>
1.发送文本邮件<br/>
2.发送html模版邮件<br/>

使用方式<br/>
a. 添加jar包依赖<br/>
     <dependency><br/>
         <groupId>com.feicent</groupId><br/>
         <artifactId>mail-util</artifactId><br/>
         <version>1.0-SNAPSHOT</version><br/>
     </dependency><br/>
     
b. 配置文件中填写邮箱信息<br/>
在类根目录创建配置文件mail.properties<br/>
填写配置信息如下<br/>

mail.host=smtp.163.com<br/>
mail.port=25<br/>
mail.username=zhang<br/>
mail.password=123456<br/>
mail.from=zhang@163.com<br/>

c. 创建邮件信息类，使用工具类进行发送

     @Test
     public void testSend() throws Exception {
         TextMailMessage message = new TextMailMessage();
         message.setTo("80****92@qq.com");
         message.setSubject("测试邮件工具类");
         message.setText("这里是邮件发送工具类的正文内容");
         MailUtil.send(message);
     }

     @Test
     public void testSend1() throws Exception {
         TemplateMailMessage message = new TemplateMailMessage();
         message.setTo("80****92@qq.com");
         message.setSubject("测试邮件工具类");
         Map model = new HashMap();
         model.put("user", "测试用户");
         model.put("text","这里是通过模版显示的邮件内容 !");
         message.setModel(model);
         message.setTemplatePath("template/mail.vm");
         MailUtil.send(message);
     }
