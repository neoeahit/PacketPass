#!/bin/bash

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
        iptables -A OUTPUT -p tcp -d ${Array[i]} --sport 22 --dport 513:65535 -m state --state ESTABLISHED -j ACCEPT   
    done
fi
#for http
if [ $http = $t ]
  then
    for ((i=5; i<${#Array[@]}; ++i));
      do
        iptables -A INPUT -p tcp --sport 1024:65535 -s ${Array[i]} --dport 80 -m state --state NEW,ESTABLISHED -j ACCEPT
iptables -A OUTPUT -p tcp --sport 80 -d ${Array[i]} --dport 1024:65535 -m state --state ESTABLISHED -j ACCEPT
    done
fi
#for https
if [ $https = $t ]
  then
    for ((i=5; i<${#Array[@]}; ++i));
      do
      iptables -A INPUT -p tcp -s  ${Array[i]} --sport 1024:65535 --dport 443 -m state --state NEW,ESTABLISHED -j ACCEPT
      iptables -A OUTPUT -p tcp --sport 443 -d  ${Array[i]} --dport 1024:65535 -m state --state ESTABLISHED -j ACCEPT
     done
fi
#for smtp
if [ $smtp = $t ]
  then
    for ((i=5; i<${#Array[@]}; ++i));
      do
        iptables -A INPUT -p tcp -s ${Array[i]} --sport 1024:65535 --dport 25 -m state --state NEW,ESTABLISHED -j ACCEPT
        iptables -A OUTPUT -p tcp --sport 25 -d ${Array[i]} --dport 1024:65535 -m state --state ESTABLISHED -j ACCEPT
    done
fi
##allowing for ping
if [ $smtp = $t ]
  then
    for ((i=5; i<${#Array[@]}; ++i));
      do
        iptables -A INPUT -s ${Array[i]} -p icmp --icmp-type echo-request -j ACCEPT
        iptables -A OUTPUT -d ${Array[i]} -p icmp --icmp-type echo-reply -j ACCEPT
      done
fi
iptables -P INPUT DROP
iptables -P OUTPUT DROP
iptables -L -v
/etc/init.d/iptables save
/etc/init.d/iptables start
iptables -L -v
