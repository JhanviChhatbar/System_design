package Low_level_design.Volatile_Stocks;

import java.util.*;
import java.util.stream.Collectors;
/*
This design give highest volatile stocks from given list of stocks
 */

public class VolatileStocks {

    public static void main(String[] args) {

        List<StockPrice> stockPriceList = new ArrayList<>();
        stockPriceList.add(new StockPrice("TCS", 2000d));
        stockPriceList.add(new StockPrice("Infosys", 2345d));
        stockPriceList.add(new StockPrice("TCS", 2010d));
        stockPriceList.add(new StockPrice("Infosys", 345d));
        stockPriceList.add(new StockPrice("TCS", 3000d));
        stockPriceList.add(new StockPrice("Infosys", 234d));
        stockPriceList.add(new StockPrice("Paras", 2000d));
        stockPriceList.add(new StockPrice("Paras", 2345d));
        stockPriceList.add(new StockPrice("HUL", 2000d));
        stockPriceList.add(new StockPrice("HUL", 2300d));

        List<MinMaxPrices> minMaxPrices = findMostVolatileStocks(stockPriceList);
        System.out.println("Most volatile stocks ");
        for(int i=minMaxPrices.size()-1; i > minMaxPrices.size()-4; i--){
            System.out.println(minMaxPrices.get(i).getCompany() + " " + minMaxPrices.get(i).getDifference());
        }

    }

    public static List<MinMaxPrices> findMostVolatileStocks(List<StockPrice> stockPrices){
        Map<String,MinMaxPrices> priceMap = new HashMap<>();
        for(StockPrice stockPrice : stockPrices) {
            if (priceMap.containsKey(stockPrice.company)){
                MinMaxPrices minMaxPrices = priceMap.get(stockPrice.company);
                minMaxPrices.setMinPrice(Math.min(minMaxPrices.getMinPrice(), stockPrice.price));
                minMaxPrices.setMaxPrice(Math.max(minMaxPrices.getMaxPrice(), stockPrice.price));
                minMaxPrices.setDifference(minMaxPrices.getMaxPrice() - minMaxPrices.getMinPrice());
                priceMap.put(stockPrice.company, minMaxPrices);
            }else {
                priceMap.put(stockPrice.company,
                        new MinMaxPrices(stockPrice.company, stockPrice.price, stockPrice.price, 0d));
            }
        }

        Collection<MinMaxPrices> minMaxPrices =  priceMap.values();
        return minMaxPrices.stream().sorted(Comparator.comparing(minMaxPrices1 -> minMaxPrices1.getDifference())).collect(Collectors.toList());
    }
}
