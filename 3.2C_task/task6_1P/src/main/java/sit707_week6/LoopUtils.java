package sit707_week6;
public class LoopUtils {
	
    public static int sumUpTo(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i; 
        }
        return sum;
    }
    public static int countEvenNumbers(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                count++;
            }
        }
        return count;
    }
}



