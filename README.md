# Testez-une-application-full-stack

# Yoga

## Start the project

Git clone:

> [git clone https://github.com/OpenClassrooms-Student-Center/P5-Full-Stack-testing](https://github.com/Alcrai/Testez-une-application-full-stack/](https://github.com/Alcrai/Testez-une-application-full-stack.git)

## Installer le Back end

Go inside Folder

> cd front

Install dependencies:

> mvn clean install

Launch the backend server:

>mvn spring-boot:run

Access the backend API at http://localhost:8080.

# For launch and generate the jacoco code coverage:

mvn clean test

## Front End

Go inside folder:

> cd front

Install dependencies:

> npm install

Launch Front-end:

> npm run start;


## Ressources

### Mockoon env 

### Postman collection

For Postman import the collection

> ressources/postman/yoga.postman_collection.json 

by following the documentation: 

https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman


### MySQL

SQL script for creating the schema is available `ressources/sql/script.sql`

By default the admin account is:
- login: yoga@studio.com
- password: test!1234


### Test

#### E2E

Launching e2e test:

> npm run e2e

Generate coverage report (you should launch e2e test before):

> npm run e2e:coverage

Report is available here:

> front/coverage/lcov-report/index.html

#### Unitary test

Launching test:

> npm run test

for following change:

> npm run test:watch
