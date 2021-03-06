(ns cljParse.core)

;;;; Description: 
;;;;    cljParse is used for validating several predefined syntaxes in several languages.
;;;;    If the validation is successful 'true' will be returned.
;;;;    Usage is via the parse function, to be called with 2 arguments:
;;;;      1. The relevant validation function.
;;;;      2. The string to be tested.


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
  (test-for-re "(?s)<\\?(php)?\\s.*\\s\\?>" c))


; sqlpatterns - Holds our supported SQL syntax reg-exs
(def sqlpatterns [#"(?is)^select .* from .*( where .*)?( group by .*)?( having .*)?( order by .*)?"
                  #"(?is)^update .* set (.+=.+)+( where .*)?"
                  #"(?is)^insert into .* values \((.+)+\)"
                  #"(?is)^delete from .*( where .*)?( limit .*)?"])


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
