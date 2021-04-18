package com.JUC20200604.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zkw
 * @date 2021-04-10
 **/
public class LambdaTest {
    public static void main(String[] args) {

        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        },"哈哈哈线程").start();

        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> {
            System.out.println(n);
        });

        //输出结果 USA, JAPAN, FRANCE, GERMANY, ITALY, U.K., CANADA
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x ->x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);

        /**
         * USA, Japan, France, Germany, Italy, U.K., Canada
         */
        String G7Countries2 = G7.stream().collect(Collectors.joining(", "));
        System.out.println(G7Countries2);


        Optional<String> someNull = Optional.of(null);

    }
}
