
# Sample Headliness App

This repository contains a detailed sample app that implements MVVM architecture 
using Room, LiveData, Retrofit,DataBinding, ViewModel,RxJava.

# Highlights

1. MVVM Architectural pattern
2. Offline Support
3. Jetpack Components - Room, LiveData, DataBinding, Paging
4. Unit test demonstration using JUnit and Mockito
5. UI unit test demonstartion using Espresso
6. Networking - Retrofit with Coroutines
7. Dependency Injection - Hilt

The application has been built with **offline support**. It has been designed using **Android Architecture components** with **Room** for offline data caching. The application is built in such a way that whenvever there is a service call, the result will be stored in local database.

The whole application is built based on the MVVM architectural pattern.

# Application Architecture
![alt text](https://cdn-images-1.medium.com/max/1600/1*OqeNRtyjgWZzeUifrQT-NA.png)

The main advatage of using MVVM, there is no two way dependency between ViewModel and Model unlike MVP. Here the view can observe the datachanges in the viewmodel as we are using LiveData which is lifecycle aware. The viewmodel to view communication is achieved through observer pattern (basically observing the state changes of the data in the viewmodel).

![image](https://i.postimg.cc/HsyGFMc8/device-2021-06-24-084726.png)
![image](https://i.postimg.cc/nzP5PVn1/device-2021-06-24-084806.png)


#### The app has following packages:
1. **models**: It contains all the data accessing and manipulating components.
2. **api**: It contains network calls and its interface.
3. **database**: It contains entity and dao class for storing the data in local
4. **helper**: It contains constant variables and common methods.
5. **ui**: View classes along with their corresponding ViewModel.


# Programming Practices Followed
a) Android Architectural Components <br/>
b) Hilt for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp <br/>
e) Room for data caching <br/>
f) JUnit and Mockito for Unit testing <br/>
d) Repository pattern <br/>

#### Classes have been designed in such a way that it could be inherited and maximize the code reuse.
### Library reference resources:
1. Room: https://developer.android.com/topic/libraries/architecture/room.html

