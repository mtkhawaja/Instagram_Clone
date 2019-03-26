# Instagram-Clone
Submitted by: **Muneeb Khawaja**

**Instagram-Clone** is a photo sharing app similar to Instagram but using Parse as its backend.

Time spent: **7** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can sign up to create a new account using Parse authentication.
* [x] User can log in and log out of his or her account.
* [x] The current signed in user is persisted across app restarts.
* [x] User can take a photo, add a caption, and post it to "Instagram".

The following **optional** features are implemented:

* [ ] User sees app icon in home screen and styled bottom navigation view
* [ ] Style the feed to look like the real Instagram feed.
* [ ] After the user submits a new post, show an indeterminate progress bar while the post is being uploaded to Parse.

The following **additional** features are implemented:
* [x] Added a menu to the action bar, with currently just the logout/exit button. 
* [x] The "Power Off/On" icon has a different function depending on where it is clicked. That is, if it is clicked when the context is the Login activity, 
	  then it exits the app, and if it is clicked during the Main Activity, then it simply logs the user out. 
* [x] Adopted the Instagram Color scheme. 	  

## Video Walkthrough

<img src="Instagram_1.gif" title = 'Video Walkthrough' width='' alt='Video Walkthrough' ><br>
GIF created with [ShareX](https://getsharex.com/).

## Notes
I was trying to implement a much sleeker and nicer looking action bar, however, I faced diffeculties resizing menu item buttons and was unable to implement everything.
Most of the trouble I faced was encountered while setting up the Parse Server. On the Console, it would say that the Parse Dashboard is running at http://0.0.0.0:4040/
,however, that resulted in a ERR_ADDRESS_INVALID. It took me a while before figuring out that the Parse Dashboard was available at http://localhost:4040/ and not http://0.0.0.0:4040/.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

### License

    Copyright [2019] [Muneeb T. Khawaja]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

