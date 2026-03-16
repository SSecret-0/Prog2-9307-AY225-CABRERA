class DataRecord {
    constructor(id, product, date, sales) {
        this.id = id;
        this.product = product;
        this.date = date;
        this.sales = sales;
    }

    hasMissingValues() {
        return !this.id || !this.product || !this.date || !this.sales;
    }

    hasNegativeSales() {
        const value = parseFloat(this.sales);
        return isNaN(value) || value < 0;
    }

    isValidDate() {
        const parsed = new Date(this.date);
        return !isNaN(parsed.getTime());
    }

    generateUniqueKey() {
        return `${this.id}-${this.product}-${this.date}-${this.sales}`;
    }
}

module.exports = DataRecord;