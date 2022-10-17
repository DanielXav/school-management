INSERT INTO tb_student (email, name, registration) VALUES ('email@gmail.com', 'Daniel', '201080303');
INSERT INTO tb_student (email, name, registration) VALUES ('email2@gmail.com', 'Lucas', '201090803');

INSERT INTO tb_teacher (email, graduation, name, registration) VALUES ('email@gmail.com', 'Computação', 'Ramon', '201080904');

INSERT INTO tb_subject (name, room, teacher_id) VALUES ('Web Noturno', '203B', 1);

INSERT INTO tb_subject_has_student (subject_id, student_id) VALUES (1, 1)
INSERT INTO tb_subject_has_student (subject_id, student_id) VALUES (1, 2)


INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Lucas', 'Lucena', 'alex@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (first_name, last_name, email, password) VALUES ('Daniel', 'Xavier', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_USER');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);