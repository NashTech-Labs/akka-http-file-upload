# akka-http-file-upload
A basic application to upload a file using akka-http in Scala with its test cases

### Run application
sbt run

### Run test cases
sbt test

### Test application
After start the application hit "http://localhost:9000/user/file/upload" in any rest client with a file, you will get your file uploaded in temp folder of your system and a success response in rest client with the size of file.
