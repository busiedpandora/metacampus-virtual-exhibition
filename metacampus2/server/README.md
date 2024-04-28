## MetaCampus2

# Connessione al database
La parte di server utilizza un database MySQL.
Per la connessione:
- username: root
- password: root_metacampus2_2024
Nel nostro caso abbiamo utilizzato cun ontainer di Docker per creare la connessione (docker run --name metacampus2-mysql -e MYSQL_ROOT_PASSWORD=root_metacampus2_2024 -p 3306:3306 -d mysql).

# Esecuzione del server
- Per runnare l'applicazione eseguire: mvn spring-boot:run
- Per avviare il .jar eseguire: java -jar target/metacampus2-0.0.1-SNAPSHOT.jar
Il programma contiene una proprietà "loadMetaverse" che di default è una string vuota "". È possibile specificare il nome del metaverso da caricare quando viene avviata l'applicazione la PRIMA volta. Per la nostra applicazione, è possibile caricare le risorse del metaverso "Campus Est SUPSI".
- Dal -jar bisogna eseguire il seguente comando: java -jar target/metacampus2-0.0.1-SNAPSHOT.jar --loadMetaverse=campus_est_supsi
- Alternativamente, con il run: mvn spring-boot:run "-Dspring-boot.run.arguments=--loadMetaverse=campus_est_supsi"

# Utenti Admin
L'applicazione presenta di default due utenti admin:
- Utente 1: username: simon, password: metacampus_admin1_2024
- Utente 2: username: daniel, password: metacampus_admin2_2024
Quando vengono creati altri utenti attraverso la registrazione, il ruolo a loro assegnato è Visitor.