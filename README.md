# Snapbite
![Snapbite Logo](https://github.com/emmanuelmuturia/Snapbite/assets/55001497/c87b372a-4673-4a73-8e73-95c011317a59)

## Overview
This is a Kotlin Multiplatform (KMP) food journal mobile app that allows the user to also get suggestions on how to make their foods healthier...

https://github.com/emmanuelmuturia/Snapbite/assets/55001497/e560452b-7bf9-46f1-8cc7-b4eb33e6ff9a

## Table of Contents

1. [BackGround](#Background)
2. [Requirements](#Requirements)
3. [Project Structure](#Structure)
4. [Procedure](#Procedure)
5. [Screenshot](#Screenshot)
6. [Trivia](#Trivia)
7. [Tools, Technologies, & Libraries](#Credits)


## Background
- Snapbite was developed for the KotlinConf' 2024 Contest...
- The mobile app is written purely in Kotlin with a little bit of Swift to cater for the iOS-specific functionalities...

## Requirements
- These are the requirements for setting up the project:

1. ### Laptop
- The project requires a laptop with the minimum/recommended specifications set by Google for running Android Studio...

2. ### Android Studio
- This is the IDE used to run the project...
- Depending on the time of installation, the IDE may prompt you to update the project's Android Gradle Plugin (AGP) version...

3. ### Emulator/Physical Device (Android/iOS)
- To run the app, an Emulator provided by Android Studio or a physical device (USB/Wireless Debugging) is required...


## Structure
- Snapbite is divided into the following modules:

1. ### :android
- It contains MainActivity.kt which is used to run the Android app as well as setting up the Notification handling for Firebase Cloud Messaging (FCM)...

2. ### :commons
- This is the main module of the app that contains shared logic which is used by both the Android and iOS variants...
- It is subdivided into the following modules:

1. #### :androidMain
- It contains the Android-specific logic of the app...

2. #### :commonMain
- It houses the features of the app which have been categorised using packages in a Clean Architecture format...

2. #### :androidUnitTest
- It comprises of the Unit Tests written for the About, Settings, and Food features using MockK and Robolectric...

4. #### :iosMain
- It contains the iOS-specific logic of the app...

1. #### :main
- It provides the assets of the app such as images and the font (Caveat) using Moko Resources...

3. ### :ios
- This module utilises XCode to run and manage the iOS variant of the app...

## Procedure
- To set up the project, please follow this procedure:

1. ### The Cloning
- To clone the project from GitHub, open Android Studio and choose "Clone from Version Control"...
- When presented with the Dialog, copy and paste the following URL in the box requesting for the repository's URL: https://github.com/emmanuelmuturia/Snapbite

2. ### The Setup
- Once the project is open in Android Studio, upgrade it to the latest AGP version if prompted and follow the steps presented using the AGP Assistant...

3. ### The Launch
- If you do not have an Emulator and would like to use it for running the app, then simply go to "Device Manager" on the right-hand panel of Android Studio and click the "+" icon to add a new Virtual Device. Choose your preferred device and configurations, including the System Image if you have none installed...
- If you would like to use a Physical Device instead, here are the two options you should use to connect it to Android Studio:

1. #### USB Debugging
- Ensure that your device has "USB Debugging" enabled by first enabling Developer Options (Check your device's website for the steps you need to take for this) and navigating to the "Developer Options" section and toggling "USB Debugging" on...
- Connect your device to the laptop using a USB cable and select "Transfer Files"...
- That's it!

2. ### Wireless Debugging (Android 11+)
- To use Wireless Debugging instead, follow the above procedure but enable "Wireless Debugging" instead of "USB Debugging"...
- Once that is done, navigate to "Device Manager" and click the WiFi icon...
- You will be presented with a QR Code. On the "Developer Options" under "Wireless Debugging", choose "Pair using QR Code" and scan the QR code that has been presented to you in Android Studio. Alternatively, select the "Pair using Pairing Code" and do the same on your device still under "Wireless Debugging". Wait for Android Studio to scan and discover your device...
- That's it!

## Screenshots

| Screenshot | Description |
|---|---|
| ![Snapbite Screenshot 1](https://github.com/emmanuelmuturia/Snapbite/assets/55001497/d8c7b6ea-8416-4416-9803-65f08611fa24) | Uploading a food item... |
| ![Snapbite Screenshot 2](https://github.com/emmanuelmuturia/Snapbite/assets/55001497/24d8220c-d2d5-49dd-889e-55cff67b2f5d) | Displaying the saved food item... |
| ![Snapbite Screenshot 3](https://github.com/emmanuelmuturia/Snapbite/assets/55001497/c3762d9d-e7d0-4793-a367-9b4abe4fb445) | Healthier suggestions for the food item... |

## Trivia
- The term "Snapbite" is a portmanteau similar to Snapchat that represents the functionality of using food photos...

## Credits
- This project has been developed and supported by the following Tools, Technologies, and Libraries:

1. ### Jetpack Compose
- The project's User Interface (UI) has been built using Jetpack Compose which is Google's Kotlin-first UI toolkit...
- Snapbite has been built to support both Android and iOS platforms through Jetbrain's multiplatform technology: Kotlin Multiplatform (KMP)...

2. ### Moko
- To allow for sharing of ViewModels and assets, Moko MVVM and Moko Resources have been respectively implemented...

3. ### Voyager
- Navigation between screens was made easy by Voyager, which is a library that supports KMP projects...

4. ### Firebase
- The project's core functionalities are based on Firebase product such as Firebase Authentication (SignIn with Google), Firebase Cloud Firestore (Database), and Firebase Cloud Messaging (Push Notifications)...
- Snapbite has utilised Google's Gemini Pro AI Model to provide recommendations on making the user's food healthier using the Gemini API...

5. ### SQLDelight
- The project has utilised SQLDelight to provide an offline-first experience to the user through local storage...

6. ### MockK
- MockK has been used to mock various dependencies that were required during the Unit Tests in an effieicent and Kotlin-first manner...

7. ### Robolectric
- To simulate an Android environment, Robolectric was used and it allowed for the dependencies to work together, just like they would in a real-world Android environment...
