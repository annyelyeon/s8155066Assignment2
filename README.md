# s8155066Assignment2
### s8155066 Yelyeon Ann


## Overview
Android app for NIT3213 that logs in with the nit3213api, lists movies in a RecyclerView, and shows each item's details.

## Features
- Login screen with error handling
- Dashboard screen listing entities in a RecyclerView
- Details screen showing all entity information, including the description

## Tech Stack
- Kotlin, XML layouts with ViewBinding
- MVVM architecture
- Hilt (dependency injection)
- Retrofit + OkHttp + Gson
- RecyclerView
- JUnit, MockK, kotlinx-coroutines-test

## Requirements
- Android Studio (version you use)
- JDK 11 or higher
- minSdk 24, compileSdk 37
- Internet connection

## How to Build and Run
1. Clone the repository: `git clone <your-repo-url>`
2. Open the project in Android Studio.
3. Wait for Gradle sync to finish.
4. Select an emulator or a connected device and click **Run**.

## How to Log In
- Username: your student ID (without the "s")
- Password: your first name (case-sensitive)
- Endpoint: `/<your-location>/auth`

## API
- Base URL: `https://nit3213apinew.onrender.com`
- `POST /footscray/auth` returns a keypass
- `GET /dashboard/movies` returns the list of entities

The server may take a while to respond on the first request.

## Project Structure
- `data/` models, API service, repository
- `di/` Hilt modules
- `ui/` login, dashboard and details screens with ViewModels

## Running Tests
`./gradlew test`