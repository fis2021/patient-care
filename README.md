## General Description
The purpose of the following application is to bring both doctors and patients together. With all the pandemic situation it is really hard to make a medical appointment, especially when you need to go to a medical office for that. We want to make their life easy by making a platform based on doctor's services and customer needs
## Technologies Used
* [Java 15 or 16](https://www.oracle.com/java/technologies/javase-downloads.html)
* [JavaFX](https://openjfx.io/openjfx-docs/) (as GUI)
* [Maven](https://maven.apache.org/) / [Gradle](https://gradle.org/) (as build tools)
* [MongoDB]Nitrite Java](https://www.mongodb.com/) (as Database)
## Registration 
The user needs to first register into the application by selecting one of the 2 roles: 
* doctor 
* patient
These roles require a unique username, a password and the basic information like full name and phone number.
## Patient
A patient can see the details about doctors and their specialization.They can make appointments for a doctor or leave a review if at least one appointments is found.After the login they can see a list of all doctors where they can schoose.
## doctor
A doctor can add their specialization, accept/decline the appointments or make requests for deleting innapropiate reviews. the houses on the market.  After logging in he will see a list with all his patients that made appointments.
## Issue Tracking
In order to manage our workflow, we created a Jira instance that can be found [here](https://patient-care.atlassian.net).

## Prerequisites
To be able to install and run this project, please make sure you have installed Java 11 or higher. Otherwise, the setup will note work!
To check your java version, please run `java -version` in the command line.

To install a newer version of Java, you can go to [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://jdk.java.net/).

It would be good if you also installed Maven and / or Gradle to your system. To check if you have Maven or Gradle installed run `mvn -version` or `gradle -version`.

If you need to install any of them, please refer to this [Maven tutorial](https://www.baeldung.com/install-maven-on-windows-linux-mac) and the [official Gradle docs](https://docs.gradle.org/current/userguide/installation.html).

Make sure you install JavaFX SDK on your machine, using the instructions provided in the [Official Documentation](https://openjfx.io/openjfx-docs/#install-javafx). Make sure to export the `PATH_TO_FX` environment variable, or to replace it in every command you will find in this documentation from now on, with the `path/to/javafx-sdk-15.0.1/lib`.

_Note: you can download version 15 of the javafx-sdk, by replacing in the download link for version 16 the `16` with `15`._

## Setup & Run
To set up and run the project locally on your machine, please follow the next steps.

### Clone the repository
Clone the repository using:
```git
git clone git@github.com:fis2021/patient-care.git
```

### Verify that the project Builds locally
Open a command line session and `cd patient-care`.
If you have installed all the prerequisites, you should be able to run any of the following commands:
```
mvn clean install

If you prefer to run using the wrappers, you could also build the project using 
```
./mvnw clean install (for Linux or MacOS)
or 
mvnw.cmd clean install (for Windows)

### Open in IntelliJ IDEA
To open the project in IntelliJ idea, you have to import it as either a Maven, or a Gradle project, depending on what you prefer. 
After you import it, in order to be able to run it, you need to set up your IDE according to the [official documentation](https://openjfx.io/openjfx-docs/). Please read the section for `Non-Modular Projects from IDE`.
If you managed to follow all the steps from the tutorial, you should also be able to start the application by pressing the run key to the left of the Main class.

### Run the project with Maven / Gradle
The project has already been setup for Maven and Gradle according to the above link.
To start and run the project use one of the following commands:
* `mvn javafx:run` or `./mvnw javafx:run` (run the `run` goal of the `javafx` maven plugin)
