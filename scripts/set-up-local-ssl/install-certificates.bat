@echo off

mkcert -install

cd ..\..\src\fit-style-backend\src\main\resources\secure
mkcert -pkcs12 localhost

mkcert -cert-file %USERPROFILE%\cert.pem -key-file %USERPROFILE%\key.pem localhost

pause
