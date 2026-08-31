@echo off
setlocal enabledelayedexpansion

echo ============================================
echo  Building SNAPSHOT: Paper plugin
echo ============================================
pushd paper-plugin
for /f "tokens=3 delims=<>" %%V in ('findstr "<revision>" pom.xml') do set "PAPER_VERSION=%%V"
call mvn package "-Drevision=!PAPER_VERSION!-SNAPSHOT"
if errorlevel 1 goto :fail
popd

echo.
echo ============================================
echo  Building SNAPSHOT: Fabric mod
echo ============================================
pushd fabric-mod
call .\gradlew.bat build "-PversionSuffix=-SNAPSHOT"
if errorlevel 1 goto :fail
popd

echo.
echo Both SNAPSHOT builds succeeded.
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
