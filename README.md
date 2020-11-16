# Computers Database

* [General info](#general-info) 
* [Technologies](#technologies) 
* [Setup](#setup) 
 
## General info 
This project contains automation tests for computers database.   

## Technologies 
Project is created with: 
* Maven version: 3.5.4
* Selenium version: 3.141.59 
* Testng version: 7.3.0
* Chrome driver version: 86.0.4240.22

## Setup 
To run this project: 
1. Install maven: http://maven.apache.org/download.cgi 
2. Download chrome driver: https://chromedriver.storage.googleapis.com/index.html?path=86.0.4240.22/
3. Run the following command: 
```aidl
mvn clean test -Dwebdriver.chrome.driver=%PATH_TO_DRIVER%\chromedriver.exe -Dtestng.dtd.http=true
```
4. Reports can be found here: %PATH_TO_PROJECT%\target\surefire-reports\