import java.time.*;

public class _2016년 {

	static 
	
	public String solution(int a, int b) {
		int month[] = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String yoil[] = { "FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU" };
		int sum = 0;
		for (int i = 0; i < a - 1; i++)
			sum += month[i];
		sum += b - 1;
		return yoil[sum % 7];
	}
	
	public String solution2(int a, int b) {
		LocalDate date = LocalDate.of(2016, a, b);
		return date.getDayOfWeek().toString().substring(0,3);
	}
	
	public String solution3(int a, int b) {
		String day = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		Calendar c = Calendar.getInstance();
		c.set(2016, a-1, b);
		return day[c.get(Calendar.DAY_OF_WEEK)-1];
	}
}
