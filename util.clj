;; http://adereth.github.io/blog/2014/04/09/3d-printing-with-clojure/
(ns util.core (:use [scad-clj.scad])
    (:use [scad-clj.model])
    (:import java.lang.Runtime))

(def *outfile* "res/temp.scad")
(def *model* (cube 100 100 100))

(defn w "write to file"
  ([]
   (w *model* *outfile*))
  ([thing]
   (w thing *outfile*))
  ([thing fname]
   (spit fname (write-scad thing))))

(defn o "run openscad with outfile"
  ([] (o *outfile*))
  ([fname]
   (. (Runtime/getRuntime) exec (str "openscad " fname))))
