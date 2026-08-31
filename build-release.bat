@echo off
setlocal

echo ============================================
echo  Building release: Paper plugin
echo ============================================
pushd paper-plugin
call mvn package
if errorlevel 1 goto :fail
popd

echo.
echo ============================================
echo  Building release: Fabric mod
echo ============================================
pushd fabric-mod
call .\gradlew.bat build
if errorlevel 1 goto :fail
popd

echo.
echo Both release builds succeeded.
echo   Paper:  paper-plugin\target\
echo   Fabric: fabric-mod\build\libs\
pause
exit /b 0

:fail
popd
echo.
echo Build FAILED - see output above.
pause
exit /b 1
