import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PeopleList {
    private ArrayList<String> names;


    public PeopleList(String fileName) throws FileNotFoundException, IOException, ParseException {
        this.names = new ArrayList<String>();
        imPort(fileName);
    }

    private void imPort(String fileName) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fileName));  			// puts the in an object

        JSONObject jsonObject = (JSONObject) obj;							// creates a JSON object to handle the file
        JSONArray namesArray = (JSONArray) jsonObject.get("prenoms");		// gets the field that we are interested in

        if (namesArray != null) {
	    	/*
	    	 Puts the JSON array in an ArrayList for it to be easier to handle
	    	 */
            Iterator<String> iterator = namesArray.iterator();
            while (iterator.hasNext()) {
                String name = iterator.next();
                this.names.add(name);
            }
        }

    }

    public ArrayList<String> getnames() {
        return names;
    }

}
