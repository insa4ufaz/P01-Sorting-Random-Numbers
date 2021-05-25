# Sorting random numbers in distributed systems

## Application of Distributed Systems In Speeding Up Sorting Programs

This is a comprehensive algorithm in speeding up a computation intensive problem using distributed system concept.
The program can be speed up by distributing the sorting efforts using numbers of process and thread, which can be
located within a machine or spread to different machines. The load balancer balance the jobs and distribute them to
three different servers, and then collects back the results. The three servers uses a merge sort algorithm and it also
implements multithreading for concurrent job processing in giving faster results.

![Strucutre_DA](https://user-images.githubusercontent.com/49164758/119521709-73c08500-bd8c-11eb-8fd9-681cb4e021a9.png)

## Architecture - UML Class Diagram

![UML_diagram](https://user-images.githubusercontent.com/49164758/119521815-889d1880-bd8c-11eb-9fe2-1696dce2d088.jpeg)

## How to run

`javac Client.java LoadBalancingServer.java Server1.java Server2.java Server3.java`

`java Client`
