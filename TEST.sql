DROP TABLE menu;

DELETE FROM menu;

SELECT * FROM menu;

INSERT INTO menu(num, parent_num, depth, NAME) 
VALUES (1, NULL, 0, 'ROOT');
INSERT INTO menu(num, parent_num, depth, NAME) 
VALUES (2, 1, 1, '대1');
INSERT INTO menu(num, parent_num, depth, NAME) 
VALUES (3, 2, 2, '중1');
INSERT INTO menu(num, parent_num, depth, NAME) 
VALUES (4, 1, 1, '대2');
INSERT INTO menu(num, parent_num, depth, NAME) 
VALUES (5, 4, 2, '중2');



COMMIT;