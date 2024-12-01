# Usa a imagem base com OpenJDK 21
FROM eclipse-temurin:21-jdk-jammy

# Define o diretório de trabalho no container
WORKDIR /app

# Copia apenas os arquivos necessários para o Maven Wrapper e o build
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .

# Baixa as dependências do Maven sem fazer o build (para cache de dependências)
RUN ./mvnw dependency:go-offline

# Copia o restante do código-fonte da aplicação
COPY src ./src

# Compila e empacota o aplicativo
RUN ./mvnw clean package -DskipTests

# Copia o JAR gerado para o container
COPY target/ServidorServicosJa-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 para o serviço
EXPOSE 8080

# Define o comando de inicialização do container
CMD ["java", "-jar", "app.jar"]
