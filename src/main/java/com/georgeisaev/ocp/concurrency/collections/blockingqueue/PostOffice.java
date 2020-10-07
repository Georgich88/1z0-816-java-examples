package com.georgeisaev.ocp.concurrency.collections.blockingqueue;

import com.github.javafaker.Faker;
import org.apache.log4j.Logger;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Illustrates the work of {@linkplain BlockingDeque} interface.
 *
 * @author Georgy Isaev
 * @version 1.0.0
 */
public class PostOffice {

    private static final int MIN_THREAD_NUMBER = 4;
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();
    private static final Logger logger = Logger.getLogger(PostOffice.class);
    private final BlockingDeque<Mail> mailsToSend;

    public PostOffice() {
        mailsToSend = new LinkedBlockingDeque<>();
    }

    public static void main(String[] args) throws InterruptedException {
        final int availableCores = Math.min(MIN_THREAD_NUMBER, getRuntime().availableProcessors());
        ExecutorService service = null;
        PostOffice postOffice = new PostOffice();
        try {
            service = (newFixedThreadPool(availableCores));
            ExecutorService finalService = service;
            IntStream.range(0, availableCores)
                    .forEach(i -> {
                        finalService.submit(() -> IntStream.range(0, RANDOM.nextInt(1_000))
                                .forEach(j -> postOffice.mailsToSend.offer(generateEmail()))
                        );
                        finalService.submit(() -> IntStream.range(0, RANDOM.nextInt(1_000))
                                .forEach(j -> Objects.requireNonNull(postOffice.mailsToSend.poll()).send()));
                    });
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    private static Mail generateEmail() {
        Mail mail = new EMail(FAKER.internet().emailAddress(), FAKER.internet().emailAddress(), FAKER.book().title());
        logger.info(mail);
        return mail;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostOffice{");
        sb.append("mailsToSend=").append(mailsToSend);
        sb.append('}');
        return sb.toString();
    }

}
