FROM openjdk:8
ADD target/TMDB.jar TMDB.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "TMDB.jar"]