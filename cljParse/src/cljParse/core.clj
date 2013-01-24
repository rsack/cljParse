(ns cljParse.core)

;; TODO:
;;  CHECK:  Need to find a way to wrap the long reg-exs
;;  DECIDE:	Check if "if re-find " or cond and true/false

;; Notes:
;;		Careful of spaces in optional regex

(defn checkendswithsemicolon? [c]
  (if (re-find #"(?i)^.+;" c)
    true
    false))

(defn checkjavavardeclaration? [c]
  (if (re-find #"^(byte|short|int|long|float|double|boolean|String) .+(=.*)?;" c)
    true
    false))

(defn checksql? [token]
    (cond
      (re-find #"(?i)^select .* from .*( where .*)?( group by .*)?( having .*)?( order by .*)?" token) true
      (re-find #"(?i)^delete from .*( where .*)?( limit .*)?" token) true
     :else false))

(defn parse [f parsestr]
  (f parsestr))

(comment
  (parse type-of-parse-function "string to parse") )
