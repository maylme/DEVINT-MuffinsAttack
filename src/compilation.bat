rem set PATH=../../lib/;../../jre/bin/
rem javac -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar -Djava.library.path=../ressources/lib/ -d ../bin devintAPI/*.java jeu/*.java

javac -encoding UTF-8 -cp .;../../VocalyzeSIVOX/bin/SI_VOX.jar;../ressources/lib/jdom-2.0.5.jar;../ressources/lib/jl1.0.1.jar -d ../bin devintAPI/*.java jeu/*.java  
pause
