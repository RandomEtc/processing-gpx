
javac -cp ../../lib/core.jar;library/nanoxml-lite-2.2.3.jar -sourcepath src -d classes -target 1.1 -source 1.3 src/tomc/gpx/*.java

@cd classes
jar -Mcvf ../library/gpx.jar tomc/gpx/*.class
@cd ..
