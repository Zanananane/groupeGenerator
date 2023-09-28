import java.io.FileNotFoundException;
import java.io.IOException;


import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.*;

import java.io.FileWriter;


public class randomGroup {
    private PeopleList list;
    private HashMap<String, ArrayList<String>> arrayOfGroups;
    private int peopleNumber;
    private int numberOfGroups;
    private int groupNumber;
    private List<String> subListPeople;
    private HashMap<String, Integer> peoplesGroups;

    public randomGroup(String nomList, int peopleNumber, int numberOfGroups) throws FileNotFoundException, IOException, ParseException {
        this.list = new PeopleList(nomList);
        this.arrayOfGroups = new HashMap<String,ArrayList<String>>();
        this.peoplesGroups = new HashMap<String,Integer>();
        this.numberOfGroups = numberOfGroups;
        this.peopleNumber = peopleNumber;
        this.subListPeople = new ArrayList<String>(this.list.getnames()).subList(0,this.peopleNumber);
        this.groupNumber = 0 ;
        for(int i =0;i<this.peopleNumber; i++){
            this.peoplesGroups.put(this.subListPeople.get(i),-1);
        }
    }

    public void CreatesGroups(){
		/*
		 This method creates groups for the number of people defined by "peopleNumber"
		 the number of groups is defined by "groupNumber"
		 */

        Random random = new Random();

        int randomNumber;
        this.subListPeople = new ArrayList<String>(this.list.getnames()).subList(0,this.peopleNumber);

        ArrayList<String> group = new ArrayList<String>();

        int numberOfpeopleByGroups = this.peopleNumber/this.numberOfGroups;
        ArrayList<Integer> occurences = new ArrayList<Integer>();
        for (int i=0; i < this.numberOfGroups; i++) {          							// loop for the number of groups
            for(int j=0; j < numberOfpeopleByGroups; j++) {							// loop for the number of people in each group

                randomNumber = random.nextInt(subListPeople.size());            // gets a random number to choose someone
                String personsName = subListPeople.get(randomNumber);

                int iteratif =0;

                if ( this.groupNumber > this.numberOfGroups & j>1){
                    while (Collections.frequency( occurences, this.peoplesGroups.get(personsName))>2 & iteratif>500){
                        randomNumber = random.nextInt(subListPeople.size());
                        personsName = subListPeople.get(randomNumber);
                        iteratif ++;
                    }
                    group.add(subListPeople.get(randomNumber));							// get this person in the group
                    this.peoplesGroups.put(personsName,this.groupNumber);
                    occurences.add(this.peoplesGroups.get(personsName));
                    subListPeople.remove(randomNumber);									// remove the person from the sublist so they can't be chosen anymore
                }
                else {
                    group.add(subListPeople.get(randomNumber));                            // get this person in the group
                    this.peoplesGroups.put(personsName, this.groupNumber);
                    occurences.add(this.peoplesGroups.get(personsName));
                    subListPeople.remove(randomNumber);                                    // remove the person from the sublist so they can't be chosen anymore

                }
            }
            occurences.clear();
            this.arrayOfGroups.put("group"+this.groupNumber,new ArrayList<String>(group));			// puts the new group in a hash map with the other groups
            group.clear();
            this.groupNumber ++;
        }

    }
    /*
    public void GroupExists(){

    }
    */

    public void Display() {
        System.out.println(this.arrayOfGroups);
        System.out.println(this.peoplesGroups);
    }

    public void WriteGroups(String fileName){
        JSONObject jsonObj = new JSONObject(this.arrayOfGroups);
        try (FileWriter file = new FileWriter(fileName+".json",true)){
            file.write(jsonObj.toJSONString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
