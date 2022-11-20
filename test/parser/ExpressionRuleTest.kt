package parser

import parseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ExpressionRuleTest {
    @Test
    fun shouldRaiseExceptionBecauseExpressionIsWhitespacedWrongly() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/expressionIsWhitespacedWrongly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheParenthesisAreWrittenCorrectly() {
        assertDoesNotThrow {
            parseTree("test/testfiles/parenthesisAreWrittenCorrectly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheParenthesisAreWrittenWrongly() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/parenthesisAreWrittenWrongly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheBasicOperationsExpressionAreWrittenCorrectly() {
        assertDoesNotThrow {
            parseTree("test/testfiles/basicOperationsExpression.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheExpressionWithMixedOperatorsIsWrittenCorrectly() {
        assertDoesNotThrow {
            parseTree("test/testfiles/expressionWithMixedOperators.icaro")
        }
    }
}