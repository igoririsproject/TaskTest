import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IgorImpl {
	private static int getMin(int[] arr) {
		int min = 1;
		Arrays.sort(arr);
		int l = arr.length;
		
		for (int i = 0; i < l; i++) {
			int e = arr[i];
			if (e < 1) continue;
			
			if (e < min && i + 1 < l && e + 1 < arr[i + 1]) {
				return e + 1;
			}
			
			if (e == min) min = e + 1;
		}
		
		return min;
	}
	
	public static int getMinUsingList(int[] arr) {
		List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());		
		int[] r = new int[] {1};
		
		list.stream()
			.forEach(i -> {
				if (i > 0) {
					int min = r[0];
					
					if (list.stream().noneMatch(j -> j.intValue() == min)) {
						r[0] = min;
					} else {
						r[0] = i + 1;
					}
				}
			});
		
		return r[0];
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
        int r2 = getMinUsingList(args);
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
