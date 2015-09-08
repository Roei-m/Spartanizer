package org.spartan.refactoring.wring;

import static org.spartan.refactoring.utils.Funcs.duplicate;
import static org.spartan.refactoring.utils.Funcs.parent;

import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Statement;
import org.spartan.refactoring.utils.Is;

/**
 * A {@link Wring} to convert <code>if {a) g();}</code> into
 * <code>if (a) g();</code>
 *
 * @author Yossi Gil
 * @since 2015-09-09
 */
public class BlockSingleton extends Wring.ReplaceCurrentNode<Block> {
  @Override Statement replacement(final Block b) {
    if (!Is.statement(parent(b)))
      return null;
    final List<Statement> ss = b.statements();
    if (ss.size() != 1)
      return null;
    final Statement $ = ss.get(0);
    return Is.blockEssential($) ? null : duplicate($);
  }
  @Override String description(@SuppressWarnings("unused") final Block _) {
    return "Remove redundant curly braces.";
  }
}