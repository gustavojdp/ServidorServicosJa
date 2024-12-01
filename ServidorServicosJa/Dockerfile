# Usa uma imagem base com o OpenJDK 21
FROM eclipse-temurin:21-jdk-jammy

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o arquivo JAR gerado para o container
COPY target/ServidorServicosJa-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta 8080 (ou outra que sua aplicação use)
EXPOSE 8080

# Comando para executar o JAR
CMD ["java", "-jar", "app.jar"]