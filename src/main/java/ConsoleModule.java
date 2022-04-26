import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsoleModule {
    private int vertex;
    private int edge;

    public int[][] getDataFromFile() {

            Scanner scanner = new Scanner(System.in);
            vertex = scanner.nextInt();
            edge = scanner.nextInt();
            int[][] adjacencyMatrix = new int[vertex][vertex];
            for (int i = 0; i < vertex; i++) {
                for (int j = 0; j < vertex; j++) {
                    adjacencyMatrix[i][j] = 0;
                }
            }
            for (int i = 0; i < edge; i++) {
                adjacencyMatrix[scanner.nextInt()][scanner.nextInt()] = 1;
            }
            return adjacencyMatrix;



    }
    public void print(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public int getEdge() {
        return edge;
    }

    public void setEdge(int edge) {
        this.edge = edge;
    }
}
