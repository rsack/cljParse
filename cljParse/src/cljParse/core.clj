(ns cljParse.core)

;; TODO:
;;   	add something like parse( sqlparse( string ) )
;;		check if "if re-find " or cond and true/false

;; Notes:
;;		Careful of spaces in optional regex

(defn checkbash? [c]
  (if (re-find #"(?i)^.+;" c)
    true
    false))


(defn chkIt? [c]
  ( re-find #"(?i)(^select .* from .*( group by .*)?)|(^delete from me)" c))

(defn test-sql [token]
    (cond
      (chkIt? token) true
      :else false))

(defn parse [f parsestr]
  (f parsestr))

(comment
  (parse type-of-parse-function "string to parse") )
