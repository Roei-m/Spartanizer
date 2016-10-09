package il.org.spartan.spartanizer.research;

import org.eclipse.jdt.core.dom.*;

/** @author Ori Marcovitch
 * @since 2016 */
public class CommentsCleanerVisitor extends ASTVisitor {
  @Override public boolean visit(final Javadoc n) {
    n.delete();
    return true;
  }

  @Override public boolean visit(final LineComment ¢) {
    System.out.println("line");
    ¢.delete();
    return true;
  }

  @Override public boolean visit(final BlockComment ¢) {
    ¢.delete();
    return true;
  }
}