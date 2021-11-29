(ns ankerkeycap.core (:use [util.core]))

(def *outfile* "res/ankerkeycap.scad")

(w (let [w 15.5 h 17.6 d 1.7 thick 0.7 thick_2 (* 2 thick)
         whalf (/ w 2) hhalf (/ h 2)
         block (cube w h d)
         inner (cube (- w thick_2) (- h thick_2) 1)
         key   (difference block (translate [0 0 thick] inner))
         hole (translate [0.4 0.4 0] (cube 1 1 (/ d 1.2)))
         clip  (difference  (cube 1.3 1.5 d) hole)
         holelr (rotate (/ Math/PI 2) [0 0 1] hole)
         cliplr  (difference  (cube 1.3 1.5 d) holelr)
         dot   (cube 0.7 0.7 d)
         txt (rotate (/ Math/PI 1) [1 0 0] (extrude-linear {:height 0.5} (text "c")))
         ]
         
     (union key
            ;; larger clips
            (translate [-3.5  (- h (/ h 2) 3.7)  0] clip)
            (translate [ 3.5  (- h (/ h 2) 3.7)  0] cliplr)
            ;; dots
            ;; 4.6 from x boundary, 4-2.4 
            (translate [(- whalf 4.6)  (- 4.0 hhalf)  0] dot)
            (translate [(- whalf 4.6)  (- 1.6 hhalf)  0] dot)
            (translate [(- 4.6 whalf)  (- 4.0 hhalf)  0] dot)
            (translate [(- 4.6 whalf)  (- 1.6 hhalf)  0] dot)
            (translate [(* -0.5 whalf) (* 0.5 hhalf) (* -1 d 0.5)] txt))))
