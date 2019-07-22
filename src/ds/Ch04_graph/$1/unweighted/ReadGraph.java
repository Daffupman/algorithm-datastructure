package ds.Ch04_graph.$1.unweighted;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Daffupman
 * @description 从文件中读取图
 * @date 2019/7/18 20:58
 */
public class ReadGraph {

	private Scanner scanner;

	public ReadGraph(Graph graph,String filename) {
		readFile(filename);

		try {

			int V = scanner.nextInt();
			if(V < 0) {
				throw new IllegalArgumentException("number if vertices in a graph must not be negative.");
			}
			assert V == graph.V();

			int E = scanner.nextInt();
			if(E < 0) {
				throw new IllegalArgumentException("number if edges in a graph must not be negative.");
			}

			for (int i = 0; i < E; i++) {
				int v = scanner.nextInt();
				int w = scanner.nextInt();

				assert v >=0 && v < V;
				assert w >=0 && w < V;
				graph.addEdge(v, w);
			}

		} catch (InputMismatchException ie) {
			String token = scanner.next();
			throw new InputMismatchException("attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"");
		} catch (NoSuchElementException ne) {
			throw new NoSuchElementException("attempts to read an 'int' value from input stream, but there are no more tokens available");
		}
	}

	private void readFile(String filename) {
		assert filename != null;
		try {
			File file  = new File(filename);
			if(file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				scanner = new Scanner(new BufferedInputStream(fis),"utf-8");
				scanner.useLocale(Locale.ENGLISH);
			} else {
				throw new IllegalArgumentException(filename+" doesn't exists.");
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Could not open "+filename, e);
		}
	}

}
