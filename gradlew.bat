@echo off
set WRAPPER_VERSION=7.4
set GRADLE_HOME=%~dp0\gradle\wrapper\gradle-wrapper.jar
java -Dorg.gradle.wrapper.version=%WRAPPER_VERSION% -cp "%GRADLE_HOME%" org.gradle.wrapper.GradleWrapperMain %*
