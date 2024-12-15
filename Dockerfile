FROM openjdk:21
COPY target/primeStoreService-0.0.1-SNAPSHOT.jar /app_prime_store.jar
CMD ["java", "-jar", "app_prime_store.jar"]
