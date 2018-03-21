.PHONY: clean

clean :
	rm */*.class

.PHONY: %

% : Application/IET.class
	java Application.IET $@

Application/IET.class :
	javac Application/IET.java
