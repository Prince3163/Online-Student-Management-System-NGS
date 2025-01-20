# Student Management System

A simple web application to manage student information using Java, JSP, and Servlets.

![image](https://github.com/user-attachments/assets/6e2b1827-267f-4b0f-9e78-2456138abf0a)


## Features

- User login
- Add new students
- Update student information
- Delete student records
- View student dashboard
- User logout

## Technologies Used

- Java
- JSP
- Servlets
- PostgreSQL
- Maven

## File Descriptions

- `login.jsp`: Login form with error messages display.
- `LoginServlet.java`: Handles login and session management.
- `add.jsp`: Form to add new students.
- `AddServlet.java`: Processes new student data.
- `update.jsp`: Form to update student information.
- `UpdateServlet.java`: Handles student data updates.
- `Dashboard.java`: Displays student records in tabular form.
- `deleteServlet.java`: Deletes student records.
- `LogoutServlet.java`: Invalidates session.
- `style.css`: Common styles for all forms in application.
- `web.xml`: Servlet and database configuration, including context parameters and startup file.
- `pom.xml`: Maven project configuration.

## Flow

1. **Login**:
   - User accesses `login.jsp` and submits credentials.
   - `LoginServlet` validates credentials and starts a session.
   - On success, user is redirected to `DashboardServlet`.

2. **Dashboard**:
   - `Dashboard` displays all student details in a table.
   - User can choose to add, update, or delete student records.

3. **Add Student**:
   - User accesses `add.jsp` to fill in student details.
   - `AddServlet` processes the form, checks for existing users, and inserts new student data into the database.
   - On success, user is redirected to the dashboard.

4. **Update Student**:
   - User selects a student to update from the dashboard.
   - `UpdateServlet`'s `doGet` method fetches student details and pre-fills `update.jsp`.
   - User modifies the details and submits the form.
   - `UpdateServlet`'s `doPost` method updates the database with the new data.
   - On success, user is redirected to the dashboard.

5. **Delete Student**:
   - User selects a student to delete from the dashboard.
   - `deleteServlet` removes the student record from the database.
   - On success, user is redirected to the dashboard.

6. **Logout**:
   - User clicks the logout button.
   - `LogoutServlet` invalidates the session and redirects the user to `login.jsp`.

## Setup

1. **Clone the repository**:
    ```sh
    git clone https://github.com/Prince3163/Online-Student-Management-System-NGS.git
    cd Online-Student-Management-System-NGS
    ```

2. **Configure the database**:
    - Create a PostgreSQL database.
    - Update the database connection parameters in the `web.xml` file, like url, username and password.

3. **Build the project**:
    ```sh
    mvn clean install
    ```

4. **Deploy to Tomcat**:
    - Copy the WAR file from the `target` directory to the Tomcat `webapps` directory.

5. **Start Tomcat**:
    - Access the app at `http://localhost:8080/OnlineStudentManagementSystem`.
