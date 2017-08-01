# multiplayer-kalah-backbase

This is my code challenge for backbase, i hope you like it


## Versions

To access V1.0.0 of the repo, click on the `release` tab in https://github.com/jakard/multiplayer_kalah.

## Using an IDE

You can run the system in your IDE by running the two servers in order: _RegistrationService_, and _GamingService_.

Open the Eureka dashboard [http://localhost:1111](http://localhost:1111) in your browser to see that the `GAMING-SERVICE` have registered.  Next open SOAPUI or another REST test tool to execute method on the service endpoint.


## Command Line

You may find it easier to view the different applications by running them from a command line since you can place the two windows side-by-side and watch their log output

To do this, open three CMD windows (Windows) or three Terminal windows (MacOS, Linux) and arrange so you can view them conveniently.

 1. In each window, change to the directory where you cloned the game
 1. In the first window, build the application using `mvn clean package`
 1. In the same window run: `java -jar target/game-service-backbase-1.1.0.RELEASE.jar eureka` and wait for it to start up
 1. Switch to the second window and run: `java -jar target/game-service-backbase-1.1.0.RELEASE.jar game` and again wait for
 it to start up


You should see servers being registered in the log output of the first (registration) window.




