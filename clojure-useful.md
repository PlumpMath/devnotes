;# Useful Clojure Functions and Macros

;## Math


(defn lcm [& args]
    (let [gcd (fn [a b] (if (zero? b) a
                          (recur b (mod a b))))
          lcm (fn [a b] (/ (* a b) (gcd a b)))]
      (reduce lcm args)))



(defn gdc [a b]
 (if (zero? b) 
   a 
 (recur b (mod a b))))
