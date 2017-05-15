package Testing;

import Helpers.FileHelper;

import java.io.IOException;

/**
 * Created by jacob on 14/05/2017.
 */
public class test {

    public static void main(String[] args) throws IOException {
        FileHelper fh = new FileHelper();
        fh.printProperties();
    }

}
