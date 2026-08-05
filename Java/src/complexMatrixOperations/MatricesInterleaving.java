package complexMatrixOperations;


public class MatricesInterleaving {
    /*
    Your task is to write a function, interleaveMatrices, that takes two matrices (2D arrays) and a start and end range for rows and columns for each matrix as inputs. Instead of concatenating submatrices together, this task requires interleaving the columns from the submatrices within the final matrix.
If A and B are your two matrices, and the respective submatrices selected from them based on the given ranges are sub_A and sub_B, then the task is to form a new matrix C by interleaving columns from sub_A and sub_B. Starting with the first column of sub_A, alternately include a column from sub_A and a column from sub_B until all columns from both submatrices are included.
All matrices are filled with integers. The size of each matrix, A and B, ranges between 1×1 and 10×10, inclusive, and each element in the matrix is from the range of −100 to 100, inclusive. The start and end ranges for rows and columns for each matrix are provided as an array {start_row, end_row, start_column, end_column}, and these are 0-based indices.

For example, if A is:

{{1, 2, 3, 4},
{5, 6, 7, 8},
{9, 10, 11, 12}}

and B is:

{{11, 12, 13},
{14, 15, 16},
{17, 18, 19}}

If we select 2x2 submatrices from each (comprising the 2nd to the 3rd rows and the 2nd to the 3rd columns from A, and the 1st to the 2nd rows and the 1st to the 2nd columns from B), their interleaved combination would look like this:

{//this is what the matrixcoords should look like
    {1,2,1,2},
    {0,1,0,1}
}

{{6, 11, 7, 12},
{10, 14, 11, 15}}

Note that in the output, columns from sub_A and sub_B are interwoven.

It is guaranteed that the given submatrices have pairwise equal dimensions.
    */
    public int[][] interleaveMatrices(int[][] matrixA, int[][] matrixB, int[][] submatrixCoords) {
        // TODO: Implement the solution here.
        /*
        1. Obtener puntos de anclaje para las submatrices a partir de submatrixCoords: subARowSt, subArowEnd, subAcolSt, subAColEnd, subBRowSt, subBRowEnd,SubBColSt, SubBcolEnd
        2. obtener dimensiones de subA y de subB para poder crearlas; solo es necesario calcular rows y cols 1 vez, por las restricciones del problema: pairwise equal dimensions significa que tendrán igual cantidad de rows y dimensiones, se suman +1 para compensar 0-index
        {
            {1,2,1,2},
            {0,1,0,1}
        }
            rows = subArowEnd - subARowst +1
            cols = subBColEnd - subBColSt +1
        3. crear subA[rows][cols], subB[rows][cols]
        4. poblar suba y subB
            bucle de i=0 hasta i <rows -> basta con un solo bucle porque ambas matrices tienen mismo largo y ancho
                bucle de j= 0 jasta j< cols
                        subA[i][j] = matrixA[subARowSt+i][subAColSt+j]
                        subB[i][j] = matrixB[subBRowSt+i][suBColSt+j]
        5. crear matrizc[rows][2*cols]
        6. poblar matrizC <---------nbecesario profundizar
            bucle de i = 0 hasta matrizC.largo
                bucle de j= 0 hasta j<cols
                    matrizC[i][j] = subA[i][j]
                    matrizC[i][2j+1] = subB[i][j]
                    {
                        a{
                            {0,2}.
                            {0,2}
                        }
                        b{
                            {1,3},
                            {1,3}
                        }
                          a b a b
                        0{0,1,2,3},
                        1{0,1,2,3}
                    }
                    1. i=0, j= 0
                        0, 0 = a0,0
                        0, 2*0+1(1) = b0,0
                    2. i=0, j=1
                        0, 1 = a0,1
                        0,2*1+1(3) (2)=b0,1

        */

        int subARowSt = submatrixCoords[0][0],
                subARowEnd = submatrixCoords[0][1],
                subAColSt = submatrixCoords[0][2],
                subAColEnd = submatrixCoords[0][3],
                subBRowSt = submatrixCoords[1][0],
                subBColSt = submatrixCoords[1][2],
                rows = subARowEnd - subARowSt + 1,
                cols = subAColEnd - subAColSt +1;
        int[][] subA = new int[rows][cols];
        int[][] subB = new int[rows][cols];
        int[][] matrixC = new int[rows][cols*2];

        for(int i = 0; i<rows;i++){
            for(int j = 0; j< cols; j++){
                subA[i][j] = matrixA[subARowSt+i][subAColSt+j];
                subB[i][j] = matrixB[subBRowSt+i][subBColSt+j];
            }
        }

        for(int i = 0; i<rows;i++){
            for(int j = 0; j< cols; j++){
                matrixC[i][2*j] = subA[i][j];
                matrixC[i][(2*j)+1] = subB[i][j];
            }
        }

        return matrixC;
    }
}