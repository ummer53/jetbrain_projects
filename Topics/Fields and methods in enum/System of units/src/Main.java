enum SI {

    M("length"),
    KG("mass"),
    S("time");

    public final String quantityName;

    SI(String quantityName) {
        this.quantityName = quantityName;
    }

    // implement getQuantityName() method here
    public String getQuantityName() {
        for( SI item : values()) {
            if (item.quantityName==quantityName) {
                return this.quantityName;
            }
        }
        return null;
    }

}