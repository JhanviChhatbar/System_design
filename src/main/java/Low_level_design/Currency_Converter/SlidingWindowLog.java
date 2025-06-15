package Low_level_design.Currency_Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLog {

    private final int limit;        // Max allowed requests per user per window.
    private final long windowSizeMs;      // Size of the sliding window (e.g., 60,000 ms for 1 minute).
    private final Map<String, List<Long>> requestLog;   /* Key: user ID or IP
                                                            Value: List of request timestamps
                                                            Data structure used: ConcurrentHashMap to make per-user log thread-safe.*/


    public SlidingWindowLog(int limit, long windowSizeMs){
        this.limit = limit;
        this.windowSizeMs = windowSizeMs;
        this.requestLog = new ConcurrentHashMap<>();
    }

    public synchronized boolean allowRequest(String userId){
        long now = System.currentTimeMillis();

        List<Long> userRequests = requestLog.computeIfAbsent(userId, k -> new ArrayList<>());
        /*
            What does computeIfAbsent do?
            If the key (userId) is present in the map, it:
            - Returns the existing value (i.e., the List<Long> already stored for that user).
            If the key is not present, it:
            - Creates a new ArrayList<Long> (by calling the lambda k -> new ArrayList<>()).
            - Inserts it into the map (requestLog.put(userId, newList)).
            - Returns a reference to that new list.
         */

        long cutoff = now - windowSizeMs;
        userRequests.removeIf(timeStamp -> timeStamp <= cutoff);    // Remove any request timestamps that are older than the current window.

        if(userRequests.size() < limit){
            userRequests.add(now);
            return true;
        }
        return false;
    }

    public void cleanUp(){
        long now = System.currentTimeMillis();
        long cutOff = now - windowSizeMs;

        requestLog.entrySet().removeIf(entry -> {
            entry.getValue().removeIf(timeStamp -> timeStamp <= cutOff);
            return entry.getValue().isEmpty();
        });

        /*
        For each user:
        Remove old request timestamps.
        If a user has no remaining requests, remove them from the map.
         */
    }

}
