package cci;

import java.util.*;

/*
 * Given a list of people with their birth and death years, implement a method
 * to compute the year with the most number of people alive. If a person was
 * alive during any portion of the year, they should be included in that year's count.
 * 
 * Time complexity: O(2*(N+logN))
 * Space complexity: 2*N*4+4*4 bytes, 2*N*4 (two heaps of int), 4*4 (max, maxYear, count, now)
 */
class LivingPeople {
  public int yearMaxAlive(PriorityQueue<Integer> birth, PriorityQueue<Integer> death) {
    int max = Integer.MIN_VALUE, maxYear = Integer.MIN_VALUE;
    int count = 0;
    Integer now = birth.poll();
    while (!death.isEmpty()) {
      if (null != now && now <= death.peek()) {
        count++;
        now = birth.poll();
      }
      else {
        if (max < count) {
          max = count;
          maxYear = death.peek();
        }
        count--;
        death.poll();
      }
    }

    System.out.printf("%d people alive in year %d.\n", max, maxYear);
    return maxYear;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int samples = sc.nextInt();
    PriorityQueue<Integer> birth = new PriorityQueue<Integer>();
    PriorityQueue<Integer> death = new PriorityQueue<Integer>();
    while (samples-- > 0) {
      birth.add(sc.nextInt());
      death.add(sc.nextInt());
    }
    sc.close();

    LivingPeople lp = new LivingPeople();
    System.out.println(lp.yearMaxAlive(birth, death));
  }
}

//5
//1959 1990
//1945 2010
//1960 1990
//1930 1960
//2015 2017

//3
//1940 1950
//1940 1950
//1940 1950

//1
//1910 1910

//4
//1910 1920
//1921 1930
//1931 1940
//1941 1950

//3
//1910 1920
//1921 1930
//1930 1940

//3
//1955 1990
//1965 1982
//1923 1982