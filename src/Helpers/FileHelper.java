package Helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by jacob on 15/05/2017.
 */
public class FileHelper {

    private static final String defaultConfigLocation = "configs/config.txt";

    private BufferedReader br;
    private Properties defaultProps = new Properties();
    private Properties currentProps;
    private ArrayList<Customer> Customers;

    public FileHelper(){
        loadDefaultProperties();

        if (!new File("configs").isDirectory()) {
            new File("configs").mkdir();
        }

        //Check for config file. Create new default if notExists.
        if (!new File(defaultConfigLocation).exists()){
            try (FileWriter fw = new FileWriter(new File(defaultConfigLocation))) {
                defaultProps.store(fw, "Default configuration file\nIf deleted, a new one will be generated at runtime");
                currentProps = new Properties(defaultProps);
            } catch (IOException e){
                e.printStackTrace(System.err);
                System.err.println("Unable to read or create config file. Did you run as administrator?");
                System.exit(-1);
            }
        }

        //Load properties from configs
         try (FileReader fr = new FileReader(new File(defaultConfigLocation))) {
            currentProps = new Properties();
            currentProps.load(fr);
         } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not find default config file. Should not have been able to see this message");
            System.exit(-1);
         }

    }

    public void loadFromFile(){
        loadUsersFromFile();
        loadInventoryFromFile();
        loadWorkersFromFile();
    }

    public void loadUsersFromFile(){
        File users = new File(currentProps.getProperty("DataBaseFolder") + "/" + currentProps.getProperty("UserProfiles"));
        if(users.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(users));
                Properties userProfiles = new Properties();
                userProfiles.load(br);
                for (Object user: userProfiles.keySet()){
                    String userID = (String) user;
                    String[] attributes = userProfiles.getProperty(userID).split(",");
                    Customers.add(new Customer(Integer.parseInt(userID), attributes[0], attributes[1], attributes[2]));
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Could not read " + currentProps.getProperty("UserProfiles"));
            }
        }
    }

    public void loadInventoryFromFile(){

    }

    public void loadWorkersFromFile(){

    }

    private void loadDefaultProperties(){
        defaultProps.setProperty("DataBaseFolder", "DB");
        defaultProps.setProperty("Inventory", "Inventory.txt");
        defaultProps.setProperty("Workers", "Workers.txt");
        defaultProps.setProperty("UserProfiles", "UserProfiles.txt");
        defaultProps.setProperty("Port", "4444");
    }

    /**
     * Used for testing purposes only
     */
    public void printProperties() throws IOException {
        currentProps.store(System.out, null);
    }

    public Properties getProps(){
        return currentProps;
    }

}
