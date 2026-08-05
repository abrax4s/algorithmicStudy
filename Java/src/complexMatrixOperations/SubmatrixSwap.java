package complexMatrixOperations;


public class SubmatrixSwap {
  /*
  Warren, an innovator in mathematical problems, challenges you to solve a complex task involving matrix manipulation. He provides you with a 2D array, M, with dimensions m x n, where m and n range from 1 to 500, inclusive. Each element in the matrix ranges from -100 to 100, inclusive.

Warren further provides you with the coordinates describing two sub-matrices within M, denoted as S1 and S2. He asks you to write a Java function, say submatrixSwap(), which takes as inputs the matrix M and the coordinates specifying the sub-matrices S1 and S2. This function is required to swap the positions of S1 and S2 within M.

The swapping of sub-matrices is subject to the following constraints:

The sub-matrices do not overlap.
S1 and S2 must have identical dimensions, i.e., the number of rows and columns in S1 must equal the number of rows and columns in S2.
The coordinates of each submatrix are given by 4 coordinates - {row_l, row_r, col_l, col_r}, which correspond to a valid submatrix with rows in [row_l, row_r) and columns in [col_l, col_r).
Example

Let's consider an example to clarify the task:

Suppose 'M' is:

int[][] M = {{1, 2, 3, 4, 5},
             {6, 7, 8, 9, 10},
             {11, 12, 13, 14, 15},
             {16, 17, 18, 19, 20},
             {21, 22, 23, 24, 25}};
With sub-matrix S1 defined by the coordinates 0, 2, 2, 4 (indicating that it spans from rows 0 to 1 and columns 2 to 3), and S2 given the coordinates 2, 4, 0, 2.

Our function submatrixSwap(matrix, new int[]{0, 2, 2, 4}, new int[]{2, 4, 0, 2}) should obtain the following swapped matrix:

M = {{1, 2, 11, 12, 5},
     {6, 7, 16, 17, 10},
     {3, 4, 13, 14, 15},
     {8, 9, 18, 19, 20},
     {21, 22, 23, 24, 25}};
Explanation:

In this scenario, the sub-matrix S1 spans rows 0 to 1 and columns 2 to 3 (0-indexed) and includes the elements 3, 4, 8, 9. The sub-matrix S2 spans rows 2 to 3 and columns 0 to 1 (0-indexed) and includes the elements 11, 12, 16, 17.

The function submatrixSwap() swaps the positions of S1 and S2 within the matrix M. As a result, the columns 2 and 3 in rows 0 and 1 have been replaced by S2, and the columns 0 and 1 in rows 2 and 3 have been replaced by S1.
  */

    public void submatrixSwap(int[][] matrix, int[] coord_S1, int[] coord_S2) {
        // TODO: Implement the function that swaps coord_S1 and coord_S2 in the matrix
    /*
    ejemplo de coordenadas s1 y s2:
    int[] coord_S1 = {0, 2, 0, 2};
      int[] coord_S2 = {3, 5, 0, 2};
    coord structure: {row_start,row_end,col_start,col_end}
    row_l = start row, row_r = end row
    0. asignar coords a punteros:
      int rowStS1 = coord_S1[0],   rowStS2 = coord_S2[0],
          rowEndS1 = coord_S1[1],  rowEndS2 = coord_S2[1],
          colStS1 = coord_S1[2],   colStS2 = coord_S2[2],
          colEndS1 = coord_S1[3],  colEndS2 = coord_S2[3];
    1. obtener largo y ancho de submatrices:
      sRows = rowEndS2-rowStS2
      sCols = colEndS2-colStS2
    2. crear S1 y S2
      int[][] S1 = new int[sRows][sCols];
      int[][] S2 = new int[sRows][sCols];
    3. bucle de i=0 hasta i<sRows{
        k1 = rowStS1
        k2 = rowStS2
        bucle de j=0 a j<sCols{
          l1 = colStS2
          l2 = colStS2
          S1[i][j] = matrix[k1][l1]
          S2[i][j] = matrix[k2][l2]
          l1++;l2++
        }
        k1++;k2++
      }
    4. bucle de i=0 hasta i<rows{
        k1 = rowStS1
        k2 = rowStS2
        bucle de j = 0 hasta j<cols{
          l1 = colStS2
          l2 = colStS2
          matrix[k1][l1] = S1[i][j]
          matrix[k2][l2] = S2[i][j]
        }
    }
    */

        int rowStS1 = coord_S1[0], rowStS2 = coord_S2[0],
                colStS1 = coord_S1[2], colStS2 = coord_S2[2],
                rowEndS1 = coord_S1[1],
                colEndS1 = coord_S1[3];
        int sRows = rowEndS1-rowStS1, sCols = colEndS1-colStS1;
        int[][] S1 = new int[sRows][sCols];
        int[][] S2 = new int[sRows][sCols];

        for(int i = 0; i<sRows;i++){
            for(int j = 0; j<sCols; j++){
                S1[i][j] = matrix[rowStS1+i][colStS1+j];
                S2[i][j] = matrix[rowStS2+i][colStS2+j];
            }
        }

        for(int i = 0; i<sRows;i++){
            for(int j= 0; j<sCols;j++){
                matrix[rowStS1+i][colStS1+j] = S2[i][j];
                matrix[rowStS2+i][colStS2+j] = S1[i][j];
            }
        }
    }
}