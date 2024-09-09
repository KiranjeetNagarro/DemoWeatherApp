# DemoWeatherApp android app with Room Database Cache

This is an Android application that fetches weather data from an API and caches it locally using Room database for offline access. The project includes test cases to ensure proper functionality of both the data layer and UI layer.

## Features [weatherApp.webm](https://github.com/user-attachments/assets/72751baf-0558-4e3d-8fc4-802e9dcec381)


- Fetches weather data from OpenWeatherMap API.
- Caches weather data using Room database for offline support.
- Displays weather forecast including temperature, humidity, and weather conditions.
- Unit tests for data and UI layers.
- Follows MVVM architecture.
- Follows clean archtecture with data and domain layers
- Error handling for network issues.

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Android Studio installed.
- Basic understanding of Kotlin, Room, and Retrofit.
- OpenWeatherMap API Key. You can get one by registering [here](https://openweathermap.org/).

### Libraries and Dependencies
1. [Support libraries](https://developer.android.com/jetpack/androidx/): appcompat / recyclerview / constraintlayout
2. Square[Retrofit](https://github.com/square/retrofit)  [Okhttp](https://github.com/square/okhttp) [Logging-Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
3. [Gson](https://github.com/google/gson) - convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object
4. [Room Database](https://developer.android.com/jetpack/androidx/releases/room) - Database used for cache
5. [Hilt Dependency](https://developer.android.com/jetpack/androidx/releases/hilt) - To enable dependency injection of certain classes from the androidx libraries.

### Functionality
1. Search any city current weather with temperature, humidity, wind speed and weather description
2. View searched city next 5 days weather forecast with minimum and maximum temperatures
3. Search in offline mode to get faster results.
4. Delete recent search cities or All cities

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/KiranjeetNagarro/DemoWeatherApp.git

### Android Studio (Recommended)
(These instructions were tested with latest Android Studio version)

Open Android Studio and select File->Open... or from the Android Launcher select Import project and navigate to the root directory of your project.
Select the directory or drill in and select the file build.gradle in the cloned repo.
Click 'OK' to open the the project in Android Studio.
A Gradle sync should start, but you can force a sync and build the 'app' module as needed.
### Gradle (command line)
Build the APK: ./gradlew build

### Test cases
1. Run test  with Gradle
[./gradlew test] - local test run
2. Run tests with Android studio to generate code coverage reports

### Running the Sample App
Connect an Android device to your development machine.

### Android Studio
Select Run -> Run 'app' (or Debug 'app') from the menu bar
Select the device you wish to run the app on and click 'OK'
