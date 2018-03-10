echo %1
c:
cd "%~dp0"
cd
dir
java -jar scratchrunner.jar -f %1
pause