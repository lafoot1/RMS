call mvn clean install -f rms-api/pom.xml 
mvn clean install spring-boot:run -f rms-core/pom.xml -DskipTests -Dspring.profiles.active=local