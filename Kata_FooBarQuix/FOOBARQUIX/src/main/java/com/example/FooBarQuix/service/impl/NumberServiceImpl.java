package com.example.FooBarQuix.service.impl;

import com.example.FooBarQuix.service.NumberService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NumberServiceImpl implements NumberService {

    @Override
    public String transformNumber(int number) {
        String numberStr = String.valueOf(number);

        //
        String result = Stream.of(
                        number % 3 == 0 ? "FOO" : "",  // Divisibilité par 3
                        number % 5 == 0 ? "BAR" : "",  // Divisibilité par 5
                        numberStr.chars().filter(c -> c == '3').mapToObj(c -> "FOO").collect(Collectors.joining()),// appliquer "FOO" pour chaque occurrence de "3"
                        numberStr.chars().filter(c -> c == '5').mapToObj(c -> "BAR").collect(Collectors.joining()),// appliquer "BAR" pour chaque occurrence de "5"
                        numberStr.contains("7") ? "QUIX" : "" // Contient 7
                )
                .filter(s -> !s.isEmpty()) // Filtrer les chaînes vides
                .collect(Collectors.joining());
                .collect(Collectors.joining());


        return result.isEmpty() ? numberStr : result;
    }

}
