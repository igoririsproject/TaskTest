
public class ResultSet {
	private Result result1;
	private Result result2;
	
	public boolean areResultsEqual() {
		if (result1 == null || result2 == null) return false;
		return result1.getResult() == result2.getResult();
	}
	
	public Result getResultOne() {
		return result1;
	}
	
	public Result getResultTwo() {
		return result2;
	}
	
	public ResultSet setResultOne(Result r) {
		result1 = r;
		return this;
	}
	
	public ResultSet setResultTwo(Result r) {
		result2 = r;
		return this;
	}
}
