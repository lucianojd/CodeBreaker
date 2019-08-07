all:
	javac *.java engine/*.java

jar:
	jar cfm CodeBreaker.jar manifest.txt Main.class engine/*.class

unit_test:
	java engine.UnitTests

clean:
	rm *.class engine/*.class
