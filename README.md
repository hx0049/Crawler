# Crawler
crawler application for CSDN blog and so on...

This is a collection of crawler application 

## 1.CSDNCrawlerApp:
 This is a crawler application for us to crawling blog data from CSDN with keywords.
 Unfortunaly it is only a simple project to practice, I won't try to make this to fit distributed system(For I don't have enough time to do that).
### Environment
  JDK1.8 
  Maven 
  IDE: IntelliJ IDEA
  (this is a spring boot project with spring-boot-jpa)

Actually, If you understand every step int this project ,you can easily to create you own crawler application to any website.
### how to make this application running
  1.install a Chrome browser on you computer,and install a suitable chromedriver.exe
  
  2.make sure your chrome.exe and hromedriver.exe is in the same directory. Besides,make sure the directory is same as 
  the webdriver.chrome.driver environment-path defined in com.cetc28.util.WebDriverUtil.chromeDriver() method.
  (last two step can be skipped over if you can use other methods to get authorId with keyword.Here we use selenium is because that CSDN search
  blogs with keyword cant get a little result,it asked www.baidu.com for help and baidu return some search results ,after that, CSDN show us after mxing them. If you just want to get CSDN search result, it is very easy to achieve.)
  
  3.use database MYSQL and change application.yml with you database config-info.
  
  4.open you browser for website "http://localhost:8080/start/csdn",and wait for application running.
  
  5.Good Luck.  (2018-10-21)
  
  ### attention
  For convenient,I have crawled every css and picture as I can. all the image and css file in csdn-blog has been converted to base64 string or inner-style element. so it needs a lot of space to store the blog info for mysql, and mysql should enlarge the max-size of fields blob. othewise you will lose some blog-data.
  
  ## 2.EBookCrawler:

This is a crawler application for us to crawling ebook data from www.duquanben.com. to understand the crawling process, you should know the web structure very well,and find some regular things to make use of.

### Environment
  JDK1.8 
  Maven 
  IDE: IntelliJ IDEA
  (this is a spring boot project with spring-boot-jpa)

Actually, If you can take some time to read this application, web crawler will never be so magic to you.
### how to make this application running 
  1.use database MYSQL and change application.yml with you database config-info.
  
  2.running this application 
   
  3.open you browser for website "http://localhost:8080/ebook",and wait for application running.
  
  4.Good Luck.  (2018-11-14)
