@echo off

echo Starting Config Server...
start cmd /k "cd config-server && mvn spring-boot:run"
timeout /t 5

echo Starting Discovery Server...
start cmd /k "cd Discovery && mvn spring-boot:run"
timeout /t 5

echo Starting Customer Service...
start cmd /k "cd Customer && mvn spring-boot:run"
timeout /t 5

echo Starting Product Service...
start cmd /k "cd Product && mvn spring-boot:run"