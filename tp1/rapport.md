# Tp1

* vous trouverez les images dans le dossier src .

1. typing ifconfig : ip : 192.168.13.128

### adress v4
ens33: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.13.128  netmask 255.255.255.0  broadcast 192.168.13.255
        inet6 fe80::d73b:5954:2827:502f  prefixlen 64  scopeid 0x20<link>
        ether 00:0c:29:3f:60:d4  txqueuelen 1000  (Ethernet)
        RX packets 848081  bytes 1219026577 (1.2 GB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 314130  bytes 21876100 (21.8 MB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
### adress local
lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 65536
        inet 127.0.0.1  netmask 255.0.0.0
        inet6 ::1  prefixlen 128  scopeid 0x10<host>
        loop  txqueuelen 1000  (Local Loopback)
        RX packets 5478  bytes 576787 (576.7 KB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 5478  bytes 576787 (576.7 KB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
### virtual adress
tun0: flags=4305<UP,POINTOPOINT,RUNNING,NOARP,MULTICAST>  mtu 1500
        inet 10.135.9.216  netmask 255.255.252.0  destination 10.135.9.216
        inet6 fe80::a5c0:a973:74f5:d33b  prefixlen 64  scopeid 0x20<link>
        unspec 00-00-00-00-00-00-00-00-00-00-00-00-00-00-00-00  txqueuelen 100  (UNSPEC)
        RX packets 1485  bytes 1762040 (1.7 MB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 1259  bytes 102085 (102.0 KB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0


2. * since i'm using my computer need to install socklab and execute it i had an error `fatal error : readline/readline.h: No such file or directory`  so i had to install libreadline-dev .
* The socket identifier is 3

3. socklab> status
 Id  Proto   Local address              Remote address             TYPE  RWX ?
 ---------------------------------------------------------------------------
>3   UDP U   -    

4. socklab> bind 3 192.168.13.128 3000

5. socklab> sendto 3 127.0.0.1 3000 "hello world!"
Sent 12 bytes

6. He needs id-socket , ip-sender , port-used
7. socklab> recvfrom 3 12
should return : 
socklab> recvfrom 4 20
Un message de 16 octet(s) a ete recu de sparc1 (5000).
Message=<hello world!>
* since i'm using virtual machine they system got several ips so we need to use this commande : `sendto 3 * 3000 "hello world!"`
8. We should pick a port to use because maybe the machine already used a lot of port .
9. socklab> close 3
10. socklab> socket upd
Protocol? [tcp/udp] :  udp
The socket identifier is 3

socklab> socket udp
The socket identifier is 4

socklab> bind 3 * 0 // 0 means void port
The socket was attributed port 54339.

socklab> bind 4 * 0
The socket was attributed port 48040.

socklab> status
 Id  Proto   Local address              Remote address             TYPE  RWX ?
 ---------------------------------------------------------------------------
 3   UDP U   *(54339)                   -                          ipv4  .W.
>4   UDP U   *(48040)                   -                          ipv4  .W.

11. * sudo apt install wireshark
    * sudo wireshark -i lo -f udp
    * using ens 
    * stop it

12. salmib@ubuntu:~/Downloads/socklab-master$ ip address
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
13. 14. to respond i made a mistake i answer by "comment allez-" 
15. * d- msg1 : 20/62 * 100 = 32,258 % , msg2 : 14/56 * 100 = 25 %
16. socklab> close 3

socklab> close 4

socklab> close 4
Id socket ?:  5

socklab> status
No socket has been created yet.

## TCP

1. socklab> socket tcp
The socket identifier is 3

socklab> socket tcp
The socket identifier is 4

2. sudo wireshark -i lo -f 'tcp port 3000' &

3. ss -ant4

4. connection refused

5. listen 4 2

6. * serveur s2
   * client s1

7. socklab> connect 3 127.0.0.1 4000
Connection established.
there is a new line about connction etablished between 3 and 4    

8. socklab> accept 4
A connection from localhost (3000) was received.
Connection is established, with socket ID 7.

9. a new line has been pormoted : >7   TCP     127.0.0.1(4000)            127.0.0.1(3000)            ipv4  .W.
ps: its n°7 because i was testing so i have added a few other sockets and she got the write permission

10. * line 4 : after getting connected now S2 got SYN,ACK information about S1
    * line 5 : S1 response that everything is okay
    * line 6 : S1 ask S2 to get prepered to get informations from him
    * line 7 : S2 confime with a sequence number
11. * if PSH != 1 then nothing is gonna happen and we wont recieve the msg
    * how much data has been sent for the session ; eg: first we sent 1 , and when we sent the msg it increas to 21 "data of msg is 20"
    * the ack "aknowledgmenet" number is 21 means how much data has recieved
12.    
13. socklab> read 7 21
Read 20 bytes: message=<comment allez-vous ?>
14. now its 0
15. now we have 8	2389.307465065	127.0.0.1	127.0.0.1	TCP	66	3000 → 4000 [FIN, ACK] Seq=21 Ack=1 Win=65536 Len=0 TSval=3435186741 TSecr=3433728083 we observe a new var is promoted FIN to finish the connection
16. * write 7 "Tres bien merci !" 
    Sent 17 bytes
    * socklab> read 3 17 
    Read 17 bytes: message=<Tres bien merci !>
17. * 9	2389.347922977	127.0.0.1	127.0.0.1	TCP	66	4000 → 3000 [ACK] Seq=1 Ack=22 Win=65536 Len=0 TSval=3435186782 TSecr=3435186741 getting connected    ack  = 1
    * 10	3492.375640322	127.0.0.1	127.0.0.1	TCP	83	4000 → 3000 [PSH, ACK] Seq=1 Ack=22 Win=65536 Len=17 TSval=3436289809 TSecr=3435186741 push the data
    * 11	3492.375648676	127.0.0.1	127.0.0.1	TCP	66	3000 → 4000 [ACK] Seq=22 Ack=18 Win=65536 Len=0 TSval=3436289809 TSecr=3436289809 recieved
    * 12	3614.468142449	127.0.0.1	127.0.0.1	TCP	66	4000 → 3000 [FIN, ACK] Seq=18 Ack=22 Win=65536 Len=0 TSval=3436411902 TSecr=3436289809 shut down the connection
    * 13 13	3614.468149006	127.0.0.1	127.0.0.1	TCP	66	3000 → 4000 [ACK] Seq=22 Ack=19 Win=65536 Len=0 TSval=3436411902 TSecr=3436411902 confirmed the shutdown
18. 74+66+86+66 = 292 seg , 17/292 *100 = 5,82% , udp is not much efficace as tcp , due to verifecation of tcp of getting OK the data or no
19. socklab> close 3

    socklab> close 4

    socklab> close 5

    socklab> close 6

    socklab> close 7

    ocklab> status
    No socket has been created yet.

## flux

1. socklab> socket tcp
The socket identifier is 3

socklab> socket tcp
The socket identifier is 4

socklab> bind 3 
Host? :  127.0.0.1
Port? :  3000

socklab> bind 4
Host? :  127.0.0.1
Port? :  4000

socklab> connect 3 127.0.0.1 4000
connect(): Connection refused

socklab> listen 4 1

socklab> connect 3 127.0.0.1 4000
Connection established.

socklab> write 3 "test"
Sent 4 bytes


socklab> accept 4
A connection from localhost (3000) was received.
Connection is established, with socket ID 5.


socklab> read 5 4
Read 4 bytes: message=<test>

2. udo iptables -A INPUT -p tcp --dport 3000 --tcp-flags ACK ACK -j DROP

3. we observe having a several operations between "sup" and "dup"  of ACK; its duplicate ack  10 times exactly

4. 

5. 

6. we sent 1082358 times hello , we recieved 80727 times hello . we didnt recieved all data

7. we observe that the server inform client that he has recieved the data

8. 



## Nb : 
* This work is not done , i have understand the idea of spaming a data to a server by a timeout but i can't describe it by the follow questions , or maybe the work is too long and i can't keep my mind focus , i am sorry i didn't push this work in time , i had a lot of health problems and yet i'm suffering with a pain back .

* every step has a pic to show the results pls turn the date filter to get the pics in order to questions .