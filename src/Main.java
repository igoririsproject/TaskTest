import java.util.Optional;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append("Running sample tests...\n");
		int[] t = new int[] {1, 3, 6, 4, 1, 2}; // 5
		sb.append(ZoongImpl.test(t, Optional.of(5)));
		t = new int[] {1, 2, 3}; // 4
		sb.append(ZoongImpl.test(t, Optional.of(4)));
		t = new int[] {-1, -3}; // 1
		sb.append(ZoongImpl.test(t, Optional.of(1)));
		sb.append("Sample tests done!");
		
		for (int i = 0; i < 5; i++) {
			int l = getRandomNumberInRange(1, 10);
			int arr[] = new int[l];
			
			for (int j = 0; j < l; j++) {
				arr[j] = getRandomNumberInRange(Integer.MIN_VALUE+1, Integer.MAX_VALUE-1);
			}
			
			System.out.println(sb.append(ZoongImpl.test(arr, Optional.empty()))
				.toString());
		}
		
		
	}
	
	public static int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).findFirst().getAsInt();
	}
}
