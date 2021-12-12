@echo off

cd ..\..\src\fit-style-frontend

del .env

cd src\config\consts

powershell -Command "(gc urlsApi.js) -replace 'https://localhost', 'http://localhost' | Out-File -encoding ASCII urlsApi.js"

cd ..\..\..\..\fit-style-backend\src\main\resources

powershell -Command "(gc application.properties) -replace 'server.ssl', '#server.ssl' | Out-File -encoding ASCII application.properties"

cd ..\java\ru\project\fitstyle\controller

powershell -Command "(gc AuthController.java) -replace 'https://localhost:3000', 'http://localhost:3000' | Out-File -encoding ASCII AuthController.java"
powershell -Command "(gc CoachController.java) -replace 'https://localhost:3000', 'http://localhost:3000' | Out-File -encoding ASCII CoachController.java"
powershell -Command "(gc GetImageController.java) -replace 'https://localhost:3000', 'http://localhost:3000' | Out-File -encoding ASCII GetImageController.java"
powershell -Command "(gc NewsController.java) -replace 'https://localhost:3000', 'http://localhost:3000' | Out-File -encoding ASCII NewsController.java"
powershell -Command "(gc PermissionController.java) -replace 'https://localhost:3000', 'http://localhost:3000' | Out-File -encoding ASCII PermissionController.java"
powershell -Command "(gc ProfileController.java) -replace 'https://localhost:3000', 'http://localhost:3000' | Out-File -encoding ASCII ProfileController.java"
powershell -Command "(gc SubscriptionTypeController.java) -replace 'https://localhost:3000', 'http://localhost:3000' | Out-File -encoding ASCII SubscriptionTypeController.java"
powershell -Command "(gc TestController.java) -replace 'https://localhost:3000', 'http://localhost:3000' | Out-File -encoding ASCII TestController.java"
powershell -Command "(gc TrainingController.java) -replace 'https://localhost:3000', 'http://localhost:3000' | Out-File -encoding ASCII TrainingController.java"
powershell -Command "(gc UserController.java) -replace 'https://localhost:3000', 'http://localhost:3000' | Out-File -encoding ASCII UserController.java"

pause