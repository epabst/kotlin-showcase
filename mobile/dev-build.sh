#!/bin/bash
set -eux

cd ..
./gradlew build
cd -
cordova build android; cp platforms/android/build/outputs/apk/android-debug.apk publish-folder/
