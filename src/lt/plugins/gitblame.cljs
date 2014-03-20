(ns lt.plugins.gitblame
  (:require [lt.objs.editor :as editor]
            [lt.objs.editor.pool :as pool]
            [lt.objs.command :as cmd]
            [cljs.reader :as reader]
            [clojure.core.async :as async])

  (:require-macros [lt.macros :refer [behavior]]))

(def path-to-node-modules "/Users/madhu/Library/Application Support/LightTable/plugins/Git Blame/node_modules/")

(def platform (js/require (str path-to-node-modules "git-node-platform/index.js")))
(def js-git (js/require (str path-to-node-modules "js-git/js-git.js")))
(def fs-db ((js/require (str path-to-node-modules "git-fs-db/fs-db.js")) platform))

(def git-repo "/Users/madhu/src/hackerschool/cljs-test/.git/")
(defn db
  [repo-path]
  (fs-db repo-path))

(def repo (js-git (db git-repo)))
(:arglists (.load repo "8886786c4526b6f4c3f12aa65716eca38cc161a3"))

(def classy->plain
  {"behaviour" "behavior"})

(defn declassify-string [string]
  (reduce
   (fn [string [classy plain]]
     (.replace string classy plain))
   string
   classy->plain))

(defn declassify-behind-cursor [])

(cmd/command {:command :git-blame-test
              :desc "Git Blame: replace classy words"
              :exec declassify-behind-cursor})

