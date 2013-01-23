# cljParse

A simple Clojure parser to build upon

## Usage

Execute core.clj
Call the parse function with 2 arguments:
1. A function to parse your string/query (e.g. test-sql)
2. Your string/query.

Example:
(parse test-sql "SELECT this from Table")
