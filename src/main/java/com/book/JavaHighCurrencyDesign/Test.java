package com.book.JavaHighCurrencyDesign;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Created by niceyuanze on 17-5-7.
 */
public class Test {
    static class Person{
        int x;

        public Person(int x) {
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person(1);
        Person person2 = new Person(2);
        persons.add(person1);
        persons.add(person2);
        persons.stream()
                .map(x -> new Person(x.getX()+1))
                .forEach(x -> System.out.println(x.getX()));

        persons.stream().forEach(x -> System.out.println(x.getX()));

    }
}
