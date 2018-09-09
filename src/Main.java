import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Main {
	private static void executeIgorImpl() {
		List<ResultSet> list = new ArrayList<ResultSet>();
		
		int[] t = new int[] {1, 3, 6, 4, 1, 2}; // 5
		IgorImpl.test(t, Optional.of(5), list);
		
		t = new int[] {1, 2, 3}; // 4
		IgorImpl.test(t, Optional.of(4), list);
		
		t = new int[] {-1, -3}; // 1
		IgorImpl.test(t, Optional.of(1), list);
		
		for (int i = 0; i < 5; i++) {
			int l = getRandomNumberInRange(1, 10);
			int arr[] = new int[l];
			
			for (int j = 0; j < l; j++) {
				arr[j] = getRandomNumberInRange(Integer.MIN_VALUE+1, Integer.MAX_VALUE-1);
			}
			
			IgorImpl.test(arr, Optional.empty(), list);
		}
		
		printResults(list, "C:/igor.txt");
	}
	
	private static void executeZoongImpl() {
		List<ResultSet> list = new ArrayList<ResultSet>();
		
		int[] t = new int[] {1, 3, 6, 4, 1, 2}; // 5
		ZoongImpl.test(t, Optional.of(5), list);
		
		t = new int[] {1, 2, 3}; // 4
		ZoongImpl.test(t, Optional.of(4), list);
		
		t = new int[] {-1, -3}; // 1
		ZoongImpl.test(t, Optional.of(1), list);
		
		for (int i = 0; i < 5; i++) {
			int l = getRandomNumberInRange(1, 10);
			int arr[] = new int[l];
			
			for (int j = 0; j < l; j++) {
				arr[j] = getRandomNumberInRange(Integer.MIN_VALUE+1, Integer.MAX_VALUE-1);
			}
			
			ZoongImpl.test(arr, Optional.empty(), list);
		}
		
		printResults(list, "C:/zoong.txt");
	}
	
	public static void main(String[] args) {
		// warm up...
		
		List<Integer> ggg = new ArrayList<Integer>();
		
		for (int i = 0; i < 100000; i++) {
			ggg.add(getRandomNumberInRange(i, Integer.MAX_VALUE-1));
		}
		
		ggg.stream().sorted().forEachOrdered(i -> {});
		
		// finish warming
		
		executeIgorImpl();
		executeZoongImpl();
	}
	
	public static int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).findFirst().getAsInt();
	}
	
	public static void writeToFile(String path, String text) throws IOException {
		File f = new File(path);
		if (f != null && (f.exists() || f.createNewFile()) && text != null) writeToFile(f, text);
	}
	
	public static void writeToFile(File f, String text) throws IOException {
		if (text != null) {
			OutputStreamWriter output = null;
			BufferedWriter buffered = null;
			
			try {
				output = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
				buffered = new BufferedWriter(output);
				
				try {
					if (buffered != null) buffered.write(text);
				} catch (IOException ex) {
					ex.printStackTrace();
				} finally {
					if (buffered != null) buffered.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (output != null) output.close();
			}
		}
	}
	
	private static void printResults(List<ResultSet> list, String destFileName) {
		try {
			StringBuilder sb = new StringBuilder();
			int n = 1;
			long avTime1 = 0L;
			long avTime2 = 0L;
			
			int mistakes1 = 0;
			int mistakes2 = 0;
			
			int mismatch = 0;
			
			for (ResultSet rs : list) {
				avTime1 += rs.getResultOne().getTime();
				avTime2 += rs.getResultTwo().getTime();
				
				if (rs.getResultOne().isAMistake()) mistakes1++;
				if (rs.getResultTwo().isAMistake()) mistakes2++;
				if (rs.getResultOne().getResult() != rs.getResultTwo().getResult()) mismatch++;
				
				sb.append("ResultSet ")
					.append(n)
					.append("\nInput array: ")
					.append(Arrays.toString(rs.getResultOne().getArray()))
					.append(rs.getResultOne().getAnswer().isPresent() ? "\nCorrect answer: "+rs.getResultOne().getAnswer().get() : "")
					.append("\n\n*** Method \"")
					.append(rs.getResultOne().getMethodName())
					.append("\" ***\nresult: ")
					.append(rs.getResultOne().getResult())
					.append("\ntime: ")
					.append(rs.getResultOne().getTime())
					.append("ms\nseems to be correct: ")
					.append(rs.getResultOne().doesResultSeemToBeCorrect())
					.append(rs.getResultOne().getAnswer().isPresent() ? "\nis correct: "+rs.getResultOne().isAMistake() : "")
					.append("\n\n*** Method - \"")
					.append(rs.getResultTwo().getMethodName())
					.append("\" ***\nresult: ")
					.append(rs.getResultTwo().getResult())
					.append("\ntime: ")
					.append(rs.getResultTwo().getTime())
					.append("ms\nseems to be correct: ")
					.append(rs.getResultTwo().doesResultSeemToBeCorrect())
					.append(rs.getResultTwo().getAnswer().isPresent() ? "\nis correct: "+rs.getResultTwo().isAMistake() : "")
					.append("\n-----------------------------------\n");
				
				n++;
			}
			
			sb.append("\n")
				.append("Statistics: \n")
				.append("Average time: \n\tmethod 1: ")
				.append(avTime1 / n)
				.append("ms")
				.append("\n\tmethod 2: ")
				.append(avTime2 / n)
				.append("ms")
				.append("\nMistakes: \n\tmethod 1: ")
				.append(mistakes1)
				.append("\n\tmethod 2: ")
				.append(mistakes2)
				.append("\nMismatches: ")
				.append(mismatch);
			
			writeToFile(destFileName, sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
