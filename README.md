This is a Java Spring Boot Microservice project. It includes JUnit, integration tests, SonarQube integration, a Dockerfile, and Trivy integration.

Java Springboot Microservice and JUnit tests and Integration test has been created using ChatGPT.

Step By Step Process:-
1) First, check out the Java code and install Java with Maven cache. Use the following actions:
2) For Code Build, test, and artifact creation, use the following Maven command:
i)--batch-mode → runs Maven in non-interactive mode (best for CI pipelines)
ii)--update-snapshots → checks and updates snapshot dependencies from remote repositories
iii)verify → executes Maven lifecycle till verify phase

3) For Sonar Setup, You need to create an account in sonar with github and create new organisation, Click Analyse new project, select the repo and import the repo.
4)Add Coverage plugin in pom.xml and add properties in pom.xml. For this refer this(https://docs.sonarsource.com/sonarqube-cloud/analyzing-source-code/test-coverage/java-test-coverage) link
5)Now in github actions file add the CI script provided by SonarQube (https://docs.sonarsource.com/sonarqube-cloud/analyzing-source-code/ci-based-analysis/github-actions-for-sonarcloud). Also create PAT Token in SonarQube Cloud and create it as secret in github. To Create a Token in Sonar, Go to Sonar Cloud > click on profile > My Account > Security > Enter Token name and click generate
6)To create a secret in github, Go to Settings > Security & Quality > Select Secrets & Variables > Now select actions > create a secret with name as sonar_token(Caps)
7)Run the commands to do code coverage and you can see report in sonarQube cloud(https://docs.sonarsource.com/sonarqube-cloud/analyzing-source-code/scanners/sonarscanner-for-maven#code-coverage)
8) For the image creation, You need to write a Dockerfile and CI steps can be taken from Docker Hub (https://docs.docker.com/build/ci/github-actions/) or, using custom commands, you can generate the image and push it to the registry.
9) After image creation, Scan the image using trivy action setup which is provided by github. This will create a report with vulnerabilities found in image