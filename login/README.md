## Login

### Running the Application

`mvn spring-boot:run`

The application launches with two sample user accounts defined in the java beckend under `DataLoader.java`:

**User One**

- Username: user1

- Password: password1

**Manager**

- Username: manager

- Password: abcdefg

**林嘉颖**

- Username: 林

- Password: jiayinglim

### Disabled CSRF

- The CSRF protection is currently disabled from `config/SecurityConfig.java` to ensure the logout function works for demonstration purposes.
- I suspect an issue with either the logout form in the home page template `resources/templates/welcome.html` or the java backend authentication setup under `config/SecurityConfig.java`, which results in a 403 status error and no logging out occurring after the logout button is clicked.
