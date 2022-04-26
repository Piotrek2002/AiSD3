import java.util.Scanner;

public class Main {
    private static final FileModule fileModule = new FileModule();
    private static final ConsoleModule consoleModule = new ConsoleModule();

    public static void main(String[] args) {
        System.out.println("1 - Wczytaj zpliku: ");
        System.out.println("2- Wczytaj z konsoli: ");
        Scanner scanner=new Scanner(System.in);
        int x=scanner.nextInt();
        System.out.println("-----------------");
        if (x==1){
            boolean[] visited = new boolean[fileModule.getVertex()];
            int[][] matrix=fileModule.getDataFromFile();
            AdjacencyMatrix adjacencyMatrix =new AdjacencyMatrix(matrix);
            GraphMatrix graphMatrix=new GraphMatrix(matrix);
            fileModule.print(matrix);
            fileModule.print(graphMatrix.getGraphMatrix());
            if (adjacencyMatrix.topologicalSort(fileModule.getVertex())){
                System.out.println();
                adjacencyMatrix.dfs(0,visited);
            }
        }else if (x==2){
            boolean[] visited = new boolean[consoleModule.getVertex()];
            int[][] matrix=consoleModule.getDataFromFile();
            AdjacencyMatrix adjacencyMatrix =new AdjacencyMatrix(matrix);
            GraphMatrix graphMatrix=new GraphMatrix(matrix);
            consoleModule.print(matrix);
            consoleModule.print(graphMatrix.getGraphMatrix());
            if (adjacencyMatrix.topologicalSort(consoleModule.getVertex())){
                System.out.println();
                adjacencyMatrix.dfs(0,visited);
            }
        }


    }
}
