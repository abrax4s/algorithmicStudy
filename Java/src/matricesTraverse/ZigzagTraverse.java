package matricesTraverse;

import java.util.HashMap;
import java.util.Map;

public class ZigzagTraverse {
    /*
    You are given a 2D array of integer values where each cell represents a unique integer. The size of the matrix, n x n, ranges from 1 x 1 to 10 x 10, and each integer cell value, v, ranges from 1 to 100, inclusive.

Your task is to traverse the matrix in a unique way: Start from the top-left cell and move right until you hit the upper right corner. Then, move downward one cell and start moving to the left until you hit the left boundary. Upon hitting the left boundary, move down one cell and start moving right until you hit the right boundary. When you hit the right boundary, move down one cell and start moving left again. Continue this pattern until you have traversed every cell in the matrix.

Having completed this zigzag traversal, you will gather a list of traversed cell values. Your task now is to process this list and identify the values of the prime numbers and their indices. Therefore, implement the function zigzagTraverseAndPrimes(int[][] matrix) that returns a map where each key-value pair represents an index and the prime number found at that index from the traversed list.

For instance, suppose you have a 4x4 matrix:

{
    {10, 11, 4, 3},
    {6, 7, 15, 13},
    {8, 14, 1, 2},
    {5, 9, 12, 19}
}
Copy to clipboard
Upon completing the zigzag traversal, you obtain the list: {10, 11, 4, 3, 13, 15, 7, 6, 8, 14, 1, 2, 19, 12, 9, 5}. From this list, we observe that 11, 3, 13, 7, 2, 19, and 5 are prime numbers, and they are located at the 1st, 3rd, 4th, 6th, 11th, 12th, and 15th positions (0-indexed) in the list. Our function should return: {1: 11, 3: 3, 4: 13, 6: 7, 11: 2, 12: 19, 15: 5}.

Remember, a prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself. The first few prime numbers are 2, 3, 5, 7, 11, and so on.
    */

    private static boolean isPrime(int n) {
        if(n>1){
            for(int d = 2; d<=Math.sqrt(n); d++){
                if(n!=d && n%d==0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public Map<Integer, Integer> zigzagTraverseAndPrimes(int[][] matrix) {
        int rows = 0, cols = 0, direction = 1, row = 0, col = 0;
        Map<Integer, Integer> result = new HashMap<>();
        //Getting dimensions for the matrix
        rows = matrix.length; cols = matrix[0].length;
        System.out.println(String.format("Rows: %d, Cols: %d", rows, cols));
        for(int i = 0; i < rows*cols; i++){
            if(isPrime(matrix[row][col])){
                //since i starts at 0, no need to keep all traversed cells in a list: evaluation for prime numbers can be done and store directly to the result map, keeping i as the map's key
                result.put(i, matrix[row][col]);
                System.out.println(String.format("  i: %d, Cell: %d, Row: %d, Col: %d, Direction: %d",i, matrix[row][col], row, col, direction));

            }
            //steering direction
            if(direction==1){
                if(col==cols-1){
                    direction=-1;
                    row+=1;
                }else{
                    col+=1;
                }
            }else{
                if(col==0){
                    direction=1;
                    row+=1;
                }else{
                    col-=1;
                }
            }
        }

        return result;
    }
}