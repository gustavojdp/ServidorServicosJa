FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copia os arquivos necessários para o build
COPY ServidorServicosJa/mvnw ./
COPY ServidorServicosJa/.mvn/ .mvn/
COPY ServidorServicosJa/pom.xml ./
COPY ServidorServicosJa/src/ ./src/

# Faz o download de dependências e gera o JAR
RUN ./mvnw clean package -DskipTests

# Copia o JAR gerado para o container
COPY ServidorServicosJa/target/ServidorServicosJa-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta 8080 para o serviço
EXPOSE 8080

# Comando para rodar o JAR
CMD ["java", "-jar", "app.jar"]
