((scala-mode
  . ((eval
      . (defun my/metals-debug-chapter ()
          "Start a Metals DAP debug session for the current buffer's chapter."
          (interactive)
          (let* ((file    (buffer-file-name))
                 (chapter (when (and file
                                     (string-match ".*/\\(chapter-[0-9]+\\)/.*" file))
                            (match-string 1 file))))
            (if (not chapter)
                (message "Not in a chapter directory")
              (lsp-request-async
               "workspace/executeCommand"
               '(:command "metals.list-build-targets")
               (lambda (targets)
                 (let* ((test-id (seq-find (lambda (id)
                                             (and (string-prefix-p chapter id)
                                                  (string-suffix-p "-test" id)))
                                           targets))
                        (uri     (format "file:%s/%s/.scala-build/?id=%s"
                                         (lsp-workspace-root) chapter test-id)))
                   (if (not test-id)
                       (message "No test target found for %s" chapter)
                     (lsp-request-async
                      "workspace/executeCommand"
                      (list :command "debug-adapter-start"
                            :arguments (vector
                                        (list :targets  (vector (list :uri uri))
                                              :dataKind "scala-test-suites"
                                              :data     (vector))))
                      (lambda (result)
                        (when result
                          (let* ((dap-uri (plist-get result :uri))
                                 (_       (string-match ":\\([0-9]+\\)$" dap-uri))
                                 (port    (string-to-number (match-string 1 dap-uri))))
                            (dape (list 'host "127.0.0.1"
                                        'port port
                                        :request "attach")))))
                      :error-handler (lambda (err) (message "metals-debug: %S" err))
                      :no-merge t))))
               :error-handler (lambda (err) (message "Error listing targets: %S" err)))))))))
