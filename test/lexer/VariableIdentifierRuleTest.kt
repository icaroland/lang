package lexer

import parseTree
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class VariableIdentifierRuleTest {
    @Test
    fun shouldNotRaiseExceptionBecauseCamelCaseName() {
        assertDoesNotThrow {
            parseTree("test/testfiles/camelCaseVariableIdentifier.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecausePascalCaseName() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/PascalCaseVariableIdentifier.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseSnakeCaseName() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/SnakeCaseVariableIdentifier.icaro")
        }
    }

    @Test
    fun shouldRaiseExceptionBecauseKebabCaseName() {
        assertThrows<ParseCancellationException> {
            parseTree("test/testfiles/KebabCaseVariableIdentifier.icaro")
        }
    }
}