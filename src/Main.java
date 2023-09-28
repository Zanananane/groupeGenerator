import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

        randomGroup test = new randomGroup("ListeDePrenoms.json", 40, 5);
        test.CreatesGroups();
        test.CreatesGroups();
        test.CreatesGroups();

        test.Display();
        test.WriteGroups("group");
    }
}