#! /bin/bash
#java -jar Splash.jar $@
#clang Main.ll -o Main
#./$@

# Check if the first argument is "hello"

if [ "$1" = "--tree" ]; then
    args_except_first=("${@:2}")
    echo "${@:2}"
    for arg in "${args_except_first[@]}"; do
        mvn exec:java -Dexec.mainClass="Main" -Dexec.args=" --tree $arg "
    done

else
    echo "$@"
    for arg in "$@"; do
        mvn exec:java -Dexec.mainClass="Main" -Dexec.args="$arg"
    done
fi
