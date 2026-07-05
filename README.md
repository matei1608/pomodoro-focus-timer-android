# Pomodoro Focus Timer

A minimal, no-nonsense Pomodoro timer app for Android, built with **Kotlin** and **Jetpack Compose**.

I built this project as a hands-on way to get comfortable with modern Android development—specifically declarative UI design, Compose state management, and running background timers safely with coroutines.

---

## What it does

Instead of bloating the app with stats, logins, or ads, this focuses on the core Pomodoro technique:
* **25-minute focus intervals** right out of the box.
* **Simple controls:** Start, Pause, and Reset.
* **Session tracking:** Automatically increments a daily counter every time an interval finishes so you can track your flow.
* **Zero clutter:** A single-screen layout designed with Material 3 guidelines.

---

## Under the Hood (Tech & Architecture)

The entire app is written in a single activity using pure Jetpack Compose. Here are the core concepts used to make it tick:

* **Language:** [Kotlin](https://kotlinlang.org/)
* **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
* **State Management:** Used `remember { mutableStateOf(...) }` to make sure UI components (like the countdown text and button labels) instantly react and recompose whenever the timer ticks or the user presses a button.
* **Coroutines for Timing:** Instead of legacy threads or heavy service workers, the countdown is powered by a `LaunchedEffect` block combined with Kotlin's `delay(1000L)`. This keeps the timer accurate, tied safely to the composable's lifecycle, and prevents UI freezing.

---

## Running the Project Locally

If you want to run this code on your own machine or device:

1. Clone the repo:
   git clone https://github.com/matei1608/pomodoro-focus-timer-android.git

2. Open the project folder in **Android Studio**.
3. Let Gradle sync and download the dependencies (this might take a minute on the first run).
4. Hit **Run (Shift + F10)** using an Android Emulator or a physical device connected via USB Debugging.

*Note: The app targets Android 8.0 (API 26) or higher, which covers virtually all modern Android devices.*

---

