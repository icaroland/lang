package lexer.variableidentifier

import generateParseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class VariableIdentifierLexerRuleTest { 
    @Test
    fun shouldNotRaiseExceptionBecauseCamelCaseName() {
        assertDoesNotThrow {
            generateParseTree("src/test/kotlin/lexer/variableidentifier/testfiles/camelCaseVariableIdentifier.icaro")
        }
    }
    @Test
    fun shouldRaiseExceptionBecausePascalCaseName() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/lexer/variableidentifier/testfiles/PascalCaseVariableIdentifier.icaro")
        }
    }
    @Test
    fun shouldRaiseExceptionBecauseSnakeCaseName() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/lexer/variableidentifier/testfiles/SnakeCaseVariableIdentifier.icaro")
        }
    }
    @Test
    fun shouldRaiseExceptionBecauseKebabCaseName() {
        assertThrows<ParseCancellationException> {
            generateParseTree("src/test/kotlin/lexer/variableidentifier/testfiles/KebabCaseVariableIdentifier.icaro")
        }
    }
}