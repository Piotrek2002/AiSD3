import java.util.*;


public class GraphMatrix {
    private int[][] graphMatrix;
    private int[][] adjacencyMatrix;

    public GraphMatrix(int[][] adjacencyMatrix) {
        graphMatrix = new int[adjacencyMatrix.length][adjacencyMatrix.length + 3];
        Data[] successorsList = new Data[adjacencyMatrix.length];
        Data[] predecessorsList = new Data[adjacencyMatrix.length];
        Data[] noIncidentList = new Data[adjacencyMatrix.length];

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            successorsList[i] = new Data();
            predecessorsList[i] = new Data();
            noIncidentList[i] = new Data();
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                noIncidentList[i].addElement(j + 1);
            }
        }
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    successorsList[i].addElement(j + 1);
                    predecessorsList[j].addElement(i + 1);
                    noIncidentList[i].removeElement(j + 1);
                    noIncidentList[j].removeElement(i + 1);
                }
            }
        }

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (successorsList[i].getList().size() == 0) {
                graphMatrix[i][adjacencyMatrix.length] = 0;
            } else {
                graphMatrix[i][adjacencyMatrix.length] = successorsList[i].getList().get(0);
                for (int j : successorsList[i].getList()) {
                    graphMatrix[i][j - 1] = successorsList[i].getList().get(successorsList[i].getList().size() - 1);
                }
            }
            if (predecessorsList[i].getList().size() == 0) {
                graphMatrix[i][adjacencyMatrix.length + 1] = 0;
            } else {
                graphMatrix[i][adjacencyMatrix.length + 1] = predecessorsList[i].getList().get(0);
                for (int j : predecessorsList[i].getList()) {
                    graphMatrix[i][j - 1] = predecessorsList[i].getList().get(predecessorsList[i].getList().size() - 1) + adjacencyMatrix.length;
                }
            }
            if (noIncidentList[i].getList().size() == 0) {
                graphMatrix[i][adjacencyMatrix.length + 2] = 0;
            } else {
                graphMatrix[i][adjacencyMatrix.length + 2] = noIncidentList[i].getList().get(0);
                for (int j : noIncidentList[i].getList()) {
                    graphMatrix[i][j - 1] = -noIncidentList[i].getList().get(noIncidentList[i].getList().size() - 1);
                }
            }

        }
    }

    private List<Integer> DEL_mgrafu(int[][] matrix) {
        List<Integer> lista = new ArrayList<>();
        int n = matrix.length;
        int index = 0;
        for (int j = 0; j < n; j++) {
            List<Integer> w_niezalezne = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                w_niezalezne.add(matrix[i][matrix[i].length - 2]);
            }
            if (!w_niezalezne.equals(0)) {
                String val = "Graf zawiera cykl. Sortowanie niemozliwe";
            }
            for (int i = 0; i < n; i++) {
                if (matrix[i][matrix[i].length - 2] == 0) {
                    index = i + 1;
                    lista.add(index);
                    matrix[i][matrix[i].length - 2] = -1;
                    break;
                }

            }

        }


        for (int i = 0; i < n; i++) {
            matrix[i][index - 1] = -1;
            if (matrix[i][index - 1] == -1) {
                continue;
            } else if (Arrays.stream(matrix[i]).max().orElse(0) > n) {
                matrix[i][matrix[i].length - 2] = Arrays.asList(matrix[i]).indexOf((Arrays.stream(matrix[i]).max().orElse(0)) + 1);
            } else {
                matrix[i][matrix[i].length - 2] = 0;
            }
        }


        return lista;
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


    public int[][] getGraphMatrix() {
        return graphMatrix;
    }
}
