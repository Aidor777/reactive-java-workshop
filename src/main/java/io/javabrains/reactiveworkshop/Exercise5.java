package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux() and ReactiveSources.userFlux()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFlux().subscribe(
                number -> System.out.println("Number " + number),
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("Complete!")
        );

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.userFlux().subscribe(new MySubscriber<>());

        System.out.println("Press a key to end");
        System.in.read();
    }

    // When extending BaseSubscriber, we need to tell the source that we are ready to receive n items with request(n).
    // As the next items do not arrive "automatically", this is a good way to handle back-pressure.
    private static class MySubscriber<T> extends BaseSubscriber<T> {

        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            System.out.println("Subscribed!");
            request(1);
        }

        @Override
        protected void hookOnNext(T value) {
            System.out.println("Received: " + value);
            request(1);
        }
    }

}