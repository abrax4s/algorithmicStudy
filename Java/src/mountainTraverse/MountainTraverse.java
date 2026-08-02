package mountainTraverse;

import java.util.ArrayList;

class MountainTraverse {

// TODO: Define the pathTraverse function which takes a mountain matrix and the current position (row, column) as parameters.
// The function should return an ArrayList of int arrays representing the coordinates of the path taken,
// starting from the initial position and moving to each higher adjacent cell.

    private static ArrayList<int[]> traversePath(int[][] mountain, int row, int col){

        ArrayList<int[]> path = new ArrayList<>();
        int[][] directions = new int[][]{
                {-1,0},//arriba
                {1,0}, //abajo
                {0,-1}, //izq
                {0,1} //der
        };

        path.add(new int[]{row,col});
        while(true){
            int currentMax = 0, nextX=0, nextY=0;
            for(int[] dir : directions){
                int r = row + dir[0];
                int c = col + dir[1];
                if(isWithinBounds(mountain.length, r) &&
                        isWithinBounds(mountain[0].length, c)){
                    if(mountain[r][c] > currentMax){
                        currentMax = mountain[r][c];
                        nextX = r;
                        nextY = c;
                    }
                }
            }

            if(currentMax <= mountain[row][col]){
                break;
            }

            row = nextX;
            col = nextY;
            path.add(new int[]{row,col});
        }

        return path;

    }

    private static boolean isWithinBounds(int length, int val){
        return val >= 0 && val < length;
    }



    public static void main(String[] args) {

// TODO: Create a matrix named 'mountain' representing ascending values, akin to the increasing elevation while hiking up a mountain.

        int[][] mountain = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}

        };

        traversePath(mountain, 1, 1).stream().forEach(p -> System.out.println(p[0]+","+p[1]));

    }

}