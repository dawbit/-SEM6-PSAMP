Installing AspectJ
==================
1. Download AspectJ compiler and tool from:
   http://aspectj.org/servlets/AJSite?channel=download&subChannel=compilerAndTools

   Download jar file corresponding to "Compiler and Core Tools".
   
2. Install AspectJ by invoking (the exact command will vary depending upon
   the version of aspectj downloaded):

   java -jar aspectj-tools-1.0.3.jar

   Setup classpath as specified in the installer.

Compiling and running examples
==============================
1. For compiling and running examples, change current directory to example
   of your interest and issue following command:
   ajc *.java

   Note, compiling example in "access-control" directory will
   result in an compile time error. This is the correct behavior illustrating
   how aspectj can be used for compile-time crosscutting behavior modification.
   See the article for more details.

2. Run examples by invoking the main class in each directory, except
   "thread-pooling". You can run tests by issuing following command in 
   each directory:

   java Test

3. For "thread-pooling" example, start two or more command shell. In one
   command shell, start the server by issuing:

   java UppercaseServer <port-number>

   For example,
   java UppercaseServer	10000

   In other command shells, start clients by issuing

   java UppercaseClient <servername> <port-number>

   For example,
   java UppercaseClient localhost 10000

   To test pooling aspect, kill a client (by typing ^C), start a new 
   one, and observe messages printed in command shell running the server.
   
   
