import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class TestClient {

  static void p(Object o){
    System.out.println(o);
  }

  public static void main(String[] args) {

    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] blocks = new int[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        blocks[i][j] = in.readInt();
    Board initial = new Board(blocks);
    Solver solver = new Solver(initial);
    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }

}


/*

 Optional<SearchNode>  newMethod(MinPQ priorityQueue){





private Optional<SearchNode> findGoalBoard(){

   while (true) {

      Optional<SearchNode> result = newMethod(minPriorityQueue);
      if (result.isPresent()){
       return result}
      }

     Optional<SearchNode> twinResult = newMethod(twinMinPriorityQueue);
      if (twinResult.isPresent()){
       return twinResult}
      }

}

}

 */