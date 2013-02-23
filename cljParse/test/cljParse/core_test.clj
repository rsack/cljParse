(ns cljParse.core-test
  (:use clojure.test
        cljParse.core))

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
