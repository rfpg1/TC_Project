# TC Project - sPlash Compiler

## Author
Ricardo Gonçalves 52765

## Run:

Give permissions first: chmod +x setup.sh

Then run setup: ./setup.sh

Then run script: ./script.sh (< --tree >)? (< filename>)*

Then run run: ./run.sh <filename.ll> <args>

Example: 
chmod +x setup.sh

./setup.sh

./script.sh --tree tests/positive/Test21.sp tests/positive/Test22.sp

./run.sh tests/positive/Test22.ll 1 2 3
