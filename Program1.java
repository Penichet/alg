/*
 * Name: Alan Penichet-Paul
 * EID: Ap46378
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the
     * Stable Marriage problem. Study the description of a Matching in the
     * project documentation to help you with this.
     */
    public boolean isStableMatching(Matching allocation) {
    	int user_count = allocation.getUserCount();
    	int server_count = allocation.getServerCount();
    	for(int usr = 0; usr< user_count; usr++) {
    		//if usr empty
    		int current_matching = allocation.getUserMatching().get(usr);
    		//debug
    		//System.out.println(" matching : " + allocation.getUserMatching().toString());
    		if(current_matching == -1) {
    			//for every server
    			for(int srv = 0; srv< server_count; srv++) {
    				//make array with rank as [num]
    				int[] srv_rank = toRank(allocation, srv);
    				int srv_slots = allocation.getServerSlots().get(srv);
    				int[] srv_matches = new int[srv_slots];
    				//initializes server matches array to -1 for consistency
    				Arrays.fill(srv_matches, -1);
    				int user_num = 0;
    				//array match count
    				int count = 0;
    				//for every integer in user matching
    				for(int potential_match : allocation.getUserMatching()) {
    					// if matched to server, add to array of matches for server
    					if(potential_match == srv){
    						//if matched, add to array and icnrement array count
    						srv_matches[count] = user_num;
    						count++;
    					}
    					user_num++;
    				}
    				//now we have array with matches for every slot
    				//and count == number of matches
    				for(int slot = 0; slot < srv_slots; slot++) {
    					//if slot is empty, it contains -1
    					int match = srv_matches[slot];
    					if(match == -1) {
    						//if open slot in server, unstable
    						return false;
    					}
    					if(srv_rank[usr] < srv_rank[match]) {
    						//if server prefers u' to its match, unstable
    						return false;
    					}
    				}
    			}
    		}
    		else {
    			//same start as above
    			for(int srv = 0; srv< server_count; srv++) {
    				int[] srv_rank = toRank(allocation, srv);
    				int srv_slots = allocation.getServerSlots().get(srv);
    				int[] srv_matches = new int[srv_slots];
    				Arrays.fill(srv_matches, -1);
    				int user_num = 0;
    				int count = 0;
    				//for every integer in user matching
    				for(int potential_match : allocation.getUserMatching()) {
    					// if matched to server, add to array of matches for server
    					if(potential_match == srv){
    						//if matched, add to array and increment array count
    						srv_matches[count] = user_num;
    						count++;
    					}
    					user_num++;
    				}
    				//now we have array with matches for every slot
    				//and count == number of matches
    				if(current_matching != srv) {
    					//edge case where server has 0 slots, dont want to fail
    					if(count ==0 && srv_slots!= 0){
    						return false;
    					}
	    				for(int slot = 0; slot < srv_slots; slot++) {
	    					//if slot is empty, it contains -1
	    					int match = srv_matches[slot];
	    					if(match == -1) {
	    						return false;
	    					}
	    					//if s prefers usr to match
	    					if(srv_rank[usr] < srv_rank[match]) {
	    						//if usr prefers server to usr match
	    						int[] usr_rank = toRankUsr(allocation, usr);
	    						if(usr_rank[srv] < usr_rank[current_matching]) {
	    							return false;
	    						}
	    					}
	    				}
    				}
    			}
    			
    		}
    	}
    	return true;
    }
    	
    public int[] toRankUsr(Matching match, int user){
    	//new int array of size of server count
    	int[] temp = new int[match.getServerCount()];
    	// preference list for a server
    	ArrayList<Integer> pref = match.getUserPreference().get(user);
    	int count = 0;
    	// for every slot, change value to rank
    	for(int slot : pref) {
    		temp[slot] = count;
    		count++;
    	}
    	//returns rank in temp[index]
    	return temp;
    }
    
    public int[] toRank(Matching match, int server){
    	//new int array of size of user count
    	int[] temp = new int[match.getUserCount()];
    	// preference list for a server
    	ArrayList<Integer> pref = match.getServerPreference().get(server);
    	int count = 0;
    	// for every slot, change value to rank
    	for(int slot : pref) {
    		temp[slot] = count;
    		count++;
    	}
    	//returns rank in temp[index]
    	return temp;
    }

    /**
     * Determines a solution to the Stable Marriage problem from the given input
     * set. Study the project description to understand the variables which
     * represent the input to your solution.
     * 
     * @return A stable Matching.
     */
    public Matching stableMarriageGaleShapley(Matching allocation) {
    	int server_slots=0;
    	int user_count = allocation.getUserCount();
    	ArrayList<Integer> user_matching = new ArrayList<Integer>();
    	ArrayList<Integer> open_slots = allocation.getServerSlots();
    	//initially all unmatched
    	for(int usr = 0; usr < user_count; usr++) {
    		user_matching.add(usr, -1);
    	}
    	allocation.setUserMatching(user_matching);
    	Queue<Integer> free_servers = new LinkedList<Integer>();
    	ArrayList<Queue<Integer>> PropAvail = new ArrayList<Queue<Integer>>();
    	int count = 0;
    	//total slots available
    	for (int slot : allocation.getServerSlots()){
    		server_slots += slot;
    		free_servers.add(count);
    		//want to copy pref list to queue to remove once they've proposed for each server?
    		Queue<Integer> unproposed = new LinkedList<Integer>();
    		ArrayList<Integer> pref_list = allocation.getServerPreference().get(count);
    		//copy pref to unproposed
    		for(int usr = 0; usr < user_count; usr++) {
    			int pref = pref_list.get(usr);
    			unproposed.add(pref);
    		}
    		PropAvail.add(unproposed);
    		//add to propavail
    		count++;
    	}
    	//at this point we have an queue of free servers, arraylist with queues of unproposed
    	while(server_slots>0) {
    		int server = free_servers.peek();
    		//gets list of unproposed
    		Queue<Integer> users_avail = PropAvail.get(server);
    		//peeks top of the unproposed --- NEED TO REMOVE IF PROPOSAL SUCCESFUL
    		int user_pot = users_avail.peek();
    		users_avail.remove(); // remove regardless
    		//if new accepted, decrease server slots by 1, otherwise keep same as one freed one taken
    		boolean decrease = proposecheck(allocation, server, user_pot, PropAvail, open_slots);
    		if(decrease) {
    			server_slots-=1;
    		}
    		//check here if servers are free or taken now after proposals -- NEED TO DO FOR ALL
    		for(int srv = 0; srv < allocation.getServerCount(); srv++) {
	    		if(free_servers.contains(srv)){ //if "free" make sure it's not got 0 spots left
	    			if(open_slots.get(srv) == 0) {
	    				free_servers.remove(srv);
	    			}
	    		}
	    		else {
	    			if(open_slots.get(srv)>0) { //if "closed" make sure it's not got a slot left
	    				free_servers.add(srv);
	    			}
	    		}
    		}
    		
    		//TODO add server matching to see if still open slots in specific server
    		//using get server slots at top loop?
    		
    	}
        return allocation; /* TODO remove this line */
    }  
    public boolean proposecheck(Matching allocation, int server, int user, ArrayList<Queue<Integer>> PropAvail, ArrayList<Integer> open_slots) {
    	ArrayList<Integer> new_matching = allocation.getUserMatching();
    	int current_server = new_matching.get(user);
    	if(current_server == -1) {
    		//user is free, proceed with matching
    		new_matching.set(user, server);
    		//update matching
    		allocation.setUserMatching(new_matching);
    		//subtract one from open slots for server
    		open_slots.set(server, open_slots.get(server)-1);
    		return true;
    	}
    	else {
    		//already proposed, check if prefer new server
    		int[] usr_rank = toRankUsr(allocation, user);
    		if(usr_rank[server] < usr_rank[current_server]) {
    			//match to new server
    			new_matching.set(user, server);
    			// update matching
    			allocation.setUserMatching(new_matching);
    			//free old server, take spot from new server. WOuld hypothetically onlt get here if >0
    			open_slots.set(server, open_slots.get(server)-1);
    			open_slots.set(current_server, open_slots.get(current_server)+1);
    		}
    		return false;
    	}
    }
}
