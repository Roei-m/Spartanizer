package il.org.spartan.spartanizer.research.patterns;

import java.util.*;

import org.eclipse.jdt.core.dom.*;

import il.org.spartan.spartanizer.engine.*;
import il.org.spartan.spartanizer.research.*;
import il.org.spartan.spartanizer.tipping.*;

/** Replace X != null ? X : Y with X ?? Y <br>
 * replace X == null ? Y : X with X ?? Y <br>
 * replace null == X ? Y : X with X ?? Y <br>
 * replace null != X ? X : Y with X ?? Y <br>
 * @author Ori Marcovitch
 * @year 2016 */
public final class DefaultsTo extends NanoPatternTipper<ConditionalExpression> {
  @SuppressWarnings("serial") List<UserDefinedTipper<ConditionalExpression>> tippers = new ArrayList<UserDefinedTipper<ConditionalExpression>>() {
    {
      add(TipperFactory.tipper("$X1 != null ? $X1 : $X2", "defaultsTo($X1, $X2)", ""));
      add(TipperFactory.tipper("$X1 == null ? $X2 : $X1", "defaultsTo($X1, $X2)", ""));
      add(TipperFactory.tipper("null != $X1 ? $X1 : $X2", "defaultsTo($X1, $X2)", ""));
      add(TipperFactory.tipper("null == $X1 ? $X2 : $X1", "defaultsTo($X1, $X2)", ""));
      add(TipperFactory.tipper("$X1 != null ? $X2 : $X3", "defaultsTo($X1, $X2, $X3)", ""));
      add(TipperFactory.tipper("$X1 == null ? $X2 : $X3", "defaultsTo($X1, $X2, $X3)", ""));
      add(TipperFactory.tipper("null != $X1 ? $X2 : $X3", "defaultsTo($X1, $X2, $X3)", ""));
      add(TipperFactory.tipper("null == $X1 ? $X2 : $X3", "defaultsTo($X1, $X2, $X3)", ""));
    }
  };

  @Override public String description(@SuppressWarnings("unused") final ConditionalExpression __) {
    return "defaulsTo pattern";
  }

  @Override public boolean canTip(final ConditionalExpression x) {
    for (final UserDefinedTipper<ConditionalExpression> ¢ : tippers)
      if (¢.canTip(x))
        return true;
    return false;
  }

  @Override public Tip tip(final ConditionalExpression x) throws TipperFailure {
    Logger.logNP(x, "defaultsTo");
    for (final UserDefinedTipper<ConditionalExpression> ¢ : tippers)
      if (¢.canTip(x))
        return ¢.tip(x);
    assert false;
    return null;
  }
}