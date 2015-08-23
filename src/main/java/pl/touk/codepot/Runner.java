package pl.touk.codepot;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Runner {

    public static void main(String[] args) {

        HttpClient client = HttpClientBuilder.create()
                .setMaxConnTotal(10)
                .build();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        IntStream.range(0, 10).forEach(i ->
                scheduledExecutorService.scheduleWithFixedDelay(() ->
                        run(i, client), 1, 1, TimeUnit.SECONDS));

    }

    private static void run(int i, HttpClient client) {
        try {
            String path = i % 2 == 0 ? "foo" : "bar";
            HttpResponse response = client.execute(new HttpHost("localhost", 8083),
                    new HttpGet(String.format("http://localhost:8083/%s?name=%s&value=%s", path, "persion" + i, i * 100)));
            response.getEntity().getContent().close();
            System.out.println(response.getStatusLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
