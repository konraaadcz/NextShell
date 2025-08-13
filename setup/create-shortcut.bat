@echo off
set "APP_NAME=NextShell Terminal"
set "SCRIPT_DIR=%~dp0"
set "TARGET_BAT=%SCRIPT_DIR%..\start.bat"
set "DESKTOP_PATH=%USERPROFILE%\Desktop"
set "SHORTCUT_PATH=%DESKTOP_PATH%\%APP_NAME%.lnk"
echo Set oWS = WScript.CreateObject("WScript.Shell") > "%TEMP%\create_shortcut.vbs"
echo Set oLink = oWS.CreateShortcut("%SHORTCUT_PATH%") >> "%TEMP%\create_shortcut.vbs"
echo oLink.TargetPath = WScript.CreateObject("Scripting.FileSystemObject").GetAbsolutePathName("%TARGET_BAT%") >> "%TEMP%\create_shortcut.vbs"
echo oLink.WorkingDirectory = WScript.CreateObject("Scripting.FileSystemObject").GetParentFolderName(WScript.CreateObject("Scripting.FileSystemObject").GetAbsolutePathName("%TARGET_BAT%")) >> "%TEMP%\create_shortcut.vbs"
echo oLink.Save >> "%TEMP%\create_shortcut.vbs"
cscript //nologo "%TEMP%\create_shortcut.vbs"
del "%TEMP%\create_shortcut.vbs"
echo.
echo New shortcut created: "%APP_NAME%"
pause
