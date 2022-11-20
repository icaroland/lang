package parser

import generateParseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ExpressionRuleTest {
    @Test
    fun shouldRaiseExceptionBecauseExpressionIsWhitespacedWrongly() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/expressionIsWhitespacedWrongly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheParenthesisAreWrittenCorrectly() {
        assertDoesNotThrow {
            generateParseTree("test/testfiles/parenthesisAreWrittenCorrectly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheParenthesisAreWrittenWrongly() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/parenthesisAreWrittenWrongly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheBasicOperationsExpressionAreWrittenCorrectly() {
        assertDoesNotThrow {
            generateParseTree("test/testfiles/basicOperationsExpression.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheExpressionWithMixedOperatorsIsWrittenCorrectly() {
        assertDoesNotThrow {
            generateParseTree("test/testfiles/expressionWithMixedOperators.icaro")
        }
    }
}