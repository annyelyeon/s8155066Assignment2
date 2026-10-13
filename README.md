# s8155066Assignment2
### s8155066 Yelyeon Ann


## Overview
Android app for NIT3213 that logs in with the nit3213api, lists movies in a RecyclerView, and shows each item's details.

## Screens
- **Login**:  sends the username and password to the API and receives a keypass.
- **Dashboard**: uses the keypass to get a list of entities and shows them in a RecyclerView.
- **Details**: shows all the information of the entity selected from the list.

## Tech Used
- Kotlin and XML layouts (ViewBinding)
- Hilt for dependency injection
- Retrofit with Gson for the API calls
- RecyclerView
- ViewModel
- JUnit and MockK for unit tests

## Requirements
- Android Studio (version you use)
- JDK 11 or higher
- minSdk 24, compileSdk 37

## How to Build and Run
1. Clone the repository
2. Open the project in Android Studio.
3. Wait for Gradle sync to finish.
4. Select an emulator or a connected device and click **Run**.

## How to Log In
- Username: your student ID (without the "s")
- Password: your first name
- Endpoint: `/footscray/auth`

## API
- Base URL: `https://nit3213apinew.onrender.com`
- `POST /footscray/auth` returns a keypass
- `GET /dashboard/movies` returns the list of entities

The server may take a while to respond on the first request.

## Notes
This project uses AGP 9, so Hilt runs with KSP instead of kapt
(the lecture slides show kapt). Hilt version: 2.59.2.

## Tests
Run `./gradlew test` or right-click the test folder in Android Studio and choose Run.