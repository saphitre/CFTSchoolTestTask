# Инструкция по запуску
### 1. IntelliJ IDEA
* Open 'Edit Run/Debug configuration' dialog
* Change java version
* Add program parameters\
###### -s/-i (-a/-d) out.txt in.txt\
Example1: -i out.txt in1.txt in2.txt\
Example2: -s -d out.txt in1.txt in2.txt\
### 2. Windows cmd
* Open directory with java\
Example: > cd C:\Users\User\.jdks\azul-13.0.11\bin
* Run app via command\
Example: > java -classpath C:\Users\User\IdeaProjects\CFTSchoolTestTask\out\production\CFTSchoolTestTask Main -s -d out.txt in2.txt
# Особенности реализации
* JDK version: azul-13 Java version: 13.0.11
* Build system: intelliJ
