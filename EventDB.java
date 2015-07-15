///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  EventDB.java
// File:             EventDB
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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** EventDB
 * This class is the data base for storing athletes and events.  It includes
 * several public methods for accessing, adding, and removing data from the
 * database.  It also stores objects of type Event in an arrayList to keep
 * track of the events that are in the database.
 *
 * @author Calvin Hareng and Nick Stamas
 */
public class EventDB{ 

	private ArrayList<Event> events;
	
	public EventDB (){
		
		events = new ArrayList<Event>();
		
	}
	
	/**
	 * addEvent(String t)
	 * This method creates an iterator that iterates through the events.  The name
	 * of the event that is passed in as a parameter, compared with each event
	 * already created.  If it is already in the database, the code breaks from the
	 * while loop and does not add the event.  If it goes through the entire
	 * database without finding a match, the event will be added to the DB. 
	 *
	 * @param t The event name that is compared with all of the events already           
	 * 	created.
	 */
	public void addEvent(String t){
		
		boolean check = true;
		Iterator<Event> itr = events.iterator();
		while (itr.hasNext()){
			if (itr.next().getType().equalsIgnoreCase(t)){
				check = false;
				break;
			}
			
		}
		if(check){
			Event newEvent = new Event(t);
			events.add(newEvent);
		}
	}
	
	/**
	 * addAthlete(String n, String t)
	 * This method creates an iterator, itr, and iterates through each event to 
	 * match the event passed into the parameter, t.  If t matches an event,
	 * it will check to make sure the athlete is not already in that event, and
	 * then add that athlete to the event.  If the event does not match, or if the
	 * athlete is already in that event, the athlete will not be added
	 *
	 * @param t The event name that is compared with the events already created
	 * @param n The athlete name that is compared with the athletes already added
	 * 
	 */
	public void addAthlete(String n, String t){
		
		Iterator<Event> itr1 = events.iterator();
		Event currentEvent;
		boolean eventExists = false;
		boolean contains = false;
		
		while(itr1.hasNext()){
			if(itr1.next().getType().equalsIgnoreCase(t)){
				eventExists = true;
			}
		}
		
		if(!eventExists){throw new java.lang.IllegalArgumentException("Error " +
				"event not found");}
		
		itr1 = events.iterator();
		while (itr1.hasNext()){
			currentEvent = itr1.next();
			if (currentEvent.getType().equalsIgnoreCase(t)){
				Iterator<String> itr2 = currentEvent.getRoster().iterator();
				while(itr2.hasNext()){
					String currAthlete = itr2.next();
					if(currAthlete.equalsIgnoreCase(n)){
						contains = true;
						break;
					}
				}
				if(!contains){
					currentEvent.getRoster().add(n);
				}
			
			}
		}
	}
	
	/**
	 * removeEvent(String t)
	 * This method creates an iterator, itr, and iterates through each event to
	 * match the event passed into the parameter, t.  If t matches an event,
	 * the method will remove the event, t, from the database, and it will return
	 * true.  If the event, t, does not match a current event, it will return false.
	 *
	 * @param t The event name that is compared with the events already created
	 * @return true if the event is removed and false if the event, t, is not
	 * 	in the database and therefore not removed.
	 */
	public boolean removeEvent(String t){
		Iterator<Event> itr = events.iterator();
		Event currentEvent;
		while (itr.hasNext()){
			currentEvent = itr.next();
			if (currentEvent.getType().equalsIgnoreCase(t)){
			   events.remove(currentEvent);
			   return true;
			}
			
		} return false;
	}
	
	/**
	 * containsEvent(String t)
	 * This method creates an iterator, itr, and iterates through each event to
	 * match the event passed into the parameter, t.  If t matches an event, 
	 * the method will return true, otherwise it will return false.
	 *
	 * @param t The event name that is compared with the events already created
	 * @return true if the event, t, is in the database otherwise false
	 */
	public boolean containsEvent(String t){
		Iterator<Event> itr = events.iterator();
		while (itr.hasNext()){
			if (itr.next().getType().equalsIgnoreCase(t)){
				return true;
			}
		}return false;
	}
	
