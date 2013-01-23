(ns cljParse.core)

;; TODO:
;;   	add something like parse( sqlparse( string ) )
;;		check if "if re-find " or cond and true/false


(defn chkIt? [c]
  ( re-find #"(?i)(^select .* from .*)" c))

(defn test-sql [token]
    (cond
      (chkIt? token) true
      :else false))

(defn parse [f sql-str]
  (f sql-str))

(comment
  (parse type-of-parse-function "s") )
