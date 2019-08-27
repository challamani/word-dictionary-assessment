####Spring boot application

#####API to create and persist a word dictionary by uploading a text file having few paragraphs and keeps the words in h2 database (dictionary)
**This spring boot application contains 5 APIs**

**[POST API] There are two post apis, we can invoke by using simple ajax-base UI (http://localhost:8080)** 

1. [http://localhost:8080//happiest-minds-assessment/1.0.0/uploadFile]
2. [http://localhost:8080//happiest-minds-assessment/1.0.0/uploadMultipleFiles]

Three Get APIs, there is no UI has integrated, we can directly make curl call (or) postman/browser call.
1. [http://localhost:8080/happiest-minds-assessment/1.0.0/searchByCommaSeparateWords/{TURING,TUNING}]
2. [http://localhost:8080/happiest-minds-assessment/1.0.0/searchBySubWord/AL] (it works like a SQL-like AL%)

Note: 
1. Searching the right word based on quick-index, first letter or first-two letters going to be quick index, to find the words quickly.
2. H2 Database [http://localhost:8080/h2/login.do]
3. File content parsing is handling in async way, we can find thread-pool for that.   
4. find alan_turing.txt file to upload.