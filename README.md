#Hello Akka
===========

This is the introductory project for running Akka on rasberry pi 3. We have a bare minimum akka project with 2 simple actors that just ping-pong messages back and forth (taken almost straight from the Activator Akka template). We are also using `sbt assembly` to create fat (or uber) jars. We need this since compiling/building scala apps on the pi is ... painful at best, impossible at worst. BUT no need to worry. With SBT assembly and SSH, it's simple to develop apps on a full workstation and then simply SCP your fat jar to the pi to run it!

## Running akka applications on your Pi
Im currently using a Rasberry Pi 3, Model B with the stock Rasbian OS. These steps apply to almost any akka application that you want to run on your pi.

## Step 1: Clone this repo to your workstation
You're probably wondering "Well don't I have to setup the entire Scala/SBT environment on me pi first?!". No you don't! Remember we're compiling and assembling on the workstation and then just shipping a fat jar to the pi. All you need is java (preferably java 1.8+) installed (it is on rasbian by default).

## Step 2: Compile the app
`cd` into the root of the project and run `sbt ";clean;compile"`. This will install all the dependencies and compile the application. 

## Step 3: Create a fat jar
After the build is complete, run `sbt run assembly`. This will create a fat jar into `target/scala-2.11/hello-scala-assembly.<VERSION>.jar`.

## Step 4: Deploy the jar to your pi
All we have to do now is ship the jar to your pi. Make sure the SSH server is enabled on your pi and then run `scp [FAT_JAR_PATH] pi@your.pi.ip.address:~/`. Make sure to replace `FAT_JAR_PATH` with the relative path to the fat jar. Also, change the SSH user/host as needed. If all goes well you should see a successful transfer to the home directory!

## Step 5: Run the app!
This is rediculously simple. SSH into your pi (or use a monitor connected directly to it.. whatever), `cd` into the directory where you transferred the jar file and then run `java -jar [FAR_JAR_FILENAME]`. Boom! That's it. You should see output like this:

```
[INFO] [MyActorSystem-akka.actor.default-dispatcher-2] [akka://MyActorSystem/user/pingActor] In PingActor - starting ping-pong
[INFO] [MyActorSystem-akka.actor.default-dispatcher-4] [akka://MyActorSystem/user/pingActor/pongActor] In PongActor - received message: ping
[INFO] [MyActorSystem-akka.actor.default-dispatcher-2] [akka://MyActorSystem/user/pingActor] In PingActor - received message: pong
[INFO] [MyActorSystem-akka.actor.default-dispatcher-4] [akka://MyActorSystem/user/pingActor/pongActor] In PongActor - received message: ping
[INFO] [MyActorSystem-akka.actor.default-dispatcher-2] [akka://MyActorSystem/user/pingActor] In PingActor - received message: pong
[INFO] [MyActorSystem-akka.actor.default-dispatcher-4] [akka://MyActorSystem/user/pingActor/pongActor] In PongActor - received message: ping
[INFO] [MyActorSystem-akka.actor.default-dispatcher-2] [akka://MyActorSystem/user/pingActor] In PingActor - received message: pong
```

Have fun building Akka apps on your pi! Let's get an entire distributed cluster going next ;)

