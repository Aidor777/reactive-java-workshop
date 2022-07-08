package io.javabrains.reactiveworkshop;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        System.out.println("Printing all numbers in the intNumbersStream stream");
        StreamSources.intNumbersStream().forEach(System.out::println);

        // Print numbers from intNumbersStream that are less than 5
        System.out.println("Printing numbers from intNumbersStream that are less than 5");
        StreamSources.intNumbersStream().filter(number -> number < 5).forEach(System.out::println);

        // Print the second and third numbers in intNumbersStream that are greater than 5
        System.out.println("Printing the second and third numbers in intNumbersStream that are greater than 5");
        StreamSources.intNumbersStream().filter(number -> number > 5).skip(1L).limit(2L)
                .forEach(System.out::println);

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        System.out.println("Printing the first number in intNumbersStream that's greater than 5");
        var numberToPrint = StreamSources.intNumbersStream().filter(number -> number > 5).findFirst().orElse(-1);
        System.out.println(numberToPrint);

        // Print first names of all users in userStream
        System.out.println("Printing first names of all users in userStream");
        StreamSources.userStream().map(User::getFirstName).forEach(System.out::println);

        // Print first names in userStream for users that have IDs from number stream
        System.out.println("Printing first names in userStream for users that have IDs from number stream");
        /* var possibleIDs = StreamSources.intNumbersStream().collect(Collectors.toSet());
        StreamSources.userStream().filter(user -> possibleIDs.contains(user.getId())).map(User::getFirstName)
                .forEach(System.out::println);*/
        /*StreamSources.userStream()
                .filter(user -> StreamSources.intNumbersStream().anyMatch(number -> number == user.getId()))
                .map(User::getFirstName).forEach(System.out::println);*/
        StreamSources.intNumbersStream()
                .flatMap(number -> StreamSources.userStream().filter(user -> user.getId() == number))
                .map(User::getFirstName).forEach(System.out::println);

    }

}
