
CREATE TABLE IF NOT EXISTS user_details(
        id BIGINT        NOT NULL AUTO_INCREMENT,
        created_by varchar(255)  not null,
        created_on timestamp   not null,
        last_modified_by varchar(255) ,
        last_modified_on timestamp ,
        email varchar(100)  not null,
        first_name varchar(60)  not null,
        last_name varchar(50)  not null,
        contact varchar(100) not null,
        password varchar(100),
        city varchar(100),
        country varchar(100),
        street varchar(100),
        zip varchar(100),
        user_type varchar(20),
        status varchar(20),
        CONSTRAINT user_details_pkey PRIMARY KEY (id),
        CONSTRAINT user_details_email UNIQUE(email)
) engine=InnoDB;


CREATE TABLE IF NOT EXISTS branch_details(
        id BIGINT        NOT NULL AUTO_INCREMENT,
        created_by varchar(255)  not null,
        created_on timestamp   not null,
        last_modified_by varchar(255) ,
        last_modified_on timestamp ,
        branch_code varchar(100)  not null,
        city varchar(100),
        country varchar(100),
        street varchar(100),
        zip varchar(100),
        CONSTRAINT branch_details_pkey PRIMARY KEY (id),
        CONSTRAINT branch_details_branch_code UNIQUE(branch_code)
)engine=InnoDB;

CREATE TABLE IF NOT EXISTS bank_details(
        id BIGINT        NOT NULL AUTO_INCREMENT,
        created_by varchar(255)  not null,
        created_on timestamp   not null,
        last_modified_by varchar(255) ,
        last_modified_on timestamp ,
        account_number varchar(255),
        branch_code varchar(100)  not null,
        user_fk bigint not null,
        balance numeric(19,2),
        account_status varchar(100),
        CONSTRAINT bank_details_pkey PRIMARY KEY (id),
        CONSTRAINT bank_details_account_number UNIQUE(account_number),
        CONSTRAINT bank_details_branch FOREIGN KEY (branch_code)
            REFERENCES branch_details (branch_code) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
        CONSTRAINT bank_details_user_fk FOREIGN KEY (user_fk)
             REFERENCES user_details (id) MATCH SIMPLE
             ON UPDATE NO ACTION
             ON DELETE NO ACTION
)engine=InnoDB;


CREATE TABLE IF NOT EXISTS bank_transaction(
        id BIGINT        NOT NULL AUTO_INCREMENT,
        created_by varchar(255)  not null,
        created_on timestamp   not null,
        last_modified_by varchar(255) ,
        last_modified_on timestamp ,
        account_number varchar(255) not null,
        amount numeric(19,2) not null,
        reversal_id bigint,
        to_account varchar(255),
        transaction_type varchar(20),
        CONSTRAINT bank_transaction_pkey PRIMARY KEY (id)
)engine=InnoDB;