DROP TABLE menu;

DELETE FROM menu;

SELECT * FROM menu;

INSERT INTO menu(parent_num, depth, NAME, path) 
VALUES (NULL, 0, '메뉴', null);
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (1, 1, '대1');
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (2, 2, '중1');
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (1, 1, '대2');
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (4, 2, '중2');
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (2, 2, '중3');
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (2, 2, '중4');
INSERT INTO menu(parent_num, depth, NAME) 
VALUES (4, 2, '중5');

COMMIT;

/* 공백은 스페이스바 아니고 문자사용 'ㅤ' */
SELECT A.id
	  , A.parent_num
	  , A.depth
	  , case A.depth when 1 then CONCAT('ㅤ└ ', A.name)
	         		  when 2 then CONCAT('ㅤㅤ└ ', A.name)
	         		  ELSE A.name END AS menu_name
	  , case A.depth when 1 then A.id
	  					  when 2 then A.parent_num
	  					  ELSE A.id END AS order_id
	  , A.path
	  , A.name
	  , (SELECT COUNT(1) FROM menu WHERE parent_num = A.id) AS childCnt
  FROM menu A 
  LEFT OUTER JOIN menu B 
    ON A.parent_num = B.id
ORDER BY order_id, A.id
;
