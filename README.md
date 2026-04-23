# Password Strength Checker API

A production-ready Spring Boot REST API that evaluates password strength.  
Ready to deploy on **Render** as a Web Service.

---

## Endpoints

| Method | Route | Description |
|--------|-------|-------------|
| GET | `/` | Health check / welcome message |
| GET | `/api/check?password=<pwd>` | Evaluate password strength |

### Example Response

```json
GET /api/check?password=Hello@123

{
  "score": 90,
  "strength": "Strong",
  "suggestions": ["Great password!"]
}
```

### Strength Thresholds

| Score | Strength |
|-------|----------|
| 0–40  | Weak     |
| 41–70 | Medium   |
| 71–100| Strong   |

---

## Run Locally

**Prerequisites:** Java 17+, Maven 3.6+

```bash
# 1. Clone the repo
git clone <your-repo-url>
cd PasswordStrengthChecker

# 2. Run in dev mode
mvn spring-boot:run

# 3. Test the API
curl "http://localhost:8080/api/check?password=Hello@123"
```

> **Note for Ubuntu users:** If you have multiple JDKs installed, make sure Maven
> uses the JDK (not JRE). Set `JAVA_HOME` if needed:
> ```bash
> export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
> mvn spring-boot:run
> ```

---

## Build & Deploy

```bash
# Build a runnable JAR
mvn clean install

# Start the JAR
java -jar target/password-strength-checker-1.0.0.jar
```

---

## Deploy on Render

1. Push code to GitHub.
2. Create a new **Web Service** on [render.com](https://render.com).
3. Set:
   - **Build Command:** `mvn clean install`
   - **Start Command:** `java -jar target/password-strength-checker-1.0.0.jar`
   - **Environment:** `Java`
4. Render automatically injects `PORT` — the app reads it via `${PORT:8080}`.

---

## Project Structure

```
PasswordStrengthChecker/
├── pom.xml
├── .gitignore
├── README.md
└── src/
    └── main/
        ├── java/com/example/passwordchecker/
        │   ├── PasswordStrengthCheckerApplication.java   ← entry point
        │   ├── controller/
        │   │   └── PasswordController.java               ← REST routes
        │   ├── service/
        │   │   └── PasswordStrengthService.java          ← business logic
        │   └── model/
        │       └── PasswordStrengthResponse.java         ← response POJO
        └── resources/
            └── application.properties                    ← server config
```
