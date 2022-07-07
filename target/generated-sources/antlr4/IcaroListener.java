// Generated from Icaro.g4 by ANTLR 4.9.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IcaroParser}.
 */
public interface IcaroListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IcaroParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(IcaroParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link IcaroParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(IcaroParser.InitContext ctx);
	/**
	 * Enter a parse tree produced by {@link IcaroParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(IcaroParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link IcaroParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(IcaroParser.ValueContext ctx);
}