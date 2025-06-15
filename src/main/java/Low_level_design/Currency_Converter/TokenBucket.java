package Low_level_design.Currency_Converter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket {

    private final int capacity;
    private final double refillRate;        //tokens per second
    private final AtomicInteger tokens;
    private final AtomicLong lastRefillTimeStamp;

    public TokenBucket(int capacity, double refillRate){
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = new AtomicInteger(capacity);
        this.lastRefillTimeStamp = new AtomicLong(System.currentTimeMillis());
    }

    public synchronized boolean allowRequest(){
        refill();
        if(tokens.get() > 0){
            tokens.decrementAndGet();
            return true;
        }
        return false;
    }

    /*
    Check how much time is passed since last token update, and get timepassed
    get tokens to be added based on refill rate and timepassed
    check which is lesser , capacity or tokens to be added
    set new tokes and new last time token update

     */
    public void refill(){
        long now = System.currentTimeMillis();
        long lastRefill = lastRefillTimeStamp.get();

        if(now > lastRefill){
            double secondsPassed = (now - lastRefill)/1000;
            int tokensToAdd = (int) (secondsPassed * refillRate);

            if(tokensToAdd > 0){
                int newTokens = Math.min(capacity, tokens.get() + tokensToAdd);
                tokens.set(newTokens);
                lastRefillTimeStamp.set(now);
            }
        }


    }
}
