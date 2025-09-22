CREATE TABLE tb_user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      role VARCHAR(50) NOT NULL
);
V1 :
CREATE TABLE tb_course (
                        name VARCHAR(255) NOT NULL,
                        code VARCHAR(50) NOT NULL UNIQUE PRIMARY KEY,
                        instructor_id BIGINT NOT NULL,
                        description TEXT,
                        status VARCHAR(20) NOT NULL,
                        inactive_at DATETIME NULL,
                        CONSTRAINT fk_course_instructor FOREIGN KEY (instructor_id) REFERENCES
                            user(id)
);
CREATE TABLE tb_registration (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT NOT NULL,
                              course_id BIGINT NOT NULL,
                              enrolled_at DATETIME NOT NULL,
                              CONSTRAINT fk_registration_user FOREIGN KEY (user_id) REFERENCES user(id),
                              CONSTRAINT fk_registration_course FOREIGN KEY (course_id) REFERENCES
                                  course(id),
                              CONSTRAINT uq_registration_user_course UNIQUE (user_id, course_id)
)