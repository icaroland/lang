import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.misc.ParseCancellationException
import java.io.File

fun getClassName(icaroOrClassFilePath: String) = File(icaroOrClassFilePath.substringAfterLast('/')).nameWithoutExtension

fun getClassFilePath(icaroFilePath: String) = icaroFilePath.replace(".icaro", ".class")

@Throws(ParseCancellationException::class)
fun generateParseTree(icaroFilePath: String): IcaroParser.IcaroFileContext {
    val lexer = IcaroLexer(CharStreams.fromFileName(icaroFilePath))
    val parser = IcaroParser(CommonTokenStream(lexer))

    lexer.removeErrorListeners()
    lexer.addErrorListener(IcaroExceptionThrower())

    parser.removeErrorListeners()
    parser.addErrorListener(IcaroExceptionThrower())

    return parser.icaroFile()
}

@Throws(IllegalStateException::class)
fun generateBytecode(icaroFilePath: String): ByteArray {
    val parseTree = generateParseTree(icaroFilePath)
    val className = getClassName(icaroFilePath)

    return IcaroCompiler().generateBytecode(parseTree, className)
}

fun runBytecode(className: String, bytecode: ByteArray) {
    val generatedClass = IcaroClassLoader().defineClass(className, bytecode)
    
    generatedClass.getMethod("main", Array<String>::class.java).invoke(null, arrayOf<String>())
}

fun runClassFile(classFilePath: String) {
    val bytecode = File(classFilePath).readBytes()
    val className = getClassName(classFilePath)
    
    runBytecode(className, bytecode)
}

fun createClassFile(icaroFilePath: String) {
    val bytecode = generateBytecode(icaroFilePath)
    val classFilePath = getClassFilePath(icaroFilePath)
    
    File(classFilePath).writeBytes(bytecode)
}

fun runIcaroFileWithoutGeneratingClassFile(icaroFilePath: String) {
    val bytecode = generateBytecode(icaroFilePath)
    val className = getClassName(icaroFilePath)
    
    runBytecode(className, bytecode)
}

fun runIcaroFileGeneratingClassFile(icaroFilePath: String) {
    val bytecode = generateBytecode(icaroFilePath)
    val classFilePath = getClassFilePath(icaroFilePath)
    
    File(classFilePath).writeBytes(bytecode)

    runBytecode(getClassName(icaroFilePath), bytecode)
}

fun main(args: Array<String>) {
    try {
        when (args[0]) {
            "-compile" -> createClassFile(args[1])
            "-run" -> runClassFile(args[1])
            "-compile-run" -> runIcaroFileGeneratingClassFile(args[1])
            "-compile-run-noclass" -> runIcaroFileWithoutGeneratingClassFile(args[1])
            else -> {
                println("available commands:")
                println("-compile: given the .icaro program, it creates the .class file")
                println("-run: executes the given the .class file")
                println("-compile-run: -compile and then -run")
                println("-compile-run-noclass: generate and executes the bytecode without creating any .class files")
                println("<every other commands>: print the available command list")
            }
        }
    } catch (icaroCompilationError: Throwable) {
        icaroCompilationError.printStackTrace()
    }
}