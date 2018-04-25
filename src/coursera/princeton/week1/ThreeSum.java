/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coursera.princeton.week1;

import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Zhang Chen
 */
public class ThreeSum {
    
    private final int[] arr;
    private int count;
    private HashMap<Integer, Integer> map;
    private List<String> resultList;
    
    public ThreeSum(int[] inputArray) {
        this.arr = inputArray;
        this.count = 0;
        this.map = new HashMap();
        this.resultList = new ArrayList();
    }
    
    public void computeThreeSum() {
        int n = this.arr.length;
        for (int i = 0; i < n - 2; i++) {
            int val = 0 - this.arr[i];
            for(int j = i + 1; j < n; j++) {
                if (this.map.containsKey(this.arr[j])) {
                    this.count++;
                    this.resultList.add("index: " + i + ", " + this.map.get(this.arr[j]) + ", " + j);
                }
                else {
                    int val2 = val - this.arr[j];
                    this.map.put(val2, j);
                }
            }
            this.map.clear();
        }
    }
    
    public int getCount() {
        return this.count;
    }
    
    public List<String> getResultList() {
        return this.resultList;
    }
    
    public static void main(String[] args) {
        int[] inputArr = new int[]{1,2,3,4,5,6,7,8,-1,-2,-3,-4,-5,-6};
        ThreeSum ts = new ThreeSum(inputArr);
        ts.computeThreeSum();
        StdOut.println("The number of 3-sum is: " + ts.getCount());
        List<String> resultList = ts.getResultList();
        resultList.forEach((str) -> {
            StdOut.println(str);
        });
    }
}
