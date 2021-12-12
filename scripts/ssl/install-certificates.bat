@echo off

powershell -executionpolicy Bypass -Command "Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))"
choco install mkcert

mkcert -install
mkcert -cert-file %USERPROFILE%\cert.pem -key-file %USERPROFILE%\key.pem localhost

cd ..\..\fit-style-backend\src\main\resources\secure
mkcert -pkcs12 localhost

pause