	/**
	 * containsAthlete(String n)
	 * This method goes through each event and checks to see if any of the events
	 * contain the specified athlete, n.  If it does contain the athlete, it 
	 * returns true, else it returns false.
	 *
	 * @param n The athlete name that the method is searching for.
	 * @return true if the athlete is in the database, otherwise false.
	 */
	public boolean containsAthlete(String n){
		for (int i = 0; i < events.size(); i++){
			Iterator<String> itr2 = events.get(i).getRoster().iterator();
			while (itr2.hasNext()){
				String currAthlete = itr2.next();
				if(currAthlete.equalsIgnoreCase(n)){
					return true;
				}
			}
		}return false;
	}
	
	/**
	 * isRegistered(String n, String t)
	 * Returns true if the athlete, n, is registered in the event, t, otherwise
	 * the method returns false. 
	 *
	 * @param n The athlete name that the method is searching for
	 * @param t The event name that the method is searching for
	 * @return true if the athlete, n, is registered in the event, t.
	 */
	public boolean isRegistered(String n, String t){
		
		Iterator<Event> itr = events.iterator();
		Event currentEvent;
		while (itr.hasNext()){
			currentEvent = itr.next();
			if (currentEvent.getType().equalsIgnoreCase(t)){
				Iterator<String> itr2 = currentEvent.getRoster().iterator();
				while(itr2.hasNext()){
					String currAthlete = itr2.next(); 
					if(currAthlete.equalsIgnoreCase(n)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * getRoster(String t)
	 * Retrieves the athlete roster from the event, t, and returns it.  If the
	 * event is not in the database, it returns null. 
	 *
	 * @param t The event name that the method is searching for
	 * @return currentEvent.getRoster(), the roster, if the event is found, else it   
	 * 		returns null if the event is empty or not in the database.
	 */
	public List<String> getRoster(String t){
		Iterator<Event> itr = events.iterator();
		Event currentEvent;
		while (itr.hasNext()){
			currentEvent = itr.next();
			// checking to make sure event is register
			if (currentEvent.getType().equalsIgnoreCase(t)){
					return currentEvent.getRoster();
			}
		}
		return null;
	}
	
	/**
	 * getEvents(String n)
	 * Returns the list of events that the athlete, n, is in.  If the athlete, n,
	 * does not exist, or they are not registered in any events, the method
	 * returns null.
	 *
	 * @param n The athlete that the method is checking for
	 * @return nEventList, the list of events that the athlete is in, or returns
	 * 		null if the athlete is not in any events or if the athlete doesn't exist.
	 */
	public List<String> getEvents(String n){
		List<String> nEventList = new ArrayList<String>();
		for (int i = 0; i < events.size(); i++){
			if (isRegistered(n, events.get(i).getType())){
				nEventList.add(events.get(i).getType());
			}
		}
		if(!nEventList.isEmpty()){
			return nEventList;
		}else {return null;}
	}
	
	/**
	 * iterator()
	 * This method returns an iterator, itr, which iterates through a list of
	 * objects of type Event.
	 *
	 * @return itr, which is an iterator for objects of type Event
	 */
	public Iterator<Event> iterator() {
		Iterator<Event> itr = events.iterator();
		return itr;
	}
	
	/**
	 * size()
	 * This method returns the number of events that are stored in the database
	 *
	 * @return events.size(), which is the # of events in the DB
	 */
	public int size(){
		return events.size();
	}
	
	/**
	 * removeAthlete(String n)
	 * This method deletes an athlete from the database.
	 *
	 * @return isRemoved is true if the athlete is successfully removed from the 
	 * 	database and is false if the athlete is not in the database.
	 */
	boolean removeAthlete(String n){
		
		boolean isRemoved = false;
		for (int i = 0; i < events.size(); i++){
			if (isRegistered(n, events.get(i).getType())){
				for(int e = 0; e < events.get(i).getRoster().size(); e++){
					if(events.get(i).getRoster().get(e).equalsIgnoreCase(n)){
						events.get(i).getRoster().remove(e);
					}
				}
				isRemoved = true;
			}
		}
		return isRemoved;
	}
	
}
