create table admin (
    id                int       not null auto_increment,
    cwl_name          char(50)  not null,
    first_name        char(100) not null,
    last_name         char(100) not null,
    middle_name       char(100),
    email             char(200) not null,
    updated_by_cwl    char(50)  not null,
    updated_timestamp timestamp DEFAULT CURRENT_TIMESTAMP not null,
    primary key(id),
    unique key (cwl_name)
)
engine=INNODB;

create table email_templates (
    id                int       not null auto_increment,
    template_name     char(50)  not null,
    template_text     text      not null,
    updated_by_cwl    char(50)  not null,
    updated_timestamp timestamp DEFAULT CURRENT_TIMESTAMP not null,
    primary key(id),
    unique key (template_name)
)
engine=INNODB;

create table claims (
    id                 int       not null auto_increment,
	ticket_number      char(10)  not null,
    claim_date         timestamp not null,
    claim_text         text      not null,
    extracted_ip       char(200),
    query_string       text,
    response           text,
    extracted_username char(255),
    extracted_email    char(255),
    primary key(id)
)
engine=INNODB;

create table action_type (
    id   int       not null auto_increment,
    type char(10),
    primary key(id),
    unique key (type)
)
engine=INNODB;

create table actions (
    id                  int       not null auto_increment,
    action_type_id      int       not null, 
    action_date         timestamp not null,
    sent_from           char(200) not null,
    sent_to             char(200) not null,
    email_template_id   int       not null,
    claim_id            int       not null,
    primary key(id),
    INDEX (action_type_id),    
    INDEX (email_template_id), 
    INDEX (claim_id),
    FOREIGN KEY (action_type_id)    REFERENCES action_type(id),
    FOREIGN KEY (email_template_id) REFERENCES email_templates(id),
    FOREIGN KEY (claim_id)          REFERENCES claims(id)
)
engine=INNODB;

create table parameters (
    id   int       not null auto_increment,
    name char(10),
    value varchar(2000),
    unique key (id)
)
engine=INNODB;

