import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

fun main() {
    val parser = IcaroParser(CommonTokenStream(IcaroLexer(ANTLRInputStream(System.`in`))))

    val tree: ParseTree = parser.init()

    println(tree.toStringTree(parser))
}