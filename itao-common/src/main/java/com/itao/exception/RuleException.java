package com.itao.exception;

/**规则异常
 * @author Created by Vicdor(linss) on 2016-05-20 01:04.
 */
public class RuleException extends RuntimeException {
    private static final long serialVersionUID = -3457810808179165934L;

    public RuleException() {
    }

    public RuleException(String message) {
        super(message);
    }

    public RuleException(String message, String value) {
        super(message);
    }

    public RuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuleException(Throwable cause) {
        super(cause);
    }
}