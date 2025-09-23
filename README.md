# InfoSec: Lab1

## Start-up guide
In order to boot the application follow those steps below:
1. Ensure that the docker daemon is up and running
2. Using the gradle wrapper provided with the application build
the application docker image with the `bootBuildImage` task
    ```shell
    ./gradlew clean bootBuildImage
    ```
3. Make a copy of the [.env.dev](./.env.dev) to get away from 
potential problems and change that new file so the application
would be perfectly set up
4. Now run provided docker compose file which will pull,
set up & run the postgresql and the application
    ```shell
    docker compose --env-file <your .env.dev copy filename> up
    ```
