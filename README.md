# web-eng-project

# Requirements

-npm, vue.js, java, docker, maven

# Setup

**Frontend:** 

    run "npm run serve" inside the frontend folder

**Backend:** 

    run "docker-compose up -d" inside the backend folder 

    run the SpringApplication -> WebEngBackendApplication.java

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