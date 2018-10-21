# Crawler
crawler application for CSDN blog and so on...

This is a collection of crawler application 

## CSDNCrawlerApp:
 This is a crawler application for us to crawling blod data from CSDN with keywords.
 Unfortunaly it is only a simple project to practice, I won't try to make this to fit distributed system(For I don't have enough time to do that).
### Environment
  JDK1.8 
  Maven 
  IDE: IntelliJ IDEA
  (this is a spring boot project with spring-boot-jpa)

Actually, If you understand every step int this project ,you can easy to create you own crawler application to any website.
### how to make this application running
  1.install a Chrome browser on you computer,and install a suitable chromedriver.exe
  2.make sure your chrome.exe and hromedriver.exe is in the same directory. Besides,make sure the directory is same as 
  the webdriver.chrome.driver environment-path defined in com.cetc28.util.WebDriverUtil.chromeDriver() method.
  (last two step can be skipped over if you can use other methods to get authorId with keyword.Here we use selenium is because that CSDN search
  blogs with keyword cant get a little result,it asked www.baidu.com for help and baidu return some search results ,after that, CSDN use this two results
  to show us.if you just want to get CSDN search result, it is very easy to achieve.)
  3.use database MYSQL and change application.yml with you database config-info.
  4.open you browser for website "http://localhost:8080/start/csdn",and wait for application running.
  5.Good Luck.  (2018-10-21)
