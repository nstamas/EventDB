///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Programming Assignment 1
// Files:            Event.java, EventDB.java, EventDBMain.java
// Semester:         CS367 Spring 2013
//
// Author:           Nicholas Stamas, nstamas@wisc.edu
// CS Login:         stamas
// Lecturer's Name:  Jim Skrentny
// Lab Section:      -
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Calvin Hareng, hareng@wisc.edu
// CS Login:         hareng
// Lecturer's Name:  Jim Skrentny
// Lab Section:      -
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////


import java.io.*;
import java.util.*;
import java.lang.IllegalArgumentException;

/** EventDBMain
 * This class accepts an input file from the user as an argument.  This class
 * reads that input file and adds events and athletes from that list accordingly.
 * The class then enters a loop where it prompts the user to access or change
 * data that is already stored.
 *
 * 
 * @author Calvin Hareng and Nick Stamas
 */

public class EventDBMain{     /* implements iterable??*/
    public static void main(String[] args) throws FileNotFoundException{
    
    	//List of the events 
		//that are stored in EventDB will be placed here
    	ArrayList<Event> events = new ArrayList<Event>();  
    	
    	EventDB eventDB = new EventDB();  //Creates a new Event Database
    	
    	String inFile = args[0]; //takes in the text file
    	Scanner in = new Scanner(new File(inFile));
    	
    	//checks that the input file has another line of commands
    	while(in.hasNextLine()){ 
    		String lineInput = in.nextLine();
    		
    		//Separates the inputFile into athletes and events
    		String[] tokens = lineInput.split("[,]+"); 
    		
    		for(int d = 1; d < tokens.length; d++){
    			eventDB.addEvent(tokens[d]);  // Adds each event
    		}
    		for(int e = 1; e < tokens.length; e++){
    			
    			// Adds the athletes to their events
    			eventDB.addAthlete(tokens[0], tokens[e]); 
    		}
    	}
    	

        Scanner stdin = new Scanner(System.in);  // for reading console input
        boolean done = false;
        while (!done) {
        	// Creates an iterator for the list of events
        	Iterator<Event> events1 = eventDB.iterator(); 
        	events.clear();
        	// Iterates through eventDB and creates accessible events arrayList
        	while(events1.hasNext()){ 
        		events.add(events1.next());
        	}
            System.out.print("Enter option ( cdprswx ): ");
            String input = stdin.nextLine();

            // only do something if the user enters at least one character
            if (input.length() > 0) {
                char choice = input.charAt(0);  // strip off option character
                String remainder = "";  // used to hold the remainder of input
                if (input.length() > 1) { // if there is an argument
                    // trim off any leading or trailing spaces
                    remainder = input.substring(1).trim(); 

	                switch (choice) { // the commands that have arguments
	
	                case 'c':
	                    // Clears selected event
	                	if(eventDB.removeEvent(remainder)){
	                		System.out.println("event removed");
	                	}else {System.out.println("event not found");}
	                    break;
	
	                case 'p':
	                    // Prints the event for specific athlete
	                	if (!eventDB.containsAthlete(remainder)){
	                		System.out.println("athlete not found");
	                	}else {for(int i = 0; i < 
	                	eventDB.getEvents(remainder).size(); i++){
	                			if(i == 0){
	                				System.out.print(
	                						eventDB.getEvents(remainder).get(i));
	                			}else {System.out.print(", " + 
	                					eventDB.getEvents(remainder).get(i));}
	                		}
	                		System.out.println("");
	                	}
	                    break;
	
	                case 'r':
	                    // Prints roster for specific event
	                	if (eventDB.containsEvent(remainder)){
	                		if(!eventDB.getRoster(remainder).isEmpty()){
	                			for(int i = 0; i < 
	                			eventDB.getRoster(remainder).size(); i++){
		                			if(i == 0){
		                				System.out.print(
		                						eventDB.getRoster(remainder).get(i));
		                			}else {System.out.print(", " + 
		                					eventDB.getRoster(remainder).get(i));}
		                		}
	                			System.out.println("");
	                		}else{System.out.println("event not found");}
	                	}else{System.out.println("event not found");}
	                    break;
	
	                case 's':
	                    // The following code reads in a comma-separated sequence 
	                    // of strings.  If there are exactly two strings in the 
	                    // sequence, the strings are assigned to name1 and name2.
	                    // Otherwise, an error message is printed.
	                    String[] tokens = remainder.split("[,]+");
	                    if (tokens.length != 2) {
	                        System.out.println("need to provide exactly two names");
	                    }
	                    else {
	                        String name1 = tokens[0].trim();
	                        String name2 = tokens[1].trim();
	                        
	                        if(eventDB.containsAthlete(name1) && 
	                        		eventDB.containsAthlete(name2)){
	                        	// Finds all the events that 2 athletes are in, 
	                        	//stores them in an arrayList, compares them, 
	                        	// and then puts shared events into a new arrayList.  
	                        	//Finally, it prints out the shared events.
	                        	List<String> shareEvent = new ArrayList<String>();
	                        	List<String> name1List = eventDB.getEvents(name1);
	                        	List<String> name2List = eventDB.getEvents(name2);
	                        	Iterator<String> itr1 = name1List.iterator();
	                        	String currentEvent1;
	                        	String currentEvent2;
	                        	while(itr1.hasNext()){
	                        		currentEvent1 = itr1.next();
	                        		Iterator<String> itr2 = name2List.iterator();
	                        		while(itr2.hasNext()){
	                        			currentEvent2 = itr2.next();
	                        			if(currentEvent1.equalsIgnoreCase(
	                        					currentEvent2)){
	                        				shareEvent.add(currentEvent1);
								break;
	                        			}
	                        		}
	                        	}
	                        	if(!shareEvent.isEmpty()){
	                        		for(int i = 0; i < shareEvent.size(); i++){
			                			if(i == 0){
			                				System.out.print(shareEvent.get(i));
			                			}else {System.out.print(", " + 
			                					shareEvent.get(i));}
			                		}
		                			System.out.println("");
	                        	}else{System.out.println("none");}
	                        	
	                        }else{System.out.println("none");}
	                        
	                    }
	                    break;
	
	                case 'w':
	                    // Withdraws an athlete from all of the events that they 
	                	//are in
	                	if(eventDB.containsAthlete(remainder)){
	                		if(eventDB.removeAthlete(remainder)){
	                			System.out.println(remainder + " withdrawn " +
	                					"from all events");
	                		}else{System.out.println("athlete not found");}
	                	}else{System.out.println("athlete not found");}
	                    break;
	
	                default: // ignore any unknown commands
                    	System.out.println("Incorrect command.");
	                	break;
	                
	                } // end switch
                } // end if
                else { //if there is no argument
                	switch (choice) { // the commands without arguments
                	
                	case 'd': 
	                    // Prints out the entire Display command
                		
                		//(1) NUMBER OF EVENTS AND ATHLETES
                		
                		//official list of "unique" athletes
                		List<String> athleteList = new ArrayList<String>(); 					 								
                		List<String> testList = new ArrayList<String>();  
                		// This list stores an events roster.  It compares this
                	    // list against athleteList to make sure an athlete
                		// has not already been added to the list.
                		
                		//total number of events
                		double eventNum = events.size();  
                		double athleteNum;  // total number of unique athletes

                		// goes through each event
                		for(int i = 0; i < eventNum; i++){ 
                			if(events.get(i) != null){
                				testList = eventDB.getRoster(
                						events.get(i).getType());
                				
                				//goes through each ath.
                				for(int j = 0; j < testList.size(); j++){ 
                					if (athleteList.size() == 0){
                						for(int w = 0; w < testList.size(); w++){
                							athleteList.add(testList.get(w));
                						}
                					}
                					for(int k = 0; k < athleteList.size(); k++){ 
                						//prev. stored ath.
                						if(testList.get(j) == athleteList.get(k)){ 
                							
                							// if ath.int 2 events
                							break;
                						}
                						if(k == (athleteList.size() - 1)){ 
                							//last tier. adds if !=
                							athleteList.add(testList.get(j));
                						}
                					}
                				}
                			}
                			else{
                				throw new java.lang.IllegalArgumentException();
                			}
                		}
                		
                		athleteNum = athleteList.size(); //initializes athleteNum
                		System.out.println("Events: " + Math.round(eventNum) + 
                				", Athletes: " + Math.round(athleteNum));  
                		//prints total athletes and events
                		
                		//(2) # OF ATHLETES/EVENT
                		
                		// highest number of athletes in one event
                		double highestPerEvent = 0;  
                		
                		// least number of athletes in one event
                		double leastPerEvent = 0;    
                		
                		// total number of athletes, counting repeats of 
                		//athletes in separate events
                		double athleteTotal = 0;     
                		
                		// if more than one popular event, this list will
                		// store several popular events
                		ArrayList<String> popularEvents = new ArrayList<String>(); 
                		
                		// if more than one least popular event, this list will
                		// store several least popular events
                		ArrayList<String> leastPopEvents = new ArrayList<String>(); 
                											
                		// stores the average amount of athletes per event
                		double averagePerEvent = 0;  
				
                		for(int l = 0; l < eventNum; l++){
                			//total # of athletes, not counting unique #
                			testList = eventDB.getRoster(events.get(l).getType());
                			athleteTotal += testList.size(); 
                											 // of athletes
                			if(l == 0){
                				//initializes leastPerEvent
                				// to the first event roster size
                				leastPerEvent = events.get(l).getRoster().size(); 
																		  		  
                			}
                			if(testList.size() > highestPerEvent){
                				popularEvents.clear();  //clears array if new, 
                									   //more
													   //popular event.
                				
                				popularEvents.add(events.get(l).getType());  
                			
                				//sets the largest roster size to integer value
                				highestPerEvent = testList.size();  
                			}
                			//it is the same as a previous event
                			else if(testList.size() == highestPerEvent){    
                				popularEvents.add(events.get(l).getType()); 
                			}
                			
                			//if and not "else if" b/c could be least
                			// and most pop. event if only 1 event
                			if(testList.size() < leastPerEvent){ 
													     		 
                				leastPopEvents.clear();           
                				//clears arrayList if new, more
							   // popular event
						
                				leastPopEvents.add(events.get(l).getType());
                				leastPerEvent = testList.size();
                			}
                			//adds to arrayList if same roster
                			// size as another event
                			else if(testList.size() == leastPerEvent){ 
															   		   
                				leastPopEvents.add(events.get(l).getType());
                			}
                		}
                	     
                		 // converts averagePerEvent to integer
                		(averagePerEvent) = Math.round(athleteTotal / eventNum);
                		System.out.println("# of athletes/event: most " + 
                				//prints and converts #'s to integers
                				Math.round(highestPerEvent) +  
                				", least " + Math.round(leastPerEvent) + 
                				", average " + Math.round(averagePerEvent));
				
					//(3) ATHLETE WITH MOST OR LEAST EVENTS
                		// highest number events that a single athlete is in
                		double mostEvents = 0;  
                		
                		// least number of events that a single athlete is in
                		double leastEvents = 0; 
                		
                		// average number of events that an athlete is in
                		double averageEvents = 0; 
                		
                		// counter that keeps track of an individual athlete's 
                		//# of events
                		double eventsCurrAthlete = 0; 
                		
                		// keeps track of an individual athlete's name
                		String currAthlete;  
                		
                		//counts the total number of events, even ones that are 
                		//repeated. This is used in calculating the average # of
                		//events than an athlete is in
                		double eventCount = 0;  
                							    
                		
                		for(int m = 0; m < athleteNum; m++){ 
                			// Goes through each athlete
                			currAthlete = athleteList.get(m);
                			eventsCurrAthlete = 0; 
              
                			for(int n = 0; n < eventNum; n++){ 
                				// Goes through each event
                				
                				if(eventDB.isRegistered(currAthlete, 
                						events.get(n).getType())){ 
                					//if athlete is in this event
                					
                					//increments # of events the athlete is in
                					eventsCurrAthlete++; 	
                					eventCount++; 			// incremeter
                				}
                			}
                			if(eventsCurrAthlete > mostEvents){
                				//The highest # of events is most recent athlete
                    			mostEvents = eventsCurrAthlete; 
                    		}
                    		if(eventsCurrAthlete < leastEvents || m == 0){
                    			//The lowest # of events is most recent athlete
                    			leastEvents = eventsCurrAthlete; 
                    		}
                		}
                		
                		// converts average events to an integer
                		averageEvents = Math.round(eventCount / athleteList.size()); 
                		// converts values to integers and prints
                		System.out.println("# of events/athlete: most " + 
                				Math.round(mostEvents) +  
                				", least " + Math.round(leastEvents) + 
                				", average " + Math.round(averageEvents));
                		
                		//(4) 
                		System.out.print("Most Popular: ");
                		for(int b = 0; b < popularEvents.size(); b++){
                			if(b == 0){ // Used for printing purposes
                				System.out.print(initialCap(popularEvents.get(b)));
                			}else {System.out.print(", " + 
                					initialCap(popularEvents.get(b)));}
                		}
                		System.out.print(" [" + Math.round(highestPerEvent) + 
                				"] \n");
                		
                		// (5)
                		System.out.print("Least Popular: ");
                		for(int c = 0; c < leastPopEvents.size(); c++){
                			if(c == 0){
                				System.out.print(initialCap(
                						leastPopEvents.get(c)));
                			}else {System.out.print(", " + 
                					initialCap(leastPopEvents.get(c)));}
                		}
                		System.out.print(" [" + Math.round(leastPerEvent) + 
                				"] \n");
                			
	                    break;
 	                    
                	case 'x':
	                    done = true;
	                    System.out.println("exit");
	                    break;
	                    
                	default:  // a command with no argument
                		System.out.println("Incorrect command.");
	                    break;
                	} // end switch
                } // end else  
           } // end if
        } // end while
    } // end main
    
    /**
	 * initialCap(String str)
	 * This method takes in a String, str, and capitalizes the first character 
	 * of that string.If the first character of that string is an integer, 
	 * however, it will do nothing. 
	 *
	 * @param str The String that is to be changed by this method.
	 * @return newWord.trim() The new capitalized word is returned      
	 * 	created.
	 */
    private static String initialCap(String str){
    	String[] tokens = str.split(" ");  // splits events by white space
    	for(int i = 0; i < tokens.length; i++){ // moves through tokens list
    		if(!tokens[i].isEmpty()){ // checks if empty
    			char ch = tokens[i].charAt(0); 
    			//grabs initial character of each separated word
    			
    			if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')){
    				// checks if character is an actual letter not a number or 
    				//symbol
    				
    				ch = Character.toUpperCase(ch);// sets to upper
    				String word = ch + tokens[i].substring(1);
    				// merges upper case letter with rest of word
    				
    				tokens[i] = word;// adds newly capitalized word to list
    			}
    		}
    	}
    	String newWord = "";
    	for(int e = 0; e < tokens.length; e ++){
    		
    		// combines words in list back to string form
    		newWord = newWord + tokens[e] + " ";
    	}
    	return newWord.trim();// returns newly formed trimmed string
    	
    }
}
