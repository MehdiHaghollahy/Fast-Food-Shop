import java.util.Arrays;


public class Gragh {

    private int[][] adjMatrix;

    private String[] vertexData;

    private int size;

    public Gragh(int size){
        this.size = size;
        this.adjMatrix = new int[size][size];
        this.vertexData = new String[size];
    }

    public void addEdge(int u , int v , int weight){
        adjMatrix[u][v] = weight;
        adjMatrix[v][u] = weight;
    }

    public void addVertexData(int vertex , String data){
        vertexData[vertex] = data;
    }

    public String dijkstra(String startVertexData, String endVertexData) {
        int[] predecessors = new int[size];
        Arrays.fill(predecessors, -1);
        int startVertex = Arrays.asList(vertexData).indexOf(startVertexData);
        int endVertex = Arrays.asList(vertexData).indexOf(endVertexData);
        int[] distances = new int[size];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;
        boolean[] visited = new boolean[size];

        for (int count = 0; count < size - 1; count++) {
            int u = minDistance(distances, visited);

            if (u == -1) {
                break;
            }

            if (u == endVertex) {
                break;
            }

            visited[u] = true;


            for (int v = 0; v < size; v++) {
                if (!visited[v] && adjMatrix[u][v] != 0 && distances[u] != Integer.MAX_VALUE && distances[u] + adjMatrix[u][v] < distances[v]) {
                    distances[v] = distances[u] + adjMatrix[u][v];
                    predecessors[v] = u;
                }
            }
        }

        String shortestPath = getPath(distances, predecessors, startVertex, endVertex);
        return "Path: " + shortestPath + ", Distance: " + distances[endVertex];
    }

    private int minDistance(int[] distances, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < size; v++) {
            if (!visited[v] && distances[v] <= min) {
                min = distances[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public String getPath(int[] distances, int[] predecessors, int startVertex, int endVertex) {
        if (endVertex == -1 || distances[endVertex] == Integer.MAX_VALUE) {
            return "No path";
        }

        StringBuilder path = new StringBuilder();
        for(int vertex = endVertex; vertex != -1; vertex = predecessors[vertex]) {
            path.insert(0, vertexData[vertex]);
            if(vertex != startVertex) {
                path.insert(0, "->");
            }
        }
        return path.toString();
    }
}

