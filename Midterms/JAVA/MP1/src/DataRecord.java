import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DataRecord {

    private String id;
    private String product;
    private String date;
    private String sales;

    public DataRecord(String id, String product, String date, String sales) {
        this.id = id;
        this.product = product;
        this.date = date;
        this.sales = sales;
    }

    public boolean hasMissingValues() {
        return id.isEmpty() || product.isEmpty() || date.isEmpty() || sales.isEmpty();
    }

    public boolean hasNegativeSales() {
        try {
            double value = Double.parseDouble(sales);
            return value < 0;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public boolean isValidDate() {
        try {
            LocalDate.parse(date); // expects YYYY-MM-DD
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String generateUniqueKey() {
        return id + "-" + product + "-" + date + "-" + sales;
    }
}