# TxtLogSdk
SDK for write application logs into text file.



To get a Git project into your build:

Step 1. Add the JitPack repository to your build file
gradle
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Shekhar23:TxtLogSdk:Tag'
	}

Step 3. Initialize sdk
  
	
    /**
     * This method call is needed only for the initialize sdk
     * @param context : Application context need for file operation.
     * @param isDebugModeOnly : Set it true if you wants log writing for debug mode only.
     */
     
     TxtLog.sdkInitialize(Application context, boolean isDebugModeOnly);
  
Step 4. Use sdk with write method 

	
    /**
     * This method is used  for write log with TAG
     * @param tag : Tag like class name, or method name.
     * @param message : what you wants to write in file 
     */
     
     TxtLog.write(String tag, String message) 


https://jitpack.io/#Shekhar23/TxtLogSdk

[![](https://jitpack.io/v/Shekhar23/TxtLogSdk.svg)](https://jitpack.io/#Shekhar23/TxtLogSdk)

www.shekharpande.co.in | www.shekharpande.com
