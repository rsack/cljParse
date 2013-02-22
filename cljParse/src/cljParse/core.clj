(ns cljParse.core)

;; TODO:
;;  CHECK:  Need to find a way to wrap the long reg-exs
;;  DECIDE:	Check if "if re-find " or cond and true/false

;; Notes:
;;		Careful of spaces in optional regex

(defn test-for-re [ re-str str-str ] 
  (if (re-find (re-pattern re-str) str-str)
   true
   false))

(defn end-semicolon? [c]
  (test-for-re "(?i)^.+;" c))

(defn java-var? [c]
  (test-for-re "^(byte|short|int|long|float|double|boolean|String) .+(=.*)?;" c))

(defn perl-var? [c]
  (test-for-re "^(my|our) .+(=.*)?;" c))

(defn valid-html? [c]
  (test-for-re "(?i)^<html>.*<body>.*</body>.*</html>" c))
;; Another way for the SQL:
(def sqlpatterns [#"(?i)^select .* from .*( where .*)?( group by .*)?( having .*)?( order by .*)?"
                  #"(?i)^delete from .*( where .*)?( limit .*)?"])

(defn sql? [token]
  (when (some #(re-find % token) sqlpatterns)
    true))

(defn parse [f parsestr]
  (f parsestr))

(comment
  (parse type-of-parse-function "string to parse") )
