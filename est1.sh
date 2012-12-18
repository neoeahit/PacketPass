#!/bin/bash

tcp=$1
ssh=$2
smtp=$3
http=$4
https=$5
array=( $@ )
len=${#array[@]}
_domain=${array[$len-1]}
_args=${array[@]:0:$len}
temp=${_args[0]}
 IFS=" "; declare -a Array=($*)
set -- "$temp"
t="Y"
#formaing rules for iptables
iptables -P INPUT ACCEPT
iptables -F
#for ssh
if [ $ssh = $t ]
  then
    for ((i=5; i<${#Array[@]}; ++i));
      do
        iptables -A INPUT -p tcp -s ${Array[i]} --sport 513:65535 --dport 22 -m state --state NEW,ESTABLISHED -j ACCEPT   
    done
fi

iptables -P INPUT DROP
iptables -L -v
service iptables save
iptables -L -v
