package best.lang.cryptorates.crypto.viewstate

data class ErrorInfo (val msgId: Int,
                     val retryRunnable: Runnable)