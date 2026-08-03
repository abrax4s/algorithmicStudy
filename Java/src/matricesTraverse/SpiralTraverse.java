package matricesTraverse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*-------------------------------------------------------------------

Consider a grid of characters in the form of a 2D array, where each cell represents a distinct character selected from a-z. Your task is to process this grid following a specific order.

Start from the top-left cell of the grid and move in a clockwise spiral direction. Initially, go right until you hit the right boundary, then down until you reach the bottom boundary, then left until you encounter the left boundary, and finally, up until you hit the top boundary (note that the top boundary is now the first row since we already visited the first cell in the matrix). Once this cycle is complete, move inwards, i.e., one cell to the right, and repeat the spiral process within the remaining unvisited cells.

During this spiral traversal, you will generate a sequence of visited cell characters. Afterwards, identify the vowels (a, e, i, o, u) in the sequence and return their positions in a 0-indexed order.

Please implement the function spiralTraverseAndVowels(char[][] grid) to achieve this. This function takes a 2D array of characters (grid) as input and returns an array containing the positions of the vowels in the spirally traversed sequence.

For instance, consider the following 3x4 grid:

{{'a', 'b', 'c', 'd'},
{'e', 'f', 'g', 'h'},
{'i', 'j', 'k', 'l'}}
Copy to clipboard
Upon completing the spiral traversal, we will obtain the sequence: {'a', 'b', 'c', 'd', 'h', 'l', 'k', 'j', 'i', 'e', 'f', 'g'}. From this sequence, we observe that 'a', 'i', and 'e' are vowels and are located at the 0th, 8th, and 9th positions in the sequence, so our function returns: {0, 8, 9}.

The size of the 2D array (grid) will not exceed 100x100, and each character will be a lowercase letter from 'a' to 'z'.

--------------------------------------------------------------------*/
public class SpiralTraverse {
    public int[] spiralTraverseAndVowels(char[][] grid) {
        int rows = 0, cols = 0, top = 0, bottom = 0, left = 0, right = 0, direction = 1, row = 0, col = 0;
        //Defining vowels set to validate each char in the grid
        Set<Character> vowels = new HashSet<>(Arrays.asList('a','e','i','o','u'));
        //Getting the dimensions from the grid
        rows = grid.length; cols = grid[0].length;
        int[] result = new int[]{};
        List<Integer> resultList = new ArrayList<>();
        //setting up outer boundaries before starting the loop
        bottom = rows-1; right = cols-1;
        for(int i = 0; i < rows*cols; i++){
            //since i starts at 0, we can use it to keep track of the position of the vowels in a zero-indexed order, using arrayList to have dynamic dimensions, we can convert this to array before returning
            System.out.println(String.format("\n------------------------------\nRow: %d, Col: %d, \n  Top: %d, Right: %d, Bottom: %d, Left: %d ", row, col, top, right, bottom, left));
            System.out.print(grid[row][col]);
            if(vowels.contains(grid[row][col])){
                resultList.add(i);
                resultList.forEach(v -> System.out.print(v+", "));
            }

            //logic to traverse the array in a spiral direcion starting from the top-left pos.
            //direction can be right(1), down(2), left(3) or up(4)
            /*
            char[][] grid = {
        {'w', 'e', 's'},
        {'i', 'i', 't'},
        {'l', 'i', 'f'},
        {'e', '.', '!'}
    };
            */

            switch(direction){
                case 1:
                    if(col==right){
                        direction = 2;
                        //right = col-1;
                        top+=1;
                        row += 1;
                    }else{
                        col+=1;
                    }
                    break;
                case 2:
                    if(row == bottom){
                        direction = 3;
                        //bottom = row-1;
                        right-=1;
                        col-=1;
                    }else {
                        row+=1;
                    }
                    break;
                case 3:
                    if(col == left){
                        direction = 4;
                        //left = col+1;
                        bottom-=1;
                        row-=1;
                    }else {
                        col-=1;
                    }
                    break;
                case 4:
                    if(row == top){
                        direction = 1;
                        //top = row+1;
                        left+=1;
                        col+=1;
                    }else {
                        row-=1;
                    }
                    break;
            }
        }
        //returning list as an array
        return resultList.stream().mapToInt(i -> i).toArray();
    }
}