### Pre-requisites:
1. **Install Java 17 or above**:
   - Download and install Java Development Kit (JDK) version 17 or higher from [Oracle’s website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/).

2. **Install Selenium WebDriver v4.26**:
   - You can add the Selenium dependency to the `pom.xml` file (as you mentioned) for Maven projects. Ensure the version is v4.26 or the latest available.
   - Maven dependency example:
     ```xml
     <dependency>
         <groupId>org.seleniumhq.selenium</groupId>
         <artifactId>selenium-java</artifactId>
         <version>4.26.0</version>
     </dependency>
     ```

3. **Install TestNG**:
   - TestNG is needed for running the test cases.
   - Add the TestNG dependency to the `pom.xml` file:
     ```xml
     <dependency>
         <groupId>org.testng</groupId>
         <artifactId>testng</artifactId>
         <version>7.7.1</version>
         <scope>test</scope>
     </dependency>
     ```

4. **IDE Installation**:
   - Install an Integrated Development Environment (IDE) like **IntelliJ IDEA** or **Eclipse** to write, compile, and run the Java code.
   - For **IntelliJ IDEA**, you can install the Selenium and TestNG plugins if they are not already installed.

5. **Chrome Browser**:
   - Ensure that the **Chrome browser** is installed and up to date on your machine. Selenium will require it to launch and interact with the web pages.
   - You can download the latest version of Chrome from the official [Google Chrome website](https://www.google.com/chrome/).

6. **ChromeDriver**:
   - **ChromeDriver** is needed to allow Selenium to interact with the Chrome browser. Download the version of **ChromeDriver** that corresponds to your version of Chrome from [ChromeDriver download page](https://sites.google.com/a/chromium.org/chromedriver/).
   - Make sure that **ChromeDriver** is added to your system's PATH or that the location is specified in your code.

7. **Create the `pom.xml` file**:
   - Ensure you have the necessary dependencies in your `pom.xml` file to enable both Selenium and TestNG.
   - Example of the `pom.xml` content for Selenium and TestNG dependencies:
     ```xml
     <dependencies>
         <!-- Selenium dependency -->
         <dependency>
             <groupId>org.seleniumhq.selenium</groupId>
             <artifactId>selenium-java</artifactId>
             <version>4.26.0</version>
         </dependency>

         <!-- TestNG dependency -->
         <dependency>
             <groupId>org.testng</groupId>
             <artifactId>testng</artifactId>
             <version>7.7.1</version>
             <scope>test</scope>
         </dependency>
     </dependencies>
     ```

### Steps to Run the Code:

1. **Open your IDE**:
   - Launch your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
   - Make sure the project has the `pom.xml` file configured with the necessary dependencies (Selenium, TestNG).

2. **Build the Project**:
   - If you're using **Maven**, right-click on the `pom.xml` file and select **Maven > Reload Project** in IntelliJ IDEA or use the command `mvn clean install` to install the necessary dependencies.

3. **Set Up the WebDriver**:
   - Download the **ChromeDriver** and place it in a directory.
   - Ensure that the path to `chromedriver.exe` is specified in your code or added to the system’s PATH.

   Example for setting ChromeDriver path in Java code:
   ```java
   System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
   ```

4. **Run the Test**:
   - **Option 1**: Using the IDE:
     - Using **TestNG** as a test framework, right-click on the test file (e.g., `FitPeoTest.java`) and select **Run**.

5. **Execution Feedback**:
   - Once the test runs, the **Chrome browser** will open automatically, and you will see the actions being performed (e.g., opening URLs, interacting with elements).
   - After the test completes, the execution status will be shown in the **TestNG console** in your IDE or the CLI output.

6. **View Test Results**:
   - **TestNG Result**: Test results will be available in the console.
