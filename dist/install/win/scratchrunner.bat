echo %1
c:
cd "%~dp0"
cd
dir
java -jar scratchrunner.jar %1
pause