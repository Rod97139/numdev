# Yoga

## Start the project

Git clone:

> git clone https://github.com/Rod97139/numdev.git

Go inside folder:

> cd yoga


### Back-end

- Java version 8 is required.

Go inside back folder:

> cd back

Install the database:

- Make shure you have MySQL version ^8.0 installed and running on port 3306
- You don't have nothing to do, the database will be created and filled with data automatically after running the back-end application
- By default the admin account is:
  - login: yoga@studio.com
  - password: test!1234

> mvn clean install
> 
> mvn spring-boot:run

Run tests:

> mvn test

Run tests coverage:

> mvn clean verify
> 
> mvn jacoco:report

### Front-end

install node version 16.14.2

> nvm install 16.14.2

Go inside front folder:

> cd front

Install dependencies:

> npm install

Launch Front-end:

> npm run start;

Run jest tests:

> jest

> jest --watch

Run jest coverage:

> jest --coverage

Run cypress tests:

> npm run cypress:open

Run cypress coverage:

- first stop the angular local server

Then:

> npm run e2e:coverage


### Postman collection

For Postman import the collection

> ressources/postman/yoga.postman_collection.json

### MySQL

SQL script for creating the schema is available `ressources/sql/script.sql`

### Test coverage

Report with istanbul is available here:

> front/coverage/lcov-report/index.html
