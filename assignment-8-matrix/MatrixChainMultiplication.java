import java.util.Scanner;

class MatrixChainMultiplication {

    // Matrix Ai has dimension p[i-1] x p[i] for i = 1..n
    static int MatrixChainOrder(int p[], int n) {
        /*
         * For simplicity of the
         * program, one extra row and
         * one extra column are allocated in m[][]. 0th row
         * and 0th column of m[][] are not used
         */
        int m[][] = new int[n][n];

        int i, j, k, L, q;

        /*
         * m[i, j] = Minimum number of scalar
         * multiplications needed to compute the matrix
         * A[i]A[i+1]...A[j] = A[i..j] where
         * dimension of A[i] is p[i-1] x p[i]
         */

        // cost is zero when multiplying one matrix.
        for (i = 1; i < n; i++)
            m[i][i] = 0;

        // L is chain length.
        for (L = 2; L < n; L++) {
            for (i = 1; i < n - L + 1; i++) {
                j = i + L - 1;
                if (j == n)
                    continue;
                m[i][j] = Integer.MAX_VALUE;
                for (k = i; k <= j - 1; k++) {
                    // q = cost/scalar multiplications
                    q = m[i][k] + m[k + 1][j]
                            + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j])
                        m[i][j] = q;
                }
            }
        }

        return m[1][n - 1];
    }

    // Driver code
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);

        int count = 0;

        while (count < 1) {
            System.out.println("Enter the number of matrices you wish to multiply:");
            int number_of_matrices = input.nextInt();

            // Creating an array of dimensions to be entered which will be always 1 greater than the number of matrices
            // Refer to the example 
            int[] dimensions = new int[number_of_matrices + 1];

            // Entering the dimensions
            // If there are 3 matrices with dimensions 10 X 20, 20 X 30, 30 X 15
            // The dimension will be entered as 10, 20, 30, 15 - Hence there are 1 more dimensions than there are matrices
            for (int i = 0; i <= number_of_matrices; i++) {
                System.out.print("Please enter dimension " + i + " : ");
                dimensions[i] = input.nextInt();
            }


            // Initialize the cost matrix (M)
            int[][] m = new int[number_of_matrices + 1][number_of_matrices + 1];

            // Initialize the parenthesis matrix (S)
            int[][] s = new int[number_of_matrices + 1][number_of_matrices + 1];

            for (int i = 1; i <= number_of_matrices; i++) {
                m[i][i] = 0;
            }

            int total_number_of_multiplications;

            // Here index refers to the max number of outputs that need to be calculated in each row
            // For example for index = 1 i.e. row 1 and number of matrices = 4 the number of outputs = 3
            for (int index = 1; index < number_of_matrices; index++) {

                for (int left = 1; left <= number_of_matrices - index; left++) {
                    
                    int right = left + index;
                    
                    m[left][right] = Integer.MAX_VALUE;

                    // Here 'k' refers to the k in Matrix mutiplication formula
                    for (int k = left; k < right; k++) {
                        total_number_of_multiplications = m[left][k] + m[k+1][right] + dimensions[left] * dimensions[k] * dimensions[right];

                        if (total_number_of_multiplications < m[left][right]) {
                            m[left][right] = total_number_of_multiplications;
                            s[left][right] = k;
                        }
                    }
                }
            }

            System.out.println("\n\n\tM Matrix\n");
            for(int i = 1; i <= number_of_matrices; i++ ) {
                for(int j = 1; j <= number_of_matrices; j++ ){
                    if(i <= j) {
                        System.out.print( m[ i ][ j ]+ "\t" );
                    }
                    else {
                        System.out.print( "\t" );
                    }   
                }
                System.out.println("\n");
            }

            System.out.println("\tS Matrix\n");
		    for(int i = 1; i <= number_of_matrices; i++ ) {
                for(int j = 1; j <= number_of_matrices; j++ ) {
                    if( i<=j ) {
                        System.out.print( s[ i ][ j ]+ "\t" );
                    }
					else {
                        System.out.print( "\t" );
                    }
			}
			    System.out.println("\n");
		    }

            count++;
        }

        

       

        // int arr[] = new int[] { 1, 2, 3, 4 };
        // int size = arr.length;

        // System.out.println(
        //         "Minimum number of multiplications is "
        //                 + MatrixChainOrder(arr, size));
    }
}
