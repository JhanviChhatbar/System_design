package Low_level_design.Currency_Converter;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CurrencyConverterwithoutRateLimiter {



        // Simulates a thread-safe cache for exchange rates (e.g., from a rate provider)
        private final ConcurrentHashMap<String, Double> exchangeRateCache = new ConcurrentHashMap<>();

        // Lock for updating rates safely
        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        // Initializes with some mock data
        public CurrencyConverterwithoutRateLimiter() {
            updateExchangeRate("USD", "EUR", 0.91);
            updateExchangeRate("EUR", "USD", 1.10);
            updateExchangeRate("USD", "INR", 83.2);
            updateExchangeRate("INR", "USD", 0.012);
        }

        public double convert(String fromCurrency, String toCurrency, double amount) {
            String key = fromCurrency + ":" + toCurrency;

            lock.readLock().lock();
            try {
                Double rate = exchangeRateCache.get(key);
                if (rate == null) {
                    throw new IllegalArgumentException("Exchange rate not available for: " + key);
                }
                return amount * rate;
            } finally {
                lock.readLock().unlock();
            }
        }

        public void updateExchangeRate(String fromCurrency, String toCurrency, double rate) {
            lock.writeLock().lock();
            try {
                String key = fromCurrency + ":" + toCurrency;
                exchangeRateCache.put(key, rate);
            } finally {
                lock.writeLock().unlock();
            }
        }

        // Periodic update simulation (could be replaced by scheduler or Kafka consumer)
        public void bulkUpdateRates(Map<String, Double> newRates) {
            lock.writeLock().lock();
            try {
                exchangeRateCache.putAll(newRates);
            } finally {
                lock.writeLock().unlock();
            }
        }

        public static void main(String[] args) {
            CurrencyConverterwithoutRateLimiter converter = new CurrencyConverterwithoutRateLimiter();

            System.out.println("100 USD to EUR: " + converter.convert("USD", "EUR", 100));
            System.out.println("100 EUR to USD: " + converter.convert("EUR", "USD", 100));
            System.out.println("100 USD to INR: " + converter.convert("USD", "INR", 100));
        }


}
