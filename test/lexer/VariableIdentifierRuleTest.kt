package lexer

import generateParseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class VariableIdentifierRuleTest { 
    @Test
    fun shouldNotRaiseExceptionBecauseCamelCaseName() {
        assertDoesNotThrow {
            generateParseTree("test/testfiles/camelCaseVariableIdentifier.icaro")
        }
    }
    @Test
    fun shouldRaiseExceptionBecausePascalCaseName() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/PascalCaseVariableIdentifier.icaro")
        }
    }
    @Test
    fun shouldRaiseExceptionBecauseSnakeCaseName() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/SnakeCaseVariableIdentifier.icaro")
        }
    }
    @Test
    fun shouldRaiseExceptionBecauseKebabCaseName() {
        assertThrows<ParseCancellationException> {
            generateParseTree("test/testfiles/KebabCaseVariableIdentifier.icaro")
        }
    }
}