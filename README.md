# multiplayer-kalah-backbase

This is my code challenge, here you will find:

# Features


1- The storage was made uppon Hazelcast, this is intented to make 
stateless un-central-coorditated microservices nodes that you can
deploy in cloud and make your game players free to use any of them.
This can be a good choice using "client side" microservices load  balancers like Ribbon because of the item 2.

2- This game microservices will register to an Netflix Eureka instance in order to be discovered, you may find there can be any number of this instances and you can connect each opne of them without problems because the context is stored in Hazelcast.

3- This uses springboot to run the register service (Netflix Eureka, part of Spring now) and the game service.

4- This is a multiuser backend so, there is the multiboard control that make you capable of mantain any number of users playing games simultaneusly, watch other players games and make open game challenges.
5- This is managed by maven and gradle, so you may fin very easy to deploy and create IDE projects. Just import as Gradle project or use maven tool.



# What I think is still needed (not coded yet):

1- Login and security, oAuth2 will do the work
2- More maintainable store, SpringData abstraction will solve this.

 
