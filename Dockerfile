#Imagen modelo
FROM eclipse-temurin:17.0.1_12-jdk

#Información del puerto a usar en docker (SOLO INFORMATIVO)
EXPOSE 8080

#Definir directorio raiz del contenedor
WORKDIR /root

#Copiar y pegar archivos dentro del contenedor
COPY ./pom.xml /root
#Compiamos el maven wrapper(mvn embedido que tiene spring)
COPY ./.mvn /root/.mvn
#Copiamos también el ejecutable del mvn wrapper
COPY ./mvnw /root

#Descargar dependencias, se le dice al mvn portable que descargue las dependencias sin construir el proyecto
RUN ./mvnw dependency:go-offline

#Copiar codigo fuente dentro del contenedor
COPY ./src /root/src

#Construir la aplicacion
#Se uso la opcion "-DskipTests" para evitar error de buildeao mvn clean install al ejecutar test
#Para producción debe de realizarse siempre con test
RUN ./mvnw clean install -DskipTests

#Levantar nuestra aplicación cuando el contenedor inicie
ENTRYPOINT ["java","-jar","/root/target/spring-docker-swagger-0.0.1-SNAPSHOT.jar"]

#Para la ejecución del dockerfile y crear la iamgen se usa docker build -t "nombre_img" ruta_de_dockerfile(puede ser '.')
