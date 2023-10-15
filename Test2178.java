import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test2178 {
	static int[] moveArrowX = {0, 1, 0, -1};
	static int[] moveArrowY = {-1, 0, 1, 0};

	static int matrixSizeX = 0;
	static int matrixSizeY = 0;

	static boolean[][] visited;
	static int[][] matrixValue;

	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			matrixSizeY = Integer.parseInt(st.nextToken());
			matrixSizeX = Integer.parseInt(st.nextToken());
			
			// initial
			visited     = new boolean[matrixSizeX][matrixSizeY];
			matrixValue = new int[matrixSizeX][matrixSizeY];
			
			// value setting
			for (int i=0; i<matrixSizeY; i++) {
				String inputValue   = br.readLine();
				for (int j=0; j<matrixSizeX; j++) {
					int matrixValueEach     = Integer.parseInt(inputValue.substring(j, j+1));
					matrixValue[j][i]   = matrixValueEach;
				}
			}
			
			BFS (0,0);
			System.out.println(matrixValue[matrixSizeX-1][matrixSizeY-1]);
		}
	}

	private static void BFS(int matrixX, int matrixY) {
		visited[matrixX][matrixY]   = true;
		Queue<int[]> bfsQueue = new LinkedList<>();
		bfsQueue.offer(new int[] { matrixX, matrixY });

		while (!bfsQueue.isEmpty()) {
			int[] nowMatrixXY   = bfsQueue.poll();
			int nowMatrixX      = nowMatrixXY[0];
			int nowMatrixY      = nowMatrixXY[1];
			for (int i=0; i<moveArrowX.length; i++) {
				int nextMoveXPosition   = nowMatrixX + moveArrowX[i];
				int nextMoveYPosition   = nowMatrixY + moveArrowY[i];
				int moveMatrixValue     = 0;
				if (nextMoveXPosition >= 0 && nextMoveYPosition >= 0
					&& nextMoveXPosition < matrixSizeX && nextMoveYPosition < matrixSizeY
					&& !visited[nextMoveXPosition][nextMoveYPosition]) {
					moveMatrixValue = matrixValue[nextMoveXPosition][nextMoveYPosition];
				}

				if (moveMatrixValue != 0) {
					bfsQueue.offer(new int[] {nextMoveXPosition, nextMoveYPosition});
					visited[nextMoveXPosition][nextMoveYPosition]   = true;
					matrixValue[nextMoveXPosition][nextMoveYPosition] = matrixValue[nowMatrixX][nowMatrixY] + 1;
				}
			}
		}
	}
}