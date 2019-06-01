/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.ast;

import net.sourceforge.pmd.lang.java.qname.JavaTypeQualifiedName;


/**
 * @deprecated Replaced with {@link ASTArrayAllocation} and {@link ASTConstructorCall}
 */
@Deprecated
public class ASTAllocationExpression extends AbstractJavaTypeNode implements JavaQualifiableNode {

    private JavaTypeQualifiedName qualifiedName;

    ASTAllocationExpression(int id) {
        super(id);
    }

    ASTAllocationExpression(JavaParser p, int id) {
        super(p, id);
    }

    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }


    /**
     * Returns true if this expression defines a body,
     * which is compiled to an anonymous class. If this
     * method returns false, then {@link #getQualifiedName()}
     * returns {@code null}.
     */
    public boolean isAnonymousClass() {
        if (jjtGetNumChildren() > 1) {
            // check the last child
            return jjtGetChild(jjtGetNumChildren() - 1) instanceof ASTClassOrInterfaceBody;
        }
        return false;
    }

    /**
     * Gets the qualified name of the anonymous class
     * declared by this node, or null if this node
     * doesn't declare any.
     *
     * @see #isAnonymousClass()
     */
    @Override
    public JavaTypeQualifiedName getQualifiedName() {
        return qualifiedName;
    }

}
