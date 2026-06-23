CREATE TABLE offers(

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    customer_id BIGINT NOT NULL,

    lender VARCHAR(100) NOT NULL,

    amount DECIMAL(12,2) NOT NULL,

    interest_rate DECIMAL(5,2) NOT NULL,

    tenure_months INT NOT NULL,

    min_score_required INT NOT NULL,

    status VARCHAR(20) NOT NULL,

    CONSTRAINT fk_offer_customer
    FOREIGN KEY(customer_id)
    REFERENCES customers(id)
);