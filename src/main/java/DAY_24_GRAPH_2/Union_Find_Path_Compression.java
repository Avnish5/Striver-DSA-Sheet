package DAY_24_GRAPH_2;

public class Union_Find_Path_Compression {

    int[] rank, parent;
    int n;

    public Union_Find_Path_Compression(int n) {
        this.n = n;
        rank = new int[n];
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            rank[i] = 0;
            parent[i] = i;
        }
    }

    public int find(int i) {
        if(parent[i] == i) return i;
        return parent[i] = find(parent[i]);
    }

    public void union(int x, int y) {
        int x_root = find(x);
        int y_root = find(y);

        if(x_root == y_root) return;

        if(rank[x_root] > rank[y_root]) {
            parent[y_root] = x_root;
        } else if (rank[x_root] < rank[y_root]) {
            parent[x_root] = y_root;
        } else {
            parent[x_root] = y_root;
            rank[y_root]++;
        }

    }
}
