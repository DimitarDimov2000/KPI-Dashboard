# ---- Build stage ----
FROM eclipse-temurin:25-jdk-jammy AS build

WORKDIR /app

# copy everything into the image
COPY . .

# make sure the wrapper is executable
RUN chmod +x ./gradlew

# build the jar (skip tests for now; remove -x test once stable)
RUN ./gradlew bootJar -x test --no-daemon


# ---- Run stage ----
FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

# copy the fat jar from the build stage, name doesn’t matter because of the wildcard
COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
