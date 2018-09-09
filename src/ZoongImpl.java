import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ZoongImpl {
	private static int getMin(int arr[]) {
        //sorting
        Arrays.sort(arr);
        
        //searching
        for (int i = 0; i < arr.length; i++) {// main loop iterates sorted elements
            if (arr[i] > 0) {
                if (i + 1 < arr.length && arr[i + 1] != arr[i] + 1) {
                    return arr[i] + 1;
                }
            }
        }
        
        return -1000000;
    }

	private static int getMin_(int arr[]) {
        int min = arr[0];
        int max = arr[0];

        //find max and min
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min && arr[i] > 0) min = arr[i];
        }
        
        // why list here?
        @SuppressWarnings("unused")
		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        for (int i = min; i < max; i++) {
            int j = i;// for lambda context
            boolean contains = IntStream.of(arr).anyMatch(x -> x == j);
            
            if (contains == false) {
                return j;
            }
        }

        return -1000000;
    }
    
    public static String test(int args[], Optional<Integer> answer) {
    	StringBuilder sb = new StringBuilder();
    	long start, finish;
    	sb.append("\nCalculating min for: "+Arrays.toString(args));
    	// no need to init class
    	sb.append("\n\nRunning method 1...");
        start = System.currentTimeMillis();
        int r1 = getMin(args);
        finish = System.currentTimeMillis();
        sb.append("\ntime: "+(finish - start)+"ms");
        sb.append("\nanswer is: "+r1);
        
        if (answer.isPresent()) {
        	if (r1 != answer.get().intValue()) {
        		sb.append("\nAnswer is not right: result is "+r1+", while answer is "+answer.get());
        	}
        }
        
        sb.append("\n\nRunning method 2...");
        start = System.currentTimeMillis();
        int r2 = getMin_(args);
        finish = System.currentTimeMillis();
        sb.append("\ntime: "+(finish - start)+"ms");
        sb.append("\nanswer is: "+r2);
        
        if (answer.isPresent()) {
        	if (r2 != answer.get().intValue()) {
        		sb.append("\nAnswer is not right: result is "+r2+", while answer is "+answer.get());
        	}
        }
         
        if (r1 != r2) sb.append("\nResults does not match: "+r1+" and " +r2);
        sb.append("\n###############################################");
        return sb.toString();
    }
}
