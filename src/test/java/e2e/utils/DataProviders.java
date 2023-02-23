package e2e.utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public Iterator<Object[]> newContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Alex", "Dushuk", "This I"});
        list.add(new Object[]{"Max", "Spiegel", "This Max"});
        list.add(new Object[]{"Andrei", "Luft", "This Andrei"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newContactWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{split[0], split[1], split[2]});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
