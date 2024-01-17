# web-eng-project

# Requirements

-npm, vue.js, java, docker, maven

# Startup Guide 

**Step 1:**

Start the mariadb Container on Docker 

    docker-compose up -d

**Step 2:**

Run the Spring Application "WebEngBackendApplication.java"
This should then run at http://localhost:8080

**Step 3:**

Start the Frontend Application

    npm run serve

This should then run at http://localhost:8081



# Setup

**Frontend:** 

    run "npm run serve" inside the frontend folder

**Backend:** 

    run "docker-compose up -d" inside the backend folder 

    run the SpringApplication -> WebEngBackendApplication.java

    -> If its the first time starting the apllication, set the "spring.jpa.hibernate.ddl-auto" propery in the application.properties to "create"
    -> Afterwards "update" is sufficient

# Database Setup:

    <insert sql script to setup the tables>


# MariaDB startup

    Open Terminal in running Docker Container
    > mariadb -u root -p
    > root
    > show databases;
        Wenn "webshop" noch nicht existiert:
        > create database webshop;
    > use webshop;