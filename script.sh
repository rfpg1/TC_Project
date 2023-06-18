#! /bin/bash
java -jar Splash.jar $@
clang Main.ll -o Main
./Main 123 ola
