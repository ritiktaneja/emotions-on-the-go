# App Description

This repository contains the modified versions of the [Emotion-on-the-go app](https://github.com/TKosch/emotions-on-the-go) by [Tkosch](https://github.com/TKosch) which is based on the paper "Emotions on the Go: Mobile Emotion Assessment in Real-Time using Facial Expressions".

DOI: https://doi.org/10.1145/3399715.3399928


<!-- // info of different branches: -->
There are 3 different branches, each containing a slight variation of the app.
<!--  Multi-options selection of user emotions, Single select, app usage data -->
1) Branch 1 contains the implementation of the app which provides multiple options for the actual(ground-truth) emotions of the user.
2) Branch 2 contains the implementation of the app which provides single options for the actual emotion of the user. 
3) Branch 3 contatins an upgraded version of the branch 2 app in which the app usage data of the user is also sent to the database. 

The app requires at least Android 5.0 (Lollipop).

## Installation
<!-- installing android studio -->
### For installing android studio follow this guide: https://developer.android.com/studio/install

### Clone the repository
```
    git clone https://github.com/ritiktaneja/emotions-on-the-go.git
```
### Open the project in Android studio
- Wait for the gradle scripts to execute.
<!-- // wait for gradle build -->

<!-- // Steps to create a new database: -->
## Create a Firebase database
Adding Firebase to your app involves tasks both in the Firebase console and in your open Android project (for example, you download Firebase config files from the console, then move them into your Android project). 
Login in [Google Firebase](https://firebase.google.com/) and go to the console.

Step 1) Create a Firebase project

Step 2: Register your app with Firebase
To use Firebase in your Android app, you need to register your app with your Firebase project. Registering your app is often called "adding" your app to your project.
    1) Go to the Firebase console.
    2) In the center of the project overview page, click the Android icon (plat_android) or Add app to launch the setup workflow.
    3) Enter your app's package name in the Android package name field.
    4) Click Register app.
<!-- // update the database -->

<!-- // Building an apk -->

<!-- // FER analytics -->

## Directions to use the app


## Citing the App and the Paper

Below are the BibTex entries to cite the app and the paper

```
@misc{kosch:emotions,
  author = {Kosch, Thomas and Hassib, Mariam  and Reutter, Robin and Alt, Florian},
  title = {Emotions on the Go: Mobile Emotion Assessment in Real-Time using Facial Expressions},
  year = {2020},
  publisher = {GitHub},
  journal = {GitHub repository},
  howpublished = {\url{https://github.com/TKosch/emotions-on-the-go}}
}
```

```
@inproceedings{kosch2020emotions,
    author = {Kosch, Thomas and Hassib, Mariam and Reutter, Robin and Alt, Florian},
    title = {Emotions on the Go: Mobile Emotion Assessment in Real-Time using Facial Expressions},
    booktitle = {Proceedings of the 2020 International Working Conference on Advanced Visual Interfaces},
    series = {AVI '20},
    year = {2020},
    isbn = {978-1-4503-7535-1/20/09},
    location = {Salerno, Italy},
    numpages = {9},
    doi = {10.1145/3399715.3399928},
    publisher = {ACM},
    address = {New York, NY, USA}
} 
```
