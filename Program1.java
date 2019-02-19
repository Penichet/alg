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
    	//create list of server matching
     ArrayList<ArrayList<Integer>> server_matching = new ArrayList<ArrayList<Integer>>();
     ArrayList<Integer> empty = new ArrayList<Integer>();
    	//for every user
        for(int i = 0; i<allocation.getUserCount();i++) {
        	//get matching
        	int server = allocation.getUserMatching().get(i);
        	//if empty, add to empty
        	if(server == -1) {
        		empty.add(i);
        	}
        	else {
        		//else, gets array list and adds matching to server
        	server_matching.get(server).add(i);        	
        	}
        }
        //for every user
        for(int usr = 0; usr<allocation.getUserCount();usr++) {
        		// for every server
        		for(int ser = 0; ser < allocation.getServerCount();ser++) {
        			//for every matching
        			for(int slo = 0; slo< allocation.getServerSlots().get(ser);slo++) {
        				int inc = 0;
        				int match = server_matching.get(ser).get(slo);
        				//while current match is not in first x of pref, incr
        				while(allocation.getServerPreference().get(ser).get(inc) != match) {
        				// if we find usr before current match, its unstable
        				    if(allocation.getServerPreference().get(ser).get(inc) == usr) {
        				    	//if u is not matched return false
        			        	if(allocation.getUserMatching().get(usr)==-1) {
        			        		return false;
        			        		}
        			        	//or u prefers s>s'
        			        	else {
        			        		int num = 0;
        			        		int new_server = allocation.getUserMatching().get(usr);
        			        		while(allocation.getUserPreference().get(match).get(num) != ser) {
        			        			//if equals new server, return false
        			        			if(allocation.getUserPreference().get(match).get(num) == new_server) {
        			        				return false;
        			        			}
        			        			else {
        			        				//increment
        			        				num++;
        			        			}
        			        		}
        			        	}
        				    }
        			        	
        				    else {
        				    	inc++;
        				    }
        			}
        			
        		}
        	}
        }
        
        return true;
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
