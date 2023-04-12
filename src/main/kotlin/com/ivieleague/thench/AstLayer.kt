package com.ivieleague.thench

data class TextPosition(val line: Int, val column: Int): Comparable<TextPosition> {
    override fun compareTo(other: TextPosition): Int = compareBy<TextPosition>({ it.line }, { it.column }).compare(this, other)
}
data class TextRange(override val start: TextPosition, override val endInclusive: TextPosition): ClosedRange<TextPosition>

sealed interface AstElement { val metadata: Map<String, Any?> }
sealed interface AstTopLevel: AstElement
sealed interface AstDeclaration: AstElement
sealed interface AstStatement: AstElement
sealed interface AstExpression: AstStatement
sealed interface AstParameter: AstElement
sealed interface AstArgument: AstElement

data class AstImport(
    val expression: AstExpression,
    override val metadata: Map<String, Any?> = mapOf()
): AstTopLevel
data class AstModule(
    val declarations: List<AstDeclaration>,
    override val metadata: Map<String, Any?> = mapOf()
): AstExpression
data class AstTypeDeclaration(
    val name: String,
    val parts: List<AstInParameter>,
    override val metadata: Map<String, Any?> = mapOf()
): AstDeclaration
data class AstInParameter(
    val name: String,
    val type: AstExpression,
    val vararg: Boolean = false,
    val infer: Boolean = false,
    val default: AstExpression? = null,
    override val metadata: Map<String, Any?> = mapOf()
): AstParameter
data class AstPlistParameterUse(
    val name: String,
    val vararg: Boolean = false,
    override val metadata: Map<String, Any?> = mapOf()
): AstParameter
data class AstOutParameter(
    val name: String,
    val type: AstExpression,
    val vararg: Boolean = false,
    val infer: Boolean = false,
    override val metadata: Map<String, Any?> = mapOf()
): AstParameter
data class AstInOutParameter(
    val name: String,
    val type: AstExpression,
    val vararg: Boolean = false,
    val infer: Boolean = false,
    override val metadata: Map<String, Any?> = mapOf()
): AstParameter
data class AstTypeParameter(
    val name: String,
//    val constraint:,
    override val metadata: Map<String, Any?> = mapOf()
): AstElement
data class AstPlistParameter(
    val name: String,
    override val metadata: Map<String, Any?> = mapOf()
): AstElement
data class AstFunctionDeclaration(
    val name: String,
    val plistParameters: List<AstPlistParameter>,
    val typeParameters: List<AstTypeParameter>,
    val parameters: List<AstParameter>,
    val returnType: AstExpression,
    val body: AstExpression,
    override val metadata: Map<String, Any?> = mapOf(),
): AstDeclaration
data class AstAnonymousFunction(
    val explicitParameters: List<AstParameter>? = null,
    val body: AstExpression,
    override val metadata: Map<String, Any?> = mapOf()
): AstExpression
data class AstBlock(
    val statements: List<AstStatement>,
    override val metadata: Map<String, Any?> = mapOf()
): AstExpression
data class AstConstant(
    val value: Any?,
    override val metadata: Map<String, Any?> = mapOf()
): AstExpression
data class AstCall(
    val name: String,
    val typeArguments: Map<String, AstExpression>,
    val arguments: List<AstArgument>,
    override val metadata: Map<String, Any?> = mapOf()
): AstElement
data class AstDynamicCall(
    val function: AstExpression,
    val typeArguments: Map<String, AstExpression>,
    val arguments: List<AstArgument>,
    override val metadata: Map<String, Any?> = mapOf()
): AstElement
data class AstOrderedArgument(
    val value: AstExpression,
    override val metadata: Map<String, Any?> = mapOf()
): AstArgument
data class AstNamedArgument(
    val name: String,
    val value: AstExpression,
    override val metadata: Map<String, Any?> = mapOf()
): AstArgument
data class AstReference(
    val name: String,
    val inputs: List<AstParameter>? = null,
    override val metadata: Map<String, Any?> = mapOf()
): AstExpression
data class AstAssignment(
    val name: String,
    val value: AstExpression,
    val declaration: AstValueDeclaration? = null,
    override val metadata: Map<String, Any?> = mapOf()
): AstStatement
enum class AstValueDeclaration { Val, Var }
data class AstFunctionType(
    val plistParameters: List<AstPlistParameter>,
    val typeParameters: List<AstTypeParameter>,
    val parameters: List<AstParameter>,
    val returnType: AstExpression,
)