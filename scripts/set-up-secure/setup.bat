@echo off
Powershell.exe  -executionpolicy Bypass -File download-chocolate.ps1
choco install mkcert
cd ../../src/fit-style-frontend
del .env
mkcert -install
mkcert -cert-file C:/Users/%USERNAME%/cert.pem -key-file C:/Users/%USERNAME%/key.pem localhost

@echo HTTPS=true >> .env
@echo SSL_CRT_FILE=C:/Users/%USERNAME%/cert.pem >> .env
@echo SSL_KEY_FILE=C:/Users/%USERNAME%/key.pem >> .env

cd ../fit-style-backend/src/main/resources/secure
	
mkcert -pkcs12 localhost
pause