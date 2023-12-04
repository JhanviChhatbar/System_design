package Low_level_design.Volatile_Stocks;

public class MinMaxPrices {

    String company;
    Double minPrice;
    Double maxPrice;
    Double difference;

    public MinMaxPrices(String company, Double minPrice, Double maxPrice, Double difference) {
        this.company = company;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.difference = difference;
    }

    @Override
    public int hashCode(){
        return company.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        MinMaxPrices prices;
        if(obj instanceof MinMaxPrices){
            prices = (MinMaxPrices)obj;
        }else{
            return false;
        }
        return this.company.equals(prices.company);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }
}
