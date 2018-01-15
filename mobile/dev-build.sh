#!/bin/bash
set -eux

cordova build android; cp platforms/android/build/outputs/apk/android-debug.apk publish-folder/
