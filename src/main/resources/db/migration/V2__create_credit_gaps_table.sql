CREATE TABLE credit_gaps(

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    customer_id BIGINT NOT NULL,

    factor VARCHAR(100) NOT NULL,

    current_value VARCHAR(100),

    ideal_value VARCHAR(100),

    impact VARCHAR(20),

    estimated_score_gain INT,

    action_description TEXT,

    status VARCHAR(20),

    resolved_at TIMESTAMP NULL,

    CONSTRAINT fk_credit_gap_customer
    FOREIGN KEY(customer_id)
    REFERENCES customers(id)

);