# s8155066Assignment2
### s8155066 Yelyeon Ann

## Overview
Android app for NIT3213 that logs in with the nit3213api, lists movies in a RecyclerView, and shows each item's details.

## Screens
- **Login**: sends the username and password to the API and receives a keypass. Shows an error message if the login fails.
- **Dashboard**: uses the keypass to get a list of entities and shows them in a RecyclerView. Each item shows a summary without the description.
- **Details**: shows all the information of the entity selected from the list, including the description.

## Tech Used
- Kotlin and XML layouts (findViewById)
- MVVM with a Repository
- Hilt for dependency injection
- Retrofit with Gson for the API calls
- Kotlin coroutines and StateFlow
- RecyclerView
- JUnit and MockK for unit tests

## Requirements
- Android Studio Quail 4 | 2026.1.4 Patch 1
- JDK 11 or higher
- minSdk 24, compileSdk 37
- Internet connection

## How to Build and Run
1. Clone the repository: `https://github.com/annyelyeon/s8155066Assignment2.git`
2. Open the project in Android Studio.
3. Wait for the Gradle sync to finish.
4. Select an emulator or a connected device and click **Run**.

## How to Log In
- Username: your student ID (without the "s")
- Password: your first name (case-sensitive)
- Endpoint: `/footscray/auth`

## API
- Base URL: `https://nit3213apinew.onrender.com`
- `POST /footscray/auth` returns a keypass
- `GET /dashboard/{keypass}` returns the list of entities (the keypass for this project is `movies`)

The server may take a while to respond on the first request, because it needs time to wake up. A loading indicator is shown while the app waits.

## Project Structure
- `data/` : data classes, API service, repository
- `di/` : Hilt module that provides the API service
- `uilogin/` : Login screen and LoginViewModel
- `uidashboard/` : Dashboard screen, DashboardViewModel, RecyclerView adapter and view holder
- `uidetails/` : Details screen

## Notes
This project uses AGP 9, so Hilt runs with KSP instead of kapt
(the lecture slides show kapt). Hilt version: 2.59.2.

## Tests
Unit tests are in the `test` folder:
- `LoginViewModelTest` (3 tests): empty fields, login success, login failure
- `DashboardViewModelTest` (2 tests): load success, load failure

To run them, right-click the `test` folder in Android Studio and choose **Run Tests**,
or run `gradlew.bat test` (Windows) / `./gradlew test` (Mac, Linux) in the project folder.