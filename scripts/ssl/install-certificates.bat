@echo off

cd ..\..\src\fit-style-backend\src\main\resources\secure
mkcert -pkcs12 localhost

mkcert -install
mkcert -cert-file %USERPROFILE%\cert.pem -key-file %USERPROFILE%\key.pem localhost

pause
