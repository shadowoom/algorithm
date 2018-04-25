package coursera.princeton.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

public class SocialNetworkConnectivity {

	private int nonConnected;
	private int[] network;
	private int[] sz;

	public SocialNetworkConnectivity(int n) {
		if (n <= 0) 
			throw new  java.lang.IllegalArgumentException("the number of friend in the network should be greater than 0"); 
		this.nonConnected = n;
		this.network = new int[n];
		this.sz = new int[n];
		for (int i = 0; i < n; i++) {
			this.network[i] = i;
			this.sz[i] = 1;
		}
	}

	public boolean isAllConnected() {
		return this.nonConnected == 1;
	}

	public void validate(int p) {
		int n = this.network.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("input index " + p + " is not between 0 and " + (n-1));
        }
	}

	public int root(int i) {
		validate(i);
		while (i != this.network[i])
			i = this.network[i];
		return i;
	}

	public boolean isConnected(int i, int j) {
		validate(i);
		validate(j);
		return root(i) == root(j);
	}

	public void formFriendship(int i, int j) {
        validate(i);
        validate(j);
        int n = root(i);
        int m = root(j);
        if (n == m)
        	return;
        if (this.sz[n] < this.sz[m]) {
        	this.network[n] = m;
        	this.sz[m] += this.sz[n];
        }
        else {
        	this.network[m] = n;
        	this.sz[n] += this.sz[m];
        }
        this.nonConnected--;
    } 

    public static void main(String[] args){
    	String fileName = args[0];
    	In in = new In(fileName);
    	Stopwatch timer = new Stopwatch();
        int n = in.readInt();
        SocialNetworkConnectivity snc = new SocialNetworkConnectivity(n);
        int line = 0;
        while (!in.isEmpty()) {            
            int timestamp = in.readInt();
            int i = in.readInt();
            int j = in.readInt();        
            line++;
            snc.formFriendship(i, j);
            if (snc.isAllConnected()) {
                StdOut.println("file: " + fileName 
                            + "\ntimestamp: " + timestamp 
                            + "\nline: " + line 
                            + "\n(elapsed time: " + timer.elapsedTime() + ")");
                break;
            }
        }
        in.close();
    }

}