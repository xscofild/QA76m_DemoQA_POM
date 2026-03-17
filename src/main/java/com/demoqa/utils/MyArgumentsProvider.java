package com.demoqa.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

// Поставщик тестовых данных для параметризованных тестов
// Реализует ArgumentsProvider — интерфейс JUnit 5 для передачи аргументов через @ArgumentsSource
//
// Преимущества перед @CsvSource и @CsvFileSource:
//  - данные можно генерировать динамически (читать из БД, API, файлов)
//  - можно передавать объекты любого типа, не только строки
//  - логика подготовки данных изолирована в отдельном классе
public class MyArgumentsProvider implements ArgumentsProvider {

    // Возвращает поток наборов аргументов
    // Каждый Arguments.of() = один запуск теста со своим набором данных
    // Порядок аргументов совпадает с параметрами тестового метода: (name, email, address)
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of("John", "john@mail.com", "Friedrichstr 12, Berlin"),
                Arguments.of("Anna", "anna@mail.com", "Unter den Linden 5, Berlin"),
                Arguments.of("Max",  "max@mail.com",  "Potsdamer Platz 1, Berlin")
        );
    }
}