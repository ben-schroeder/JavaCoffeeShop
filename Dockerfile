FROM maven:3-openjdk-11 as build
RUN mkdir /usr/src/project
COPY . /usr/src/project
WORKDIR /usr/src/project
RUN mvn package -DskipTests

FROM eclipse-temurin:11.0.18_10-jre-jammy
RUN mkdir /project
RUN groupadd -r someone && useradd -r -s /bin/false -g someone someone
COPY --from=build /usr/src/project/target/JavaCoffeeShop.jar /project/
RUN chown -R someone:someone /project
WORKDIR /project
USER someone
ENTRYPOINT java -jar JavaCoffeeShop.jar
