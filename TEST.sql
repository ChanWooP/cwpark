DROP TABLE menu;

DELETE FROM menu;

SELECT * FROM menu;

INSERT INTO menu(parent_num, depth, NAME) 
VALUES (NULL, 0, 'ROOT');
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (1, 1, '대1');
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (2, 2, '중1');
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (1, 1, '대2');
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (4, 2, '중2');

COMMIT;

SELECT A.ID, A.PARENT_NUM, A.DEPTH, A.NAME
  FROM menu A 
  LEFT OUTER JOIN menu B 
    ON A.PARENT_NUM = B.ID
ORDER BY A.DEPTH
;