
PROCESSING_CORE = /Applications/Processing.app/Contents/Resources/Java/core.jar

library/gpx.jar: src/tomc/gpx/*.java
	javac -cp $(PROCESSING_CORE):library/nanoxml-lite-2.2.3.jar -sourcepath src -d . -target 1.1 -source 1.3 src/tomc/gpx/*.java
	jar -Mcvf library/gpx.jar tomc/gpx/*.class

test:
	java -cp $(PROCESSING_CORE):library/nanoxml-lite-2.2.3.jar:library/gpx.jar tomc.gpx.GPX newforest.gpx

