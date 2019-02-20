/*
 * Name: Alan Penichet-Paul
 * EID: Ap46378
 */

import java.util.ArrayList;
import java.util.Arrays;
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
        /* TODO implement this function */
        return null; /* TODO remove this line */
    }  
}
