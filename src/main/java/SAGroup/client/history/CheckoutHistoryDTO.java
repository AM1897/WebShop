package SAGroup.client.history;

public class CheckoutHistoryDTO {

    private Long id;
    private String name;
    private String description;
    private String price;
    private int amount;
    private String user;

    // Standardkonstruktorer, getters och setters

    public CheckoutHistoryDTO() {
    }

    public CheckoutHistoryDTO(Long id, String user, String name, String description, String price, int amount) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // En toString()-metod kan vara användbar för att skriva ut objektinformation
    @Override
    public String toString() {
        return "CheckoutHistoryDTO{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", amount=" + amount +
                '}';
    }
}

