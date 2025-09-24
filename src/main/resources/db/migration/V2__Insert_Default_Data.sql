-- Inserindo usuários
INSERT INTO tb_users (name, email, role) VALUES
                                             ('Admin', 'admin@email.com', 1),
                                             ('Lucas Testoni', 'lucas@email.com',0),
                                             ('Maria Silva', 'maria@email.com', 2);

-- Inserindo cursos
INSERT INTO tb_courses (code, name, description, instructor_id, status, inactivation_date, creation_date) VALUES
                                                                                                              ('java-ob', 'Java Básico', 'Curso introdutório de Java', 2, 'ACTIVE', NULL, NOW()),
                                                                                                              ('spring-hib', 'Spring Boot', 'Curso de Spring Boot com JPA e Flyway', 2, 'ACTIVE', NULL, NOW());

-- Inserindo matrículas
INSERT INTO tb_registration (user_id, course_code, enrollment_date) VALUES
                                                                    (3, 'java-ob', NOW()),
                                                                    (3, 'spring-hib', NOW());