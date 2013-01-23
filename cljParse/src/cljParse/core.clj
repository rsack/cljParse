(ns cljParse.core)

;; add something like parse( sqlparse( string ) )

(defn chkIt? [c]
  ( re-find #"(?i)^select .* from .*" c))

(defn parse [sql-str]
  (chkIt? sql-str))
