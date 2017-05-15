package Helpers;

import java.io.*;
import java.util.Properties;

/**
 * Created by jacob on 15/05/2017.
 */
public class FileHelper {

    private static final String defaultConfigLocation = "configs/config.txt";

    private BufferedReader br;
    private static Properties defaultProps = new Properties();
    private Properties currentProps;

    public FileHelper(){
        loadDefaultProperties();

        if (!new File("configs").isDirectory()) {
            new File("configs").mkdir();
        }

        if (!new File(defaultConfigLocation).exists()){
            try (FileWriter fw = new FileWriter(new File(defaultConfigLocation))) {
                defaultProps.store(fw, "Default configuration file\nIf deleted, a new one will be generated at runtime");
                currentProps = new Properties(defaultProps);
            } catch (IOException e){
                e.printStackTrace(System.err);
                System.err.println("Unable to read or create config file. Did you run as administrator?");
                System.exit(-1);
            }
        } else {
            try (FileReader fr = new FileReader(new File(defaultConfigLocation))) {
                currentProps = new Properties();
                currentProps.load(fr);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Could not find default config file. Should not have been able to see this message");
                System.exit(-1);
            }
        }


    }

    private void loadDefaultProperties(){
        defaultProps.setProperty("DataBaseFolder", "DB");
        defaultProps.setProperty("Inventory", "Inventory.txt");
        defaultProps.setProperty("Workers", "Workers.txt");
    }

    /**
     * Used for testing purposes only
     */
    public void printProperties() throws IOException {
        currentProps.store(System.out, null);
    }

}
