((scala-mode
  . ((eval
      . (defun my/metals-debug-tests ()
          "Start a Metals DAP debug session for the current chapter's tests."
          (interactive)
          (lsp-request-async
           "workspace/executeCommand"
           (list :command "debug-adapter-start"
                 :arguments (vector
                             (list :targets
                                   (vector (list :uri "file:/home/eduardo/repos/reading-fpinscala/chapter-3/.scala-build/?id=chapter-3_b28d80041b-test"))
                                   :dataKind "scala-test-suites"
                                   :data (vector (list :className "TailSuite"
                                                       :tests (vector)
                                                       :exclude (vector))))))
           (lambda (result)
             (when result
               (let* ((uri  (plist-get result :uri))
                      (_    (string-match ":\\([0-9]+\\)$" uri))
                      (port (string-to-number (match-string 1 uri))))
                 (dape (list 'host "127.0.0.1"
                             'port port
                             :request "attach")))))
           :error-handler (lambda (err) (message "metals-debug: %S" err))
           :no-merge t))))))
