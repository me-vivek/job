To connect Java with HTML and build a dynamic web application, you typically use a **Java-based web framework** like **Servlets**, **JSP (JavaServer Pages)**, or **Spring Boot**. Here's a step-by-step guide for a basic setup:

---

### **1. Install Required Tools**
- **JDK**: Install the Java Development Kit.
- **Web Server**: Use a server like Apache Tomcat (if not using a framework like Spring Boot, which has an embedded server).
- **IDE**: Use an IDE like IntelliJ IDEA, Eclipse, or VS Code for development.

---

### **2. Set Up a Web Project**
You can structure your project as a **Dynamic Web Project** or a **Maven Project**.

---

#### **Option 1: Using Servlets and HTML**
This approach involves creating an HTML frontend and handling requests with Java Servlets.

---

#### **Step-by-Step Example: Java Servlets + HTML**

##### **1. Project Setup**
- Create a new Dynamic Web Project or Maven Project.
- Add the **Servlet API dependency** (if using Maven):
  ```xml
  <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
  </dependency>
  ```

##### **2. Directory Structure**
```
src/main/
│
├── java/                     // Java backend code
│   └── com.example.MyServlet.java
│
├── webapp/                   // HTML and other frontend assets
│   ├── index.html
│   └── WEB-INF/
│       └── web.xml           // Deployment descriptor
```

---

##### **3. Write HTML Frontend**
Create a file `index.html` under `webapp`:
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Java and HTML Integration</title>
</head>
<body>
    <h1>Connect Java with HTML</h1>
    <form action="helloServlet" method="GET">
        <label for="name">Enter Your Name:</label>
        <input type="text" id="name" name="name" required>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
```

---

##### **4. Create a Java Servlet**
Create a file `MyServlet.java` under `src/main/java/com/example`:
```java
package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/helloServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the name from the HTML form
        String name = request.getParameter("name");

        // Respond with a message
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>Hello, " + name + "!</h1>");
        response.getWriter().println("</body></html>");
    }
}
```

---

##### **5. Configure web.xml (Optional for Servlet 3.0+)**
Add a `web.xml` file under `WEB-INF` if not using annotations:
```xml
<web-app xmlns="http://java.sun.com/xml/ns/javaee" version="3.1">
    <servlet>
        <servlet-name>MyServlet</servlet-name>
        <servlet-class>com.example.MyServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyServlet</servlet-name>
        <url-pattern>/helloServlet</url-pattern>
    </servlet-mapping>
</web-app>
```

---

##### **6. Deploy and Run**
- Deploy the project on **Apache Tomcat** or any servlet container.
- Access the application in the browser at `http://localhost:8080/your_project_name/index.html`.

---

### **3. Advanced Approach: Spring Boot + Thymeleaf**
Spring Boot provides a more modern and streamlined way to integrate Java with HTML using templates like **Thymeleaf**. Here's an overview:

#### **Steps:**
1. Create a Spring Boot project with the **Thymeleaf** dependency:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-thymeleaf</artifactId>
   </dependency>
   ```

2. Create an HTML template (`src/main/resources/templates/index.html`):
   ```html
   <!DOCTYPE html>
   <html>
   <head>
       <title>Spring Boot</title>
   </head>
   <body>
       <h1>Welcome, <span th:text="${name}">User</span>!</h1>
       <form action="/submit" method="get">
           <input type="text" name="name" placeholder="Enter your name">
           <button type="submit">Submit</button>
       </form>
   </body>
   </html>
   ```

3. Add a Spring Boot Controller:
   ```java
   @Controller
   public class MyController {
       @GetMapping("/")
       public String index(Model model) {
           model.addAttribute("name", "Guest");
           return "index";
       }

       @GetMapping("/submit")
       public String submit(@RequestParam("name") String name, Model model) {
           model.addAttribute("name", name);
           return "index";
       }
   }
   ```

4. Run the application and access it via `http://localhost:8080`.

---

### **4. Which Option to Choose?**
- **Small-scale projects**: Servlets/JSP.
- **Modern web applications**: Spring Boot with Thymeleaf or REST APIs combined with frontend frameworks like React or Angular.

Let me know which approach you’d like to explore further!