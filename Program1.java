/*
 * Name: Alan Penichet-Paul
 * EID: Ap46378
 */

import java.util.ArrayList;
import java.util.LinkedList;

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
    		if(allocation.getUserMatching().get(usr)==-1) {
    			//for every server
    			for(int srv = 0; srv< server_count; srv++) {
    				//for every slot
    				int[] srv_rank = toRank(allocation, srv);
    				int srv_slots = allocation.getServerSlots().get(srv);
    				for(int slot = 0; slot < srv_slots; slot++) {
    					//if(srv_rank[usr] < 0)
    					
    				}
    			}
    		}
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
    	
    	
    	
    	
//    	int user_count = allocation.getUserMatching().size();
//    	int server_count = allocation.getServerCount();
//    	ArrayList<Integer> user_matching = allocation.getUserMatching();
//    	ArrayList<Integer> server_slots = allocation.getServerSlots();
//    	ArrayList<ArrayList<Integer>> server_preference = allocation.getServerPreference();
//    	ArrayList<ArrayList<Integer>> user_preference = allocation.getUserPreference();
//    	
//    	//create list of server matching
//    	ArrayList<ArrayList<Integer>> server_matching = new ArrayList<ArrayList<Integer>>();
//    	ArrayList<Integer> empty = new ArrayList<Integer>();
//    	//creates nested lists for each server
//    	for(int srv_count = 0; srv_count < server_count; srv_count++) {
//    		ArrayList<Integer> new_srv = new ArrayList<Integer>(); 
//    		server_matching.add(srv_count, new_srv);
//    	}
//    	//for every user
//        for(int user = 0; user<user_count;user++) {
//        	//get matching
//        	int server = user_matching.get(user);
//        	//if empty, add to empty
//        	if(server == -1) {
//        		empty.add(user);
//        	}
//        	else {
//        			//if server list exists
//        			server_matching.get(server).add(user);
//        		}
//        	}
//        //for every user
//        for(int usr = 0; usr<user_count;usr++) {
//        		// for every server
//        		for(int ser = 0; ser < server_count ;ser++) {
//        			//for every matching
//        			for(int slo = 0; slo< server_slots.get(ser);slo++) {
//        				int inc = 0;
//        				int match = server_matching.get(ser).get(slo);
//        				//while current match is not in first x of pref, incr
//        				while(server_preference.get(ser).get(inc) != match) {
//        				// if we find usr before current match, its unstable
//        				    if(server_preference.get(ser).get(inc) == usr) {
//        				    	//if u is not matched return false
//        			        	if(user_matching.get(usr)==-1) {
//        			        		return false;
//        			        		}
//        			        	//or u prefers s>s'
//        			        	else {
//        			        		int num = 0;
//        			        		int new_server = user_matching.get(usr);
//        			        		while(user_preference.get(match).get(num) != ser) {
//        			        			//if equals new server, return false
//        			        			if(user_preference.get(match).get(num) == new_server) {
//        			        				return false;
//        			        			}
//        			        			else {
//        			        				//increment
//        			        				num++;
//        			        			}
//        			        		}
//        			        	}
//        				    }
//        			        	
//        				    else {
//        				    	inc++;
//        				    }
//        			}
//        			
//        		}
//        	}
//        }
//        
//        return true;

    /**
     * Determines a solution to the Stable Marriage problem from the given input
     * set. Study the project description to understand the variables which
     * represent the input to your solution.
     * 
     * @return A stable Matching.
     */
    public Matching stableMarriageGaleShapley(Matching allocation) {
        /* TODO implement this function */
        return null; /* TODO remove this line */
    }  
}
