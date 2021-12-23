import java.util.function.Function;

public class MainClass {
	static Function<Integer, Integer> incrementByOne = number -> number + 1;
	static Function<Integer, Integer> multiplyByTen = number -> number * 10;
	static Function<Integer, Integer> incrementThenMulitply = incrementByOne.andThen(multiplyByTen);

	public static void main(String[] args) {

		int number1 = incrementByOne.apply(2);
		System.out.println(number1);
		int number2 = multiplyByTen.apply(number1);
		System.out.println(number2);

		int number = incrementThenMulitply.apply(2);
		System.out.println(number);

	}

}
