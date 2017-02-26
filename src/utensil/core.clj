(ns utensil.core
  (:require
   [clojure.java.io :as io])
  (:import
   [java.io File]))


(def all-files #(file-seq (io/file %)))


(defn files-with
  "Returns a seq of files that have content that match the
   regex provided.

   Example:
   `(files-with \"/resources/templates/\" #\"application/js\")`"
  [path re]
  (when-let [fs (all-files path)]
    (for [f fs
          :when (.isFile f)
          :let [contents (slurp f)]
          :when (re-find re contents)]
      f)))


(defn files-of
  "Returns a seq of files that match the given pred.
   Predicate must take file as the first argument.

   Example:
   `(files-of \"/resources/public/css/\" #(re-find #\".scss\" (.getName %))`"
  [path pred]
  (when-let [fs (all-files path)]
    (for [f fs
          :when (pred f)]
      f)))


(defn print->
  "Variant of `print` that returns the args after printing."
  [& args]
  (prn args)
  args)


(defn pprint->
  "Variant of `clojure.pprint/pprint` that returns the args
   after pprinting."
  [& args]
  (clojure.pprint/pprint args)
  args)
