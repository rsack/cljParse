(ns cljParse.core-test
  (:use clojure.test
        cljParse.core))


;; SQL Tests
; SELECT...
(deftest test-sql-select-good
  (is (= true
        (parse sql? "select * from table1")
     ) "SQL test, valid select query"
))

(deftest test-sql-select-no-from
  (is (not= true
        (parse sql? "select * table1")
     ) "SQL test, select without from."
))

; UPDATE...
(deftest test-sql-update-good
  (is (= true
        (parse sql? "update table1 set name='name1',surname='surname1' where id=5")
     ) "SQL test, valid update query"
))

(deftest test-sql-update-missing-set
  (is (not= true
        (parse sql? "update table1 name='name1',surname='surname1'")
     ) "SQL test, missing set in update"
))

; INSERT...
(deftest test-sql-insert-good
  (is (= true
        (parse sql? "insert into tbl values ('alpha', 2)")
     ) "SQL test, valid insert into query"
))

(deftest test-sql-insert-typo
  (is (not= true
        (parse sql? "insert into tbl value ('alpha', 2)")
     ) "SQL test, typo on insert into"
))

; DELETE...
(deftest test-sql-delete-good
  (is (= true
        (parse sql? "delete from table1 where name='name1' limit 5")
     ) "SQL test, valid delete from query"
))

(deftest test-sql-delete-missing-from
  (is (not= true
        (parse sql? "delete table1 where name='name1' limit 5")
     ) "SQL test, missing from in delete"
))


;; HTML Tests
(deftest test-html-good
  (is (= true
        (parse valid-html? "<html><head><title>A Page</title></head><body></body></html>")
     ) "HTML test, valid markup"
))

(deftest test-html-bad-head-close
  (is (not= true
        (parse valid-html? "<html><head><title>A Page</title><body></head></body></html>")
     ) "HTML test, badly placed closing of head tag."
))

(deftest test-html-no-closing
  (is (not= true
        (parse valid-html? "<html><head><body></body></head>")
     ) "HTML test, missing closing html tag."
))


;; Other function tests
; end-semicolon?
(deftest test-semic-good
  (is (= true
        (parse end-semicolon? "this is a line;")
     ) "end-semicolon test, valid"
))

(deftest test-semic-bad
  (is (not= true
        (parse end-semicolon? "this was a line")
     ) "end-semicolon test, invalid"
))

; Java var
(deftest test-javav-good
  (is (= true
        (parse java-var? "int x=1;")
     ) "Java var test, valid"
))

(deftest test-javav-bad
  (is (not= true
        (parse java-var? "Integer j;")
     ) "Java var test, invalid!"
))

; Perl var
(deftest test-perlv-good
  (is (= true
        (parse perl-var? "my @arrNums;")
     ) "Perl var test, valid array decleration"
))

(deftest test-perlv-bad
  (is (not= true
        (parse perl-var? "our x = 1;")
     ) "Perl var test, invalid, missing type!"
))

; PHP code validity
(deftest test-php-good-1
  (is (= true
        (parse check-php? "<?php echo \"hi\"; ?>")
     ) "PHP code test, valid code with long tags"
))

(deftest test-php-good-2
  (is (= true
        (parse check-php? "<? echo \"hi\"; ?>")
     ) "PHP code test, valid code with short tag"
))

(deftest test-php-bad-open-tag
  (is (not= true
        (parse check-php? "<?phcode; ?>")
     ) "PHP code test, bad opening tag!"
))

(deftest test-php-bad-missing-close
  (is (not= true
        (parse check-php? "<?php function(a);")
     ) "PHP code test, missing closing tag."
))
