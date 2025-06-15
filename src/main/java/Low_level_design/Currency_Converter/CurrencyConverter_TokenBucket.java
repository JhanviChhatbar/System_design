package Low_level_design.Currency_Converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CurrencyConverter_TokenBucket {

    private final ConcurrentHashMap<String, Double> exchangeRate = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final int BUCKET_CAPACITY = 10;
    private final int REFILL_RATE = 5;
    private final Map<String, TokenBucket> tokenBuckets = new ConcurrentHashMap<>();

    public CurrencyConverter_TokenBucket(){
        updateExchangeRate("USD", "EUR", 0.91);
        updateExchangeRate("EUR", "USD", 1.10);
        updateExchangeRate("USD", "INR", 83.2);
        updateExchangeRate("INR", "USD", 0.012);
    }

    public double convert(String userId, String fromCurrency, String toCurrency, double amount){
        TokenBucket bucket = tokenBuckets.computeIfAbsent(userId,
                k -> new TokenBucket(BUCKET_CAPACITY, REFILL_RATE));

        if(!bucket.allowRequest()){
            throw new RuntimeException("Rate Limit exceeded! try again later!");
        }

        String key = fromCurrency + ":" + toCurrency;
        lock.readLock().lock();

        Double rate = exchangeRate.get(key);

        if(rate == null)
            throw new IllegalArgumentException("Exchange rate not available for " + key);

        return amount * rate;

    }

    public void updateExchangeRate(String fromCurrency, String toCurrency, double rate){
        lock.writeLock().lock();

        try {
            String key = fromCurrency + ":" + toCurrency;
            exchangeRate.put(key, rate);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public void bulkUpdateRates(Map<String, Double> newRates) {
        lock.writeLock().lock();
        try {
            exchangeRate.putAll(newRates);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CurrencyConverter_TokenBucket converter = new CurrencyConverter_TokenBucket();
        String user = "user123";

        for (int i = 1; i <= 15; i++) {
            try {
                double result = converter.convert(user, "USD", "EUR", 100);
                System.out.println(i + ": 100 USD to EUR = " + result);
            } catch (Exception e) {
                System.out.println(i + ": " + e.getMessage());
            }
            Thread.sleep(200); // simulate short delay between requests
        }
    }
}
