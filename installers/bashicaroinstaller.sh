#!/bin/bash

cd ~ && mkdir -p icaro && cd icaro && wget https://github.com/Icaro-lang/compiler/releases/download/0.1/Icaro-0.1.jar

cd ~ && echo "function icaro(){ java -jar ~/icaro/Icaro-0.1.jar \$1 \$2; }" >> .bashrc