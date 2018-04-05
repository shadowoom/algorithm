/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coursera;

/**
 *
 * @author Zhang Chen
 */
public class SearchBitonicArray {
    
    public static int findTurningPoint(int l, int r, int[]arr) {
        int m = l + (r-l)/2;
        if(arr[m] > arr[m-1] && arr[m] > arr[m+1])
            return m;
        else if(arr[m] > arr[m-1] && arr[m] < arr[m+1])
            return findTurningPoint(m, r, arr);
        else
            return findTurningPoint(l, m, arr);
    }
    
    public static int searchBitonicArray(int t, int s, int[]arr) {
        if (arr[t] == s)
            return t;
        else {
            int temp = ascendingBinarySearch(s, 0, t - 1, arr);
            if(temp != -1)
                return temp;
            else
                return descendingBinarySearch(s, t+1, arr.length-1, arr);
        }
    }
    
    public static int ascendingBinarySearch(int s, int l, int r, int[]arr) {
        while (l <= r) {
            int m = l + (r -l)/2;
            if (arr[m] == s)
                return m;
            else if (arr[m] > s)
                r = m - 1;
            else
                l = m + 1;
        }
        return -1;
    }
    
    public static int descendingBinarySearch(int s, int l, int r, int[]arr) {
        while (l <= r) {
            int m = l + (r -l)/2;
            if (arr[m] == s)
                return m;
            else if (arr[m] > s)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }
    
    public static void main(String[] args){
        int[] arr = new int[]{-8, 1, 2, 3, 4, 5, -2, -3};
        int key = 2;
        int l = 0;
        int r = arr.length - 1;
        int t = findTurningPoint(l, r, arr);
        int index = searchBitonicArray(t, key, arr);
        if (index == -1)
            System.out.println("Element Not Found");
        else
            System.out.println("Element Found at index " + index);
    }
    
}
