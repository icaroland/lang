package parser.expression

import generateParseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ExpressionParserRuleTest {
    @Test
    fun shouldRaiseExceptionBecauseExpressionIsWhitespacedWrongly() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/parser/expression/testfiles/expressionIsWhitespacedWrongly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheParenthesisAreWrittenCorrectly() {
        assertDoesNotThrow {
            generateParseTree("src/test/kotlin/parser/expression/testfiles/parenthesisAreWrittenCorrectly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheParenthesisAreWrittenWrongly() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/parser/expression/testfiles/parenthesisAreWrittenWrongly.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheBasicOperationsExpressionAreWrittenCorrectly() {
        assertDoesNotThrow {
            generateParseTree("src/test/kotlin/parser/expression/testfiles/basicOperationsExpression.icaro")
        }
    }

    @Test
    fun shouldNotRaiseExceptionBecauseTheExpressionWithMixedOperatorsIsWrittenCorrectly() {
        assertDoesNotThrow {
            generateParseTree("src/test/kotlin/parser/expression/testfiles/expressionWithMixedOperators.icaro")
        }
    }
}