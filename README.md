# spring-auth
available for demo only

## background
This simple demo is about authentication and authorization base on SpringBoot framework. it includes two entities: user and roles, one user can be assigned many roles. Once login sucessfully, the user will be assigned a jwt token, which will be expired in 2hrs, then every api except login will require a jwt token inside the request header, otherwise, access denied.

all data are stored in memory, not h2 or other related db. the application will initialize some data, like users, roles, to convince you run this demo.
## install
1. clone it
2. run it `mvn spring-boot:run` or 'java -jar spring-auth.jar'
3. try some apis on port 8081 (there are some test scripts under **httpclient** floder)
## library used
1. springboot starter
2. jwt generate tool
3. that's all
