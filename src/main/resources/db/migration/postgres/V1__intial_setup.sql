CREATE SEQUENCE IF NOT EXISTS hibernate_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


CREATE TABLE IF NOT EXISTS user_details(
        id BIGSERIAL,
        created_by character varying(255)  not null,
        created_on timestamp without time zone  not null,
        last_modified_by character varying(255) ,
        last_modified_on timestamp without time zone,
        email character varying(100)  not null,
        first_name character varying(60)  not null,
        last_name character varying(50)  not null,
        contact character varying(100) not null,
        password character varying(100),
        city character varying(100),
        country character varying(100),
        street character varying(100),
        zip character varying(100),
        user_type character varying(20),
        status character varying(20),
        CONSTRAINT user_details_pkey PRIMARY KEY (id),
        CONSTRAINT user_details_email UNIQUE(email)
);


CREATE TABLE IF NOT EXISTS branch_details(
        id BIGSERIAL,
        created_by character varying(255)  not null,
        created_on timestamp without time zone  not null,
        last_modified_by character varying(255) ,
        last_modified_on timestamp without time zone,
        branch_code character varying(100)  not null,
        city character varying(100),
        country character varying(100),
        street character varying(100),
        zip character varying(100),
        CONSTRAINT branch_details_pkey PRIMARY KEY (id),
        CONSTRAINT branch_details_branch_code UNIQUE(branch_code)
);

CREATE TABLE IF NOT EXISTS bank_details(
        id BIGSERIAL,
        created_by character varying(255)  not null,
        created_on timestamp without time zone  not null,
        last_modified_by character varying(255) ,
        last_modified_on timestamp without time zone,
        account_number character varying(255),
        branch_code character varying(100)  not null,
        user_fk bigint not null,
        balance numeric(19,2),
        account_status character varying(100),
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
);


CREATE TABLE IF NOT EXISTS bank_transaction(
        id BIGSERIAL,
        created_by character varying(255)  not null,
        created_on timestamp without time zone  not null,
        last_modified_by character varying(255) ,
        last_modified_on timestamp without time zone,
        account_number character varying(255) not null,
        amount numeric(19,2) not null,
        reversal_id bigint,
        to_account character varying(255),
        transaction_type character varying(20),
        CONSTRAINT bank_transaction_pkey PRIMARY KEY (id)
);