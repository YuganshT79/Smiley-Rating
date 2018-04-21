# **Smiley-Rating**
Smiley-Rating is a custom way to greet your users with a smile :smile: while they give awesome Feedback about your app.

## Inspiration
Smiley-Rating was inspired by a UI/UX Demo by [@Ashok Mahiwal](https://www.linkedin.com/in/ashok-mahiwal-04758565/?miniProfileUrn=urn%3Ali%3Afs_miniProfile%3AACoAAA3J9fcBkBxr9yih5giAef-f9uuqCLx-X9g&lipi=urn%3Ali%3Apage%3Ad_flagship3_detail_base%3BOWGXbl%2FbQnOorA%2FpVErTvw%3D%3D)  

Here is the Demo Video

![Alt Text](https://raw.githubusercontent.com/YuganshT79/Smiley-Rating/master/images/demo.gif)

## Screenshots of the App

| Sad Face| Neutral face | Slightly Happy Face | Happy Face  | Amazing Face|
| :---:|:-------------: |:-------------:| :-----:|:---:|
|![Sad Face](https://i.imgur.com/1qSpXVDh.jpg)|![Neutral Face](https://i.imgur.com/zMyFCLzh.jpg)|![Slight Smile Face](https://i.imgur.com/PmV5Wrjh.jpg)|![Happy Face](https://i.imgur.com/jtjZ4EXh.jpg)|![Amaing Face](https://i.imgur.com/IvxlHNFh.jpg)|

## Library Usage

Add the Dependency

``` groovy
dependencies {
    implementation 'my dependency'
}
```

Add `SmileyRating` to your layout

``` xml
    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <com.yugansh.tyagi.smileyrating.SmileyRating
            android:id="@+id/smiley_view"
            android:layout_width="match_parent"
            android:layout_centerHorizontal="true"
            android:layout_height="400dp"
            app:default_rating="2"/>
    </RelativeLayout>
```
