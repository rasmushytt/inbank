# InBankBeta

This project uses Java 20, Spring Boot and Maven to create a REST API for a bank loan calculator. Frontend is created with Vue.js, runs on Vite.

# How to run the project:

I used Intellij IDEA to code this project. 

Back-end

    Install Maven and Java 20.
    Run Maven BE configuration.
    Run the project using Spring Boot Start BE configuration.

    P.S Maven and Spring Boot configurations are stored as a project file. If they don't work, try the following:
            Right click inBankHomeTask/endPoint/InBankApiEndPoint/pom.xml and click "Add as Maven project"
            Run maven clean install
            Go to project structure -> Modules -> Dependencies -> press + and find .jar file from target folder. Tick the box and press apply.
            Set up Spring Boot configuration with the main class being `ApiEndPoint.SpringBootAppForInBankApiEndPoint`. Use SDK of inbank module.
            Run the project using Spring Boot configuration.

Front-end
    
    From terminal go to root folder(inBank/inBankHomeTask)
    npm install (if you don't have npm installed, install it first. I had to use "npm install vite" and "npm install --force" from my other computer)
    npm run dev

You should now have a working loan calculator app


## Creator
Rasmushytt
