# MetaCampus2

La parte di server utilizza un database MySQL.
Per la connessione:
- username: root
- password: root_metacampus2_2024
Nel nostro caso abbiamo utilizzato container di Docker per creare la connessione (docker run --name metacampus2-mysql -e MYSQL_ROOT_PASSWORD=root_metacampus2_2024 -p 3306:3306 -d mysql).

Il programma contiene una proprietà "loadMetaverse" che di default è una string vuota "". È possibile specificare il nome del metaverso da caricare quando viene avviata l'applicazione la prima volta. Per la nostra applicazione, è possibile caricare le risorse del metaverso "Campus Est SUPSI".
- Dal -jar bisogna eseguire il seguente comando: java -jar target/metacampus2-0.0.1-SNAPSHOT.jar --loadMetaverse=campus_est_supsi
- Alternativamente, con il run: mvn spring-boot:run "-Dspring-boot.run.arguments=--loadMetaverse=campus_est_supsi"