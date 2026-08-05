package matricesTraverse;

import java.util.List;
import java.util.ArrayList;

public class MatrixBoundaryTraverse {

    /*

You are tasked with creating a Java method named matrixBoundaryConcatenation(). This method should accept two 2D matrices, matrixA and matrixB, and the number of boundary layers, n, to extract from both matrices.

In this context, a boundary layer refers to the elements that form the outer contour of a matrix. For instance, the first layer of the following 4x4 matrix includes the elements 1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, and 5:

1  2  3  4
5  6  7  8
9  10 11 12
13 14 15 16

Your method should extract the first n boundary layers from both matrixA and matrixB. It should then concatenate these extracted layers into a new list, ensuring that the layers from matrixA precede those from matrixB in the resultant list.

The matrices matrixA and matrixB will be square matrices, with each side's length ranging from 1 to 10. The number of layers n will be less than or equal to the side length of the square matrices.

The method signature should be:

public List<Integer> matrixBoundaryConcatenation(int[][] matrixA, int[][] matrixB, int n);
The elements in the input matrices can be any integer between -100 and 100.

Example

Consider the following input to our method:

Java
Copy to clipboard
int[][] matrixA = { {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16} };

int[][] matrixB = { {17, 18, 19, 20},
                    {21, 22, 23, 24},
                    {25, 26, 27, 28},
                    {29, 30, 31, 32} };

int n = 2;
Our method matrixBoundaryConcatenation(matrixA, matrixB, n) should return:

Arrays.asList(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10, 17, 18, 19, 20, 24, 28, 32, 31, 30, 29, 25, 21, 22, 23, 27, 26)
Explanation:

In matrixA, the first boundary layer is composed of the elements 1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, and 5, taken in a clockwise direction from the top-left corner. Our second layer then includes the elements 6, 7, 11, and 10.

For matrixB, the corresponding boundary layers include the elements 17, 18, 19, 20, 24, 28, 32, 31, 30, 29, 25, 21 for the first layer and 22, 23, 27, 26 for the second one.

The method outputs a list where the extracted layers from matrixA are followed by those from matrixB.



    */
    public List<Integer> matrixBoundaryConcatenation(int[][] matrixA, int[][] matrixB, int n) {
        // TODO: implement the function that extracts 'n' boundary layers from matrixA and matrixB,
        // merges them into a single list and then returns this new list.

        /*
        0. El recorrido de matrices se puede extraer a un método privado que retorne una lista de int y se referencíe una vez por cada matriz, realizando un addAll en el resultado antes de hacer el retorno.
        1.Dentro del método de recorrido, definir las variables que se van a utilizar:
            int side = matrix.length,
            ArrayList resultList
        2.  Se anidan tres loops dentro de un loop principal
        */
        List<Integer> result = new ArrayList<>();
        result.addAll(boundaryTraverse(matrixA, n));
        result.addAll(boundaryTraverse(matrixB, n));

        return result;
    }

    private List<Integer> boundaryTraverse(int[][] matrix, int n){
        int side = matrix.length;
        List<Integer> traversed = new ArrayList<>();

        for(int k = 0; k<n;k++){
            for(int col = k; col <= side-1-k; col++){//traverse top row rightwards
                traversed.add(matrix[k][col]);
            }
            for(int row = k+1; row <= side-1-k; row++){//traverse right column downwards
                traversed.add(matrix[row][side-1-k]);
            }
            for(int col = side-2-k; col>=k;col--){//traverse bottom row leftwards
                traversed.add(matrix[side-1-k][col]);
            }
            for(int row = side-2-k; row>k ;row--){//traverse left column upwards
                traversed.add(matrix[row][k]);
            }
        }

        return traversed;
    }
}

