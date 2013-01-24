(ns cljParse.core)

;; TODO:
;;      Need to find a way to wrap the long reg-exs
;;  v-	Add something like parse( sqlparse( string ) )
;;	?	Check if "if re-find " or cond and true/false

;; Notes:
;;		Careful of spaces in optional regex
;;    Maybe split the query types into parts of the cond: (re-find) true \n (re-find) true ...

(defn checkendswithsemicolon? [c]
  (if (re-find #"(?i)^.+;" c)
    true
    false))

;;(defn checksql? [c]
;;  ( re-find #"(?i)(^select .* from .*( where .*)?( group by .*)?( having .*)?( order by .*)?)|(^delete from .*( where .*)?( limit .*)?)" c))
;;
;;(defn test-sql [token]
;;    (cond
;;      (checksql? token) true
;;      :else false))


(defn checksql? [token]
    (cond
      (re-find #"(?i)^select .* from .*( where .*)?( group by .*)?( having .*)?( order by .*)?" token) true
      (re-find #"(?i)^delete from .*( where .*)?( limit .*)?" token) true
     :else false))

(defn parse [f parsestr]
  (f parsestr))

(comment
  (parse type-of-parse-function "string to parse") )
