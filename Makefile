build: 
	javac *.java

run: Test.java
	java Test

play:
	xboard -debug -fcp "make run" -scp "fairymax"  -secondInitString "new\nrandom\nsd 2\n" -tc 5 -inc 2 -autoCallFlag true -mg 10 -sgf partide.txt -reuseFirst false 

clean:
	rm *.class
	rm *.txt
	rm xboard.debug

