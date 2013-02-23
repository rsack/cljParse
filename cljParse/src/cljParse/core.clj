(ns cljParse.core)

;;;; Description: ADD!

;; TODO:
;;  CHECK:  Need to find a way to wrap the long reg-exs
;;  DECIDE:	Check if "if re-find " or cond and true/false

;; Notes:
;;		Careful of spaces in optional regex


; test-for-re- Tests the 2nd arg for a match in the 1st arg (reg ex pattern)
(defn test-for-re [ re-str str-str ] 
  (if (re-find (re-pattern re-str) str-str)
   true
   false))


; end-semicolon? - Tests for a line ending with a semicolon
(defn end-semicolon? [c]
  (test-for-re "(?i)^.+;$" c))


; java-var? - Tests for a valid java variable decleration (including basic built-in types)
(defn java-var? [c]
  (test-for-re "^(byte|short|int|long|float|double|boolean|String) .+(=.*)?;" c))


; perl-var? - Tests for a valid perl variable decleration (scope + line syntax)
(defn perl-var? [c]
  (test-for-re "^(my|our) (\\$|@|%).+(=.*)?;" c))


; valid-html? - Tests for validity of a basic html structure 
(defn valid-html? [c]
  (test-for-re "(?is)^<html>(<head>.*(<title>.*</title>)?.*</head>)?<body>.*</body>.*</html>" c))


; check-php? - Tests for PHP validity (short tag/long tag open+close)
(defn check-php? [c]
  (test-for-re "<\\?(php)?\\s.*\\s\\?>" c))


; sqlpatterns - Holds our supported SQL syntax reg-exs
(def sqlpatterns [#"(?i)^select .* from .*( where .*)?( group by .*)?( having .*)?( order by .*)?"
                  #"(?i)^delete from .*( where .*)?( limit .*)?"])


; sql? - Tests a string for a match in one of the sqlpatterns
(defn sql? [token]
  (when (some #(re-find % token) sqlpatterns)
    true))


; parse - Runs the function f on the string parsestr
(defn parse [f parsestr]
  (f parsestr))


; Example :)
(comment
  (parse type-of-parse-function "string to parse") )
