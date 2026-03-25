FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app

COPY gradlew .
COPY gradle/ gradle/
RUN chmod +x gradlew
COPY build.gradle settings.gradle ./

# Dependency Pull Step - Separate layer for caching of dependencies
RUN ./gradlew dependencies --no-daemon

# Copy source
COPY src/ src/

# App build with test
RUN ./gradlew build -x test

# Smaller image for actual run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]