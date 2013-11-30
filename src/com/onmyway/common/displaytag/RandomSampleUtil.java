package com.onmyway.common.displaytag;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Title:
 * @Description: 
 * @Create on: Jan 5, 2011 9:41:39 PM
 * @Author:LJY
 * @Version:1.0
 */
public class RandomSampleUtil {
	private static String[] words = { "Lorem", "ipsum", "dolor", "sit", "amet", "consetetur", "sadipscing", "elitr", "sed", "diam", "nonumy", "eirmod", "tempor", "invidunt", "ut", "labore", "et", "dolore", "magna", "aliquyam", "erat", "sed", "diam", "voluptua", "At", "vero", "eos", "et", "accusam", "et", "justo", "duo", "dolores", "et", "ea", "rebum", "Stet", "clita", "kasd", "gubergren", "no", "sea", "takimata", "sanctus", "est" };

	  private static Random random = new Random();

	  public static String getRandomWord()
	  {
	    return words[random.nextInt(words.length)];
	  }

	  public static String getRandomSentence(int wordNumber)
	  {
	    StringBuffer buffer = new StringBuffer(wordNumber * 12);

	    int j = 0;
	    while (j < wordNumber)
	    {
	      buffer.append(getRandomWord());
	      buffer.append(" ");
	      ++j;
	    }
	    return buffer.toString();
	  }

	  public static String getRandomEmail()
	  {
	    return getRandomWord() + "@" + getRandomWord() + ".com";
	  }

	  public static Date getRandomDate()
	  {
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(5, 365 - random.nextInt(730));
	    return calendar.getTime();
	  }
}
