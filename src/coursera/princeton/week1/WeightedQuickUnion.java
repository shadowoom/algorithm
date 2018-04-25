package coursera.princeton.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;

public class WeightedQuickUnion {

    // keep track of corresponding parent
	private int[] idArr;
    // keep track of size of its subtree
    private int[] sz;
    // keep track of the largest element in the connected component
    private int[] num;
	private int count;

	public WeightedQuickUnion(int n) {
		this.count = n;
		this.idArr = new int[n];
        this.sz = new int[n];
        this.num = new int[n];
		for(int i = 0; i < n; i++){
			this.idArr[i] = i; 
            this.num[i] = i + 1;
            this.sz[i] = 1;
		}
	}

	public int count() {
        return this.count;
    }

    private void validate(int p) {
        int n = this.idArr.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    public String toString(){
        int n = this.idArr.length;
        int[] indices = new int[n];
        for(int i = 0; i < n; i++){
            indices[i] = i;
        }
        return "Union Array with indices: " + Arrays.toString(indices) + "\n" +
               "               connected: " + Arrays.toString(this.idArr);
    }

    private int root(int i){
    	validate(i);
    	while(i != this.idArr[i])
    		i = idArr[i];
    	return i;
    }

    public int findLargest(int i) {
        validate(i);
        int n = root(i);
        return this.num[n];
    }

    public boolean connected(int p, int q){
    	validate(p);
    	validate(q);
    	return root(p) == root(q);
    }

    public void union(int p, int q){
    	validate(p);
    	validate(q);
    	int i = root(p);
    	int j = root(q);
        if(i == j)
            return;
        if(this.sz[i] < sz[j]){
            this.idArr[i] = j;
            this.sz[j] += this.sz[i];
            this.num[j] = this.num[j] < this.num[i]? this.num[i]:this.num[j];
        }
        else{
            this.idArr[j] = i;
            this.sz[i] += this.sz[j];
            this.num[i] = this.num[j] < this.num[i]? this.num[i]:this.num[j];
        }
    	this.count--;
    }

    public static void main(String[] args) {
        // int n = StdIn.readInt();
        // QuickFind uf = new QuickFind(n);
        // while (!StdIn.isEmpty()) {
        //     int p = StdIn.readInt();
        //     int q = StdIn.readInt();
        //     if (uf.connected(p, q)) continue;
        //     uf.union(p, q);
        //     StdOut.println(p + " " + q);
        // }
        // StdOut.println(uf.count() + " components");
        String fileName = "largeUF.txt";
        In in = new In(fileName);
        int n = Integer.parseInt(in.readLine().trim());
        WeightedQuickUnion uf = new WeightedQuickUnion(n);
        final long startTime = System.currentTimeMillis();
        while(in.hasNextLine()){
            String inputLine = in.readLine();
            String[] inputArr = inputLine.trim().split("\\s+");
            int p = Integer.parseInt(inputArr[0]);
            int q = Integer.parseInt(inputArr[1]);
            if (uf.connected(p, q)){
                //StdOut.println(p + " " + q + " already connected");
                continue;
            } 
            uf.union(p, q);
            //StdOut.println(p + " " + q + " is now connected");
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
        StdOut.println(uf.count() + " components");
        //StdOut.println(uf.toString());
    }


}