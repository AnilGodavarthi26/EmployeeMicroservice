# Java Spring Boot Microservice CI/CD Pipeline

This is a Java Spring Boot Microservice project with JUnit tests, integration tests, SonarQube integration, Docker containerization, and Trivy security scanning.

---

## Prerequisites

Before starting, complete these in order:

### 1. Create GitHub Account & Repository
- Go to [GitHub](https://github.com) and create account
- Create a new repository for your Java Spring Boot project
- Push your code to the repository

### 2. Create SonarQube Cloud Account
- Go to [SonarQube Cloud](https://sonarcloud.io/)
- Sign up using your GitHub account
- Create a new organization
- Import your GitHub repository into SonarQube
- **Note your Organization Key** - you'll need this later

### 3. Create Docker Hub Account
- Go to [Docker Hub](https://hub.docker.com)
- Create account with email, username, and password
- **Note your Docker Hub username** - you'll need this later

### 4. Create GitHub Personal Access Token
- Go to GitHub → Settings → Developer settings → Personal access tokens → Tokens (classic)
- Generate a new token with `repo` and `workflow` scopes
- Save this token securely

### 5. Create SonarQube Token
- Go to SonarQube Cloud → Profile → My Account → Security
- Generate a Global Analysis Token
- Save this token securely

### 6. Create Docker Hub Token
- Go to Docker Hub → Account Settings → Security
- Create access token with Read & Write permissions
- Save this token securely

### 7. Add Secrets to GitHub Repository
- Go to your GitHub repository → Settings → Secrets and variables → Actions
- Add these secrets:
  - `SONAR_TOKEN` (from Step 5)
  - `DOCKER_PAT` (from Step 6)
- Add these variables:
  - `DOCKER_USERNAME` (your Docker Hub username)
  - `SONAR_ORG_KEY` (your SonarQube organization key)

---

## Step-By-Step Process

### Step 1: Code Checkout & Java Setup with Maven Cache

You need to use GitHub Actions to:
- Checkout your code from the repository
- Install Java on the build machine
- Setup Maven with dependency caching

**What to do:**
- Use the `actions/checkout` action from GitHub
- Use the `actions/setup-java` action from GitHub
- Configure Java version (11, 17, or 21)
- Enable Maven caching for faster builds

**Why this matters:**
- Gets your latest code
- Installs Java compiler
- Caches Maven libraries so future builds run faster

**Reference Links:**
- [GitHub Actions Checkout](https://github.com/actions/checkout)
- [GitHub Actions Setup Java](https://github.com/actions/setup-java)

---

### Step 2: Build, Test, and Create Artifact

You need to use Maven to build your project with this lifecycle:

**What to do:**
- Run Maven in batch mode (non-interactive, best for CI)
- Update snapshot dependencies from repositories
- Execute the verify phase which includes: compile → test → package → verify

**What this accomplishes:**
- Compiles your Java code
- Runs all unit tests
- Runs all integration tests
- Creates a deployable artifact (.jar file)
- Verifies everything built successfully

**Why verify phase?**
- It's the final phase before deployment
- Ensures all tests pass
- Creates your application package
- Checks the build is complete and valid

**Reference Links:**
- [Apache Maven Build Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
- [Maven Verify Phase](https://maven.apache.org/ref/3.9.0/maven-core/lifecycles.html)

---

### Step 3: SonarQube Cloud Setup

You need to setup SonarQube for code quality analysis:

**What to do:**
- Create account on SonarQube Cloud (already done in prerequisites)
- Import your GitHub repository
- Create a new project in SonarQube
- Connect your GitHub repo to the project

**What this accomplishes:**
- Sets up a project dashboard for code analysis
- Prepares SonarQube to receive analysis reports
- Creates a link between your GitHub repo and SonarQube

**Reference Links:**
- [SonarQube Cloud Getting Started](https://sonarcloud.io/)
- [SonarQube GitHub Integration](https://docs.sonarsource.com/sonarqube-cloud/getting-started/github-integration/)

---

### Step 4: Add Code Coverage Plugin to Maven

You need to add a code coverage plugin to your Maven configuration:

**What to do:**
- Open your `pom.xml` file
- Find the `<build><plugins>` section
- Add JaCoCo code coverage plugin
- Configure it to run during test phase and generate reports

**Why you need this:**
- Measures how much of your code is covered by tests
- Generates coverage reports in a format SonarQube can read
- Shows which parts of code don't have tests
- Helps track coverage percentage over time

**What it does:**
- Collects coverage metrics during Maven test execution
- Creates coverage reports
- Provides data to SonarQube dashboard

**Reference Links:**
- [SonarQube Java Test Coverage Documentation](https://docs.sonarsource.com/sonarqube-cloud/analyzing-source-code/test-coverage/java-test-coverage)
- [JaCoCo Maven Plugin Documentation](https://www.jacoco.org/jacoco/trunk/doc/maven.html)

---

### Step 5: Add SonarQube Analysis to GitHub Actions

You need to configure GitHub Actions to run SonarQube analysis:

**What to do:**
- Create `.github/workflows/ci-cd-pipeline.yml` file in your repository
- Add the SonarQube action to your workflow
- Pass your SonarQube token as environment variable
- Configure it with your organization key and project key

**Why you need this:**
- Automatically analyzes code after each build
- Sends results to SonarQube Cloud
- Creates a quality report dashboard
- Fails the build if quality gates aren't met

**What it does:**
- Runs code analysis on compiled code and test coverage
- Checks for bugs, vulnerabilities, code smells
- Compares with previous analysis
- Publishes results to SonarQube dashboard

**How to get the configuration:**
- Go to your SonarQube Cloud project
- Look for "GitHub Actions" setup instructions
- Copy the configuration provided
- Paste into your workflow file

**Reference Links:**
- [SonarQube GitHub Actions Integration](https://docs.sonarsource.com/sonarqube-cloud/analyzing-source-code/ci-based-analysis/github-actions-for-sonarcloud)

---

### Step 6: Create Dockerfile

You need to create a Dockerfile to package your application:

**What to do:**
- Create a file named `Dockerfile` in your repository root
- Specify a Java base image (openjdk or similar)
- Copy your compiled .jar artifact into the image
- Define how to run your Spring Boot application
- Optionally expose the port your app runs on

**Why you need this:**
- Defines how to package your app into a container
- Ensures your app runs the same way everywhere
- Makes deployment consistent across environments
- Includes everything needed to run your app

**What it accomplishes:**
- Creates a Docker image (blueprint for container)
- Includes Java runtime
- Includes your application code
- Defines startup command

**Reference Links:**
- [Dockerfile Best Practices](https://docs.docker.com/develop/develop-images/dockerfile_best-practices/)
- [Docker Java Images Guide](https://hub.docker.com/_/openjdk)

---

### Step 7: Build and Push Docker Image to Docker Hub

You need to configure GitHub Actions to build and push your Docker image:

**What to do:**
- Add Docker build action to your workflow
- Configure it to build an image from your Dockerfile
- Login to Docker Hub using credentials
- Push the image to Docker Hub registry
- Tag the image with version/latest tag

**Why you need this:**
- Automates Docker image creation
- Stores your image in Docker Hub registry
- Makes image available for deployment
- Creates versioned releases of your application

**What it accomplishes:**
- Builds Docker image from your Dockerfile
- Authenticates with Docker Hub
- Uploads image to Docker Hub repository
- Makes it available for others to download and run

**Reference Links:**
- [Docker GitHub Actions Guide](https://docs.docker.com/build/ci/github-actions/)
- [Docker Build Push Action](https://github.com/docker/build-push-action)
- [Docker Hub Push Documentation](https://docs.docker.com/engine/reference/commandline/push/)

---

### Step 8: Create Dockerfile for Your Application

Same as Step 6 - ensure your Dockerfile is created with:
- Appropriate Java base image
- Copy your built artifact
- Correct startup command
- Proper port exposure for Spring Boot application

This Dockerfile will be used in Step 7 to build the Docker image.

---

### Step 9: Scan Docker Image with Trivy

You need to add security scanning to your workflow:

**What to do:**
- Add Trivy action to your GitHub workflow
- Configure it to scan the Docker image you just built
- Specify output format (SARIF for GitHub integration)
- Set severity levels (which vulnerabilities to report)

**Why you need this:**
- Checks Docker image for security vulnerabilities
- Finds OS-level security issues
- Identifies vulnerable dependencies in your application
- Prevents deploying insecure images

**What it accomplishes:**
- Scans the Docker image for known vulnerabilities
- Checks OS packages, system libraries, application dependencies
- Creates a vulnerability report
- Lists severity levels (CRITICAL, HIGH, MEDIUM, LOW)

**What vulnerabilities it finds:**
- OS package vulnerabilities
- Dependency/library vulnerabilities
- Configuration issues
- Secrets accidentally included in image

**Reference Links:**
- [Trivy GitHub Action](https://github.com/aquasecurity/trivy-action)
- [Trivy Documentation](https://aquasecurity.github.io/trivy/)
- [Container Security Best Practices](https://aquasecurity.github.io/trivy/latest/docs/)

---

## Expected Results After Setup

After you push code to GitHub, you should see:

1. **GitHub Actions Workflow Execution**
   - Runs automatically on code push
   - Shows progress of each step
   - Reports success or failure

2. **Code Quality Report in SonarQube**
   - Code metrics dashboard
   - Bug and vulnerability count
   - Test coverage percentage
   - Code smell violations
   - Duplication percentage

3. **Docker Image in Docker Hub**
   - Image uploaded to Docker Hub registry
   - Tagged with version or "latest"
   - Available for download and deployment

4. **Security Scan Report from Trivy**
   - Vulnerability report generated
   - Severity levels assigned
   - Specific CVEs identified
   - Affected packages listed

---

## Complete Pipeline Flow

The entire process works like this:

```
1. You push code to GitHub
   ↓
2. GitHub Actions workflow triggers automatically
   ↓
3. Checkout code from repository
   ↓
4. Install Java and setup Maven
   ↓
5. Build application with Maven (compile + test)
   ↓
6. Run code through SonarQube analyzer
   ↓
7. Build Docker image from Dockerfile
   ↓
8. Push Docker image to Docker Hub
   ↓
9. Scan Docker image with Trivy
   ↓
10. Generate reports in all three platforms
    (GitHub Actions, SonarQube Cloud, Docker Hub)
```

---

## Glossary of Terms

| Term | Meaning |
|------|---------|
| **Maven** | Build automation tool for Java projects |
| **pom.xml** | Maven configuration file that defines dependencies and build settings |
| **Artifact** | Output of build process, usually a .jar file containing your application |
| **GitHub Actions** | Automation platform that runs workflows triggered by GitHub events |
| **Workflow** | A file defining automated tasks to run in response to events |
| **SonarQube** | Code quality and security analysis platform |
| **Code Coverage** | Percentage of code covered by automated tests |
| **Docker** | Containerization platform for packaging applications |
| **Dockerfile** | File containing instructions to build a Docker image |
| **Docker Hub** | Registry (repository) for storing and sharing Docker images |
| **Docker Image** | Blueprint/template for creating Docker containers |
| **Container** | Running instance of a Docker image |
| **Trivy** | Security vulnerability scanner for containers and dependencies |
| **CVE** | Known security vulnerability identifier |
| **Token (PAT)** | Authentication credential used instead of passwords |
| **Secret** | Encrypted sensitive information stored securely in GitHub |
| **Variable** | Non-sensitive information stored in GitHub |

---

## Important Reference Links

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [SonarQube Cloud](https://sonarcloud.io/)
- [Docker Hub](https://hub.docker.com)
- [Apache Maven](https://maven.apache.org/)
- [Spring Boot Official Documentation](https://spring.io/projects/spring-boot)
- [Docker Official Documentation](https://docs.docker.com/)
- [Trivy by Aqua Security](https://github.com/aquasecurity/trivy)

---

## How to Get Actual Code

For actual code/configuration, refer to:

1. **GitHub Actions Workflow:**
   - SonarQube Cloud project → Look for GitHub Actions instructions
   - Docker action documentation at docker/build-push-action GitHub repository
   - Trivy action documentation at aquasecurity/trivy-action GitHub repository

2. **pom.xml Plugin Configuration:**
   - JaCoCo plugin → Official JaCoCo documentation
   - SonarQube Java Coverage → SonarQube documentation links

3. **Dockerfile:**
   - Docker official documentation
   - Official openjdk Docker image documentation
   - Spring Boot Docker guide

4. **GitHub Secrets Configuration:**
   - GitHub official documentation for adding secrets
   - Each tool's documentation on required environment variables

---

## Summary

This CI/CD pipeline automatically:
- ✅ Builds and tests your code
- ✅ Analyzes code quality
- ✅ Measures test coverage
- ✅ Packages application as Docker image
- ✅ Scans for security vulnerabilities
- ✅ Generates reports for monitoring

All steps run automatically when you push code to GitHub.

For exact code and configuration, always refer to the official documentation of each tool - they get updated regularly with the latest best practices.
