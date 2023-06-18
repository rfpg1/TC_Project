#! /bin/bash
sudo apt install default-jre
sudo apt install mvn
chmod +x script.sh
chmod +x run.sh
sudo apt install pip
sudo pip install antlr4-tools
cd /usr/local/lib

sudo curl -O https://www.antlr.org/download/antlr-4.12.0-complete.jar
export CLASSPATH=".:/usr/local/lib/antlr-4.12.0-complete.jar:$CLASSPATH"
alias antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.12.0-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
alias grun='java -Xmx500M -cp "/usr/local/lib/antlr-4.12.0-complete.jar:$CLASSPATH" org.antlr.v4.gui.TestRig'
cd -
antlr4


mvn clean
antlr4 -o target/generated-sources/antlr4 Grammar.g4
mvn install
