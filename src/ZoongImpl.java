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
    
    public static void test(int args[], Optional<Integer> answer, List<ResultSet> results) {
    	ResultSet set = new ResultSet();
    	long start, finish;
    	
        start = System.currentTimeMillis();
        int r1 = getMin(args);
        finish = System.currentTimeMillis();
        
        set.setResultOne(new Result()
        	.setAnswer(answer)
        	.setArray(args)
        	.setMethodName("1")
        	.setResult(r1)
        	.setTime(finish - start)); 
        
        start = System.currentTimeMillis();
        int r2 = getMin_(args);
        finish = System.currentTimeMillis();
        
        set.setResultTwo(new Result()
           	.setAnswer(answer)
           	.setArray(args)
           	.setMethodName("2")
           	.setResult(r2)
           	.setTime(finish - start)); 
        
        results.add(set);
    }
}
