@echo off
powershell -executionpolicy Bypass -Command "Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))"
choco install mkcert
cd ..\..\src\fit-style-frontend
del .env
mkcert -install
mkcert -cert-file %USERPROFILE%\cert.pem -key-file %USERPROFILE%\key.pem localhost

@echo HTTPS=true >> .env
@echo SSL_CRT_FILE=%USERPROFILE%\cert.pem >> .env
@echo SSL_KEY_FILE=%USERPROFILE%\key.pem >> .env

cd ..\fit-style-backend\src\main\resources\secure
	
mkcert -pkcs12 localhost
pause