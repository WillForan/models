;; http://adereth.github.io/blog/2014/04/09/3d-printing-with-clojure/
(ns clockholder.core (:use [scad-clj.scad])
    (:use [scad-clj.model]))
;; (in-ns 'clockholder.core)
(defonce outfile "clockholder.scad")
(def *model* (cube 100 100 100))

(defn w "write to file"
  ([]
   (w *model*))
  ([thing]
   (spit outfile (write-scad thing))))

(defn o "run openscad with outfile" []
  (import 'java.lang.Runtime)
  (. (Runtime/getRuntime) exec (str "openscad " outfile)))

(w (let [clock-h 220
         clock-w 40
         base-h 20
         base-w (- clock-w 5)
         base (cylinder [base-w (/ base-w 2)] base-h)
         clock (->> (cylinder clock-h clock-w)
                    (rotate (/ Math/PI 1) [1 0 1])
                    (translate [0 0 (- clock-h (/ base-h 2))]))]
     (difference base clock)))
