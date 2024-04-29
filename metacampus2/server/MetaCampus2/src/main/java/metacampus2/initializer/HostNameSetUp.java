package metacampus2.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;

@Component
@Profile("!test")
public class HostNameSetUp implements CommandLineRunner, Ordered {
    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    public static final String FILE_PATH = ".." + SEPARATOR + ".." + SEPARATOR + "unity" + SEPARATOR + "MetaCampus2"
            + SEPARATOR + "Assets" + SEPARATOR + "Resources" + SEPARATOR + "host_address.txt";
    @Override
    public void run(String... args) {
        createHostAddressFile();

        writeHostAddressToFile();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private void createHostAddressFile() {
        File hostAddressFile = new File(FILE_PATH);

        if(!hostAddressFile.exists()) {
            try {
                if(hostAddressFile.createNewFile()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(hostAddressFile));
                    writer.write("host-address=localhost\n");
                    writer.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void writeHostAddressToFile() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            String hostAddress = address.getHostAddress();

            File hostAddressFile = new File(FILE_PATH);

            if(hostAddressFile.isFile()) {
                BufferedReader reader = new BufferedReader(new FileReader(hostAddressFile));

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = reader.readLine()) != null) {
                    if(line.startsWith("host-address=")) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(hostAddressFile));
                        sb.append("host-address=").append(hostAddress).append("\n");
                        writer.write(sb.toString());
                        writer.close();
                    }
                }
                reader.close();
            }

        } catch (UnknownHostException ex) {
            System.out.println("Could not find IP address for this host");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
