package pass;

import java.util.Random;

public class RandomString {

	  private static char[] letters;
	  private static char[] numbers;

	  static {
	    StringBuilder tmp = new StringBuilder();
	    for (char ch = 'A'; ch <= 'Z'; ++ch)
	      tmp.append(ch);
	    letters = tmp.toString().toCharArray();
	  }
	  static {
	    StringBuilder tmp = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch)
	      tmp.append(ch);
	    numbers = tmp.toString().toCharArray();
	  } 

	  private final Random random = new Random();

	  private final char[] buf;

	  public RandomString(int length) {
	    if (length < 1)
	      throw new IllegalArgumentException("length < 4: " + length);
	    buf = new char[length];
	  }

	  public String nextString() {
		  buf[0] = letters[random.nextInt(letters.length)];
		  buf[1] = letters[random.nextInt(letters.length)];
		  
	    for (int idx = 2; idx < buf.length; ++idx) 
	      buf[idx] = numbers[random.nextInt(numbers.length)];
	    return new String(buf);
	  }
	}