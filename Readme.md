# Cucumber

## Cucumber Test Case

This is a testing framework that is used to write BDD test cases.

## Cucumber and Spring Boot

### Step 1: Add cucumber related dependencies

 <!-- Cucumber dependencies -->

        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-spring</artifactId>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java8</artifactId>
        </dependency>

        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-core</artifactId>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
        </dependency>

### Step 2: Create feature file for the controller class

### Step 3: Create the StepDefinition class for feature file scenario

### Step 4: Create Cucumber Runner class.