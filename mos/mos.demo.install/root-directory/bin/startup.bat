cd ..
SET APP_HOME=%cd%
echo Starting from %APP_HOME% ...
java -Dfelix.config.properties=file:\%APP_HOME%\conf\config.properties -Dfelix.system.properties=file:\%APP_HOME%\conf\system.properties -Dapp.home=%APP_HOME% -jar %APP_HOME%\bin\mos.felix.framework-4.0.3.jar