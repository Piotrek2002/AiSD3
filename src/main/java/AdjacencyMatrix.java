import java.util.*;

public class AdjacencyMatrix {
    private int[][] adjacencyMatrix;

    public AdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public void dfs(int start, boolean[] visited) {

        System.out.print(start + " ");

        visited[start] = true;

        for (int i = 0; i < adjacencyMatrix[start].length; i++) {

            if (adjacencyMatrix[start][i] == 1 && (!visited[i])) {
                dfs(i, visited);
                break;
            }else if (checkBoolean(visited) && i==adjacencyMatrix.length-1){
                dfs(getLastFreeValue(visited), visited);
            }
        }
    }
    boolean checkBoolean(boolean[] visited){
        for (boolean v:visited){
            if (!v){
                return true;
            }
        }
        return false;
    }
    int getLastFreeValue(boolean[] visited){
        for (int i=0;i< visited.length; i++){
            if (!visited[i]){
                return i;
            }
        }
        return 0;
    }

    public boolean topologicalSort(int v) {
        int[] indegree = new int[v];

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                indegree[i] = indegree[i] + adjacencyMatrix[j][i];
            }
        }

        Queue<Integer> q
                = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        Vector<Integer> topOrder = new Vector<>();
        while (!q.isEmpty()) {

            int u = q.poll();
            topOrder.add(u);
            setZero(u,v);

            for (int i = 0; i < v; i++) {
                if (getIncomes(i) == 0 && !topOrder.contains(i) && !q.contains(i))
                    q.add(i);
            }

        }

        if (topOrder.size()!= adjacencyMatrix.length){
            System.out.println("There exists a cycle in the graph");
            return false;
        }else {
            for (int i : topOrder) {
                System.out.print(i+" ");
            }
            return true;
        }

    }

    private int getIncomes(int v) {
        int sum = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (j == v) {
                    sum += adjacencyMatrix[i][j];
                }
            }
        }
        return sum;
    }
    public void print(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }
    }
    private void setZero(int u,int v){
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i==u){
                    adjacencyMatrix[i][j]=0;
                }
            }
        }
    }
}
