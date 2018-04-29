# **Smiley-Rating**

[![Version](https://jitpack.io/v/YuganshT79/Smiley-Rating.svg)](https://jitpack.io/#YuganshT79/Smiley-Rating)
[![API-Level](https://img.shields.io/badge/API-16%2B-orange.svg)](https://android-arsenal.com/api?level=16)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.me/YuganshTyagi/5)

Smiley-Rating is a custom way to greet your users with a smile :smile: while they give awesome Feedback about your app.

## Inspiration

Smiley-Rating was inspired by a UI/UX Demo by [@Ashok Mahiwal](https://www.linkedin.com/in/ashok-mahiwal-04758565/?miniProfileUrn=urn%3Ali%3Afs_miniProfile%3AACoAAA3J9fcBkBxr9yih5giAef-f9uuqCLx-X9g&lipi=urn%3Ali%3Apage%3Ad_flagship3_detail_base%3BOWGXbl%2FbQnOorA%2FpVErTvw%3D%3D)

Here is the Demo Video

![Demo Video](https://i.imgur.com/PHn5UWu.gif)

## Screenshots of the App

| Sad Face| Neutral face | Slightly Happy Face | Happy Face  | Amazing Face|
| :---:|:-------------: |:-------------:| :-----:|:---:|
|![Sad Face](https://i.imgur.com/1qSpXVDh.jpg)|![Neutral Face](https://i.imgur.com/zMyFCLzh.jpg)|![Slight Smile Face](https://i.imgur.com/PmV5Wrjh.jpg)|![Happy Face](https://i.imgur.com/jtjZ4EXh.jpg)|![Amaing Face](https://i.imgur.com/IvxlHNFh.jpg)|

## Library Usage

Add **JitPack** repository to your `build.gradle` file

``` gradle
allprojects {
	repositories {
	     maven { url 'https://jitpack.io' }
	}
}
```

Add the Dependency 

``` gradle
dependencies {
    implementation 'com.github.YuganshT79:Smiley-Rating:{LATEST_VERSION}'
}
```

Add `SmileyRatingView` to your layout

``` xml
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <com.yugansh.tyagi.smileyrating.SmileyRatingView
            android:id="@+id/smiley_view"
            android:layout_width="match_parent"
            android:layout_height="400dp"/>
    </RelativeLayout>
```

In your Activity

``` java
SmileyRatingView smileyRatingView = findViewById(R.id.smiley_rating);
ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                smileyRatingView.setSmiley(rating);
            }
        });
```

## Customize the Smiley

| Property | Description |
|:---:|:---:|
|app:face_color| Changes the face background color of the Smiley|
|app:eyes_color| Changes the eye color of the Smiley|
|app:tongue_color| To change the tongue's color of Smiley|
|app:mouth_color | To change mouth color of the Smiley|
|app:default_rating | Default smiley( keep same as rating bar's `rating` )

## Contribution

If you want to contribute to the Project.
- Star :star: the repository
- Fork the repository
- Make a new `branch` with dscriptive name
- Implement your feature
- Make a pull request to the repo
- Thanks for Contribution, now smile :smile: an rejoice

## Things to implement

- ~~Add smiley's for all ratings~~
- ~~Make Smiley independent of Desity~~
- ~~Implemented animation in eyes~~
- Animate change of Smiley mouth

## License

``` text
Copyright 2018 Yugansh Tyagi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```