# Darts Scoreboard

A simple dartboard scoring system made with JavaFX that is portable to iOS, Android, and Embedded Systems.
It allows scoring of 2-player 501 darts and Around the World, with a future implementation including Cricket.

## Getting Started

Please follow the procedure outlined below to get the source code ready for development.

### Prerequisites

Please ensure you have the following prerequisites installed before development.

* [Latest version of Gradle](https://gradle.org)
* [JetBrains IntelliJ IDEA](https://www.jetbrains.com/idea/) Community Edition is fine
* Xcode (for iOS porting)
* Android SDK (for Android porting)
* JDK 8

### Development and Use

NOTE: If you encounter any build errors in this step, ensure you are using JDK8.
This is done by setting either your JAVA_HOME environment variable, or by specifying
IDE settings accordingly. 

Once all prerequisites have been installed, open the project in IntelliJ. Open
the Gradle window, and run the main build task. If the build is error-free, execute the
application run task to ensure it works on your computer. If this works fine, please consult
the [JavaFXPorts documentation](https://docs.gluonhq.com/javafxports/) for deploying
on iOS, Android, and Embedded devices. Note that special free provisioning will need
to be done for deploying on iOS devices if you are not a part of the Apple Development
Program.

#### Build and Run without IDE

To build and run the project without opening an IDE, use the Gradle wrapper for your
system with the relevant commands. For example, on macOS:

```
./gradlew build
./gradlew run
```
## License

This project is "licensed" under the Unlicense - see the [LICENSE](LICENSE) file for details.
