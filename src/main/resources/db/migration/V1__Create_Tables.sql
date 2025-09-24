CREATE TABLE tb_users (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      role VARCHAR(20) NOT NULL
);
CREATE TABLE tb_courses (
                            code VARCHAR(10) NOT NULL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description TEXT,
                            instructor_id BIGINT NOT NULL,
                            status VARCHAR(20) NOT NULL,
                            inactivation_date DATETIME NULL,
                            creation_date DATETIME NOT NULL,
                            CONSTRAINT fk_course_instructor FOREIGN KEY (instructor_id) REFERENCES tb_users(id),
                            CONSTRAINT uq_course_code UNIQUE (code)
);
CREATE TABLE tb_registration (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 user_id BIGINT NOT NULL,
                                 course_code VARCHAR(10) NOT NULL,
                                 enrollment_date DATETIME NOT NULL,
                                 CONSTRAINT fk_registration_user FOREIGN KEY (user_id) REFERENCES tb_users(id),
                                 CONSTRAINT fk_registration_course FOREIGN KEY (course_code) REFERENCES tb_courses(code),
                                 CONSTRAINT uq_registration_user_course UNIQUE (user_id, course_code)
);