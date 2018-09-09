import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Result {
	private int[] array;
	
	private long time = 0L;
	
	private Optional<Integer> answer = Optional.empty();
	private int result = 1;
	
	private String methodName;
	
	public boolean doesResultSeemToBeCorrect() {
		if (answer.isPresent()) return result == answer.get().intValue();
		
		List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
		
		if (result > 1) {
			if (list.stream().noneMatch(i -> i.intValue() == 1)) {
				return false;
			}
		}
		
		return list.stream().noneMatch(i -> i.intValue() == result);
	}
	
	public Optional<Integer> getAnswer() {
		return answer;
	}
	
	public int[] getArray() {
		return array;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public int getResult() {
		return result;
	}
	
	public long getTime() {
		return time;
	}
	
	public boolean isAMistake() {
		if (!answer.isPresent()) return false;
		return result != answer.get().intValue();
	}
	
	public Result setAnswer(int answ) {
		answer = Optional.of(answ);
		return this;
	}
	
	public Result setArray(int[] arr) {
		array = arr;
		return this;
	}
	
	public Result setAnswer(Optional<Integer> answ) {
		if (answ != null && answ.isPresent()) answer = answ;
		return this;
	}
	
	public Result setMethodName(String str) {
		methodName = str;
		return this;
	}
	
	public Result setResult(int r) {
		result = r;
		return this;
	}
	
	public Result setTime(long t) {
		time = t;
		return this;
	}
}